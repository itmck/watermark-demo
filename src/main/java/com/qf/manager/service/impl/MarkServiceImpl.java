package com.qf.manager.service.impl;

import com.qf.manager.service.MarkService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Create by it_mck 2018/10/13 17:35
 *
 * @Description: 设置一个水印
 * @Version: 1.0时
 */
@Service
public class MarkServiceImpl implements MarkService {
    @Override
    public String watermark(CommonsMultipartFile image, String uploadPath, String realUploadPath) {

        return getUrlWatermark(image, uploadPath, realUploadPath);
    }

    @Override
    public String moreWatermark(CommonsMultipartFile image, String uploadPath, String realUploadPath) {

        return getUrlMoreWatermark(image, uploadPath, realUploadPath);
    }

    private String getUrlMoreWatermark(CommonsMultipartFile image, String uploadPath, String realUploadPath) {

        String logoFileName = "logo_more_" + image.getOriginalFilename();
        OutputStream os = null;

        try {
            Image img2 = ImageIO.read(image.getInputStream());//创建原图片对象
            int width = img2.getWidth(null);
            int height = img2.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);//创建图片缓存对象
            Graphics2D g = bufferedImage.createGraphics();//创建绘图工具对象
            g.drawImage(img2, 0, 0, width, height, null);
            g.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
            g.setColor(FONT_COLOR);

            int width1 = FONT_SIZE * getTextLength(MARK_TEXT);
            int heigth1 = FONT_SIZE;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));
            //设置旋转
            g.rotate(Math.toRadians(30), bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
            int x = -width / 2;
            int y = -height / 2;

            while (x < width * 1.5) {
                y = -height / 2;
                while (y < height * 1.5) {
                    g.drawString(MARK_TEXT, x, y);
                    y += heigth1 + 100;
                }
                x += width1 + 100;
            }

            g.dispose();//释放绘图工具
            os = new FileOutputStream(realUploadPath + "/" + logoFileName);
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
            en.encode(bufferedImage);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {

                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return uploadPath + "/" + logoFileName;
    }

    public int getTextLength(String text) {

        int length = text.length();
        for (int i = 0; i < text.length(); i++) {

            String s = String.valueOf(text.charAt(i));
            if (s.getBytes().length > 1) {
                length++;
            }
        }
        return length % 2 == 0 ? length / 2 : length / 2 + 1;
    }

    private String getUrlWatermark(CommonsMultipartFile image, String uploadPath, String realUploadPath) {
        String logoFileName = "logo_" + image.getOriginalFilename();
        OutputStream os = null;

        try {
            Image img2 = ImageIO.read(image.getInputStream());//创建原图片对象
            int width = img2.getWidth(null);
            int height = img2.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);//创建图片缓存对象
            Graphics2D g = bufferedImage.createGraphics();//创建绘图工具对象
            g.drawImage(img2, 0, 0, width, height, null);
            g.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
            g.setColor(FONT_COLOR);

            int width1 = FONT_SIZE * getTextLength(MARK_TEXT);
            int heigth1 = FONT_SIZE;
            int widthDiff = width - width1;
            int heigthDiff = height - heigth1;

            int x = X;
            int y = Y;
            if (x > widthDiff) {
                x = widthDiff;
            }
            if (y > heigthDiff) {
                y = heigthDiff;
            }
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));
            g.drawString(MARK_TEXT, x, y + FONT_SIZE);
            g.dispose();//释放绘图工具
            os = new FileOutputStream(realUploadPath + "/" + logoFileName);
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
            en.encode(bufferedImage);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {

                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return uploadPath + "/" + logoFileName;
    }
}

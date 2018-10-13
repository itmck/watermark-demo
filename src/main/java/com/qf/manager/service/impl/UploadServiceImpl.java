package com.qf.manager.service.impl;

import com.qf.manager.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

/**
 * Create by it_mck 2018/10/13 16:55
 *
 * @Description:
 * @Version: 1.0
 */
@Service
public class UploadServiceImpl implements UploadService {

    /**
     *
     *
     * @param image 要上传的文件
     * @param uploadPath  相对路径
     * @param realUploadPath 绝对路径
     * @return
     */
    @Override
    public String UploadImage(CommonsMultipartFile image, String uploadPath, String realUploadPath) {
        InputStream is = null;
        FileOutputStream os = null;
        try {
            is = image.getInputStream();
            os = new FileOutputStream(realUploadPath+"/"+image.getOriginalFilename());
            byte[] bytes = new byte[1024];
            int len=0;
            while((len=is.read(bytes))!=-1){
                os.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is!=null){

                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os!=null){

                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return uploadPath+"/"+image.getOriginalFilename();
    }
}

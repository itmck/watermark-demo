package com.qf.manager.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.awt.*;

/**
 * Create by it_mck 2018/10/13 17:32
 *
 * @Description:
 * @Version: 1.0
 */
public interface MarkService {
    String MARK_TEXT="itmc水印系列学习";
    String FONT_NAME="微软雅黑";
    int FONT_SIZE=30;
    int FONT_STYLE =Font.BOLD;
    Color FONT_COLOR=Color.BLACK;
    int X=10;
    int Y=10;
    float ALPHA=0.3F;

    //单文字水印
    String watermark(CommonsMultipartFile image, String uploadPath, String realUploadPath);
    //多文字水印
    String moreWatermark(CommonsMultipartFile image, String uploadPath, String realUploadPath);
}

package com.qf.manager.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Create by it_mck 2018/10/13 16:54
 *
 * @Description:
 * @Version: 1.0
 */
public interface UploadService {

    String UploadImage(CommonsMultipartFile image, String uploadPath, String realUploadPath);
}

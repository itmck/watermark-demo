package com.qf.manager.web;

import com.qf.manager.pojo.PicInfo;
import com.qf.manager.service.MarkService;
import com.qf.manager.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;

/**
 * Create by it_mck 2018/10/13 16:22
 *
 * @Description:
 * @Version: 1.0
 */

@Controller
@RequestMapping("water")
public class WatermarkAction {

    @Autowired
    private UploadService uploadService;
    @Autowired
    private MarkService markService;

    @RequestMapping("index")
    public String index() {

        return "index";
    }

    @PostMapping("mark")
    public String watermark(@RequestParam("img") CommonsMultipartFile files, Model model, HttpSession session) {

        String uploadPath = "/imgs";
        String realUploadPath = session.getServletContext().getRealPath(uploadPath);

        PicInfo pi = new PicInfo();
        PicInfo pi3 = new PicInfo();
        pi.setImgUrl(uploadService.UploadImage(files, uploadPath, realUploadPath));
        pi.setLogoImgUrl(markService.watermark(files, uploadPath, realUploadPath));
        pi3.setLogoImgUrl(markService.moreWatermark(files, uploadPath, realUploadPath));


        model.addAttribute("pi1", pi.getImgUrl());//加上水印前
        model.addAttribute("pi2", pi.getLogoImgUrl());//加上单文字水印后
        model.addAttribute("pi3", pi3.getLogoImgUrl());//加上多文字水印后
        return "watermark";
    }
}

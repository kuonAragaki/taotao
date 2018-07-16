package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.shopping.common.utils.FtpUtil;
import com.shopping.common.utils.IDUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明：上传图片处理
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/5 18:34
 */
@Controller
public class PictureController {
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile){
        Map resultMap = new HashMap<>();
        try {
            //生成一个新的文件名
            //取原始文件名
            String oldName = uploadFile.getOriginalFilename();
            //生成新文件名
            //UUID.randomUUID();
            String newName = IDUtils.getImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //图片上传
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            System.out.println("imagePath="+IMAGE_BASE_URL+imagePath+newName);
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, imagePath, newName, uploadFile.getInputStream());
            //返回结果
            if(!result){
                resultMap.put("error",1);
                resultMap.put("message","文件上传失败");
            }else{
                resultMap.put("error",0);
                resultMap.put("url",IMAGE_BASE_URL + imagePath + "/" + newName);
            }
        } catch (Exception e) {
            resultMap.put("error",1);
            resultMap.put("message","文件上传发生异常");
        }
        String json = JSON.toJSONString(resultMap);
        System.out.println("json"+json);
        return json;
    }
}

package com.pyp.cast.store.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

//文件上传工具类
public class UploadUtils {
    public String upload(MultipartFile multipartFile,String path){
        //文件名
        String fileName;
        //文件上传路径
        File file = new File(path);
        //判断该文件是否存在，不存在则创建
        if (!file.exists()){
            file.mkdir();
        }
        //获取上传文件的名称
        fileName = multipartFile.getOriginalFilename();
        //设置文件名称为唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-","");
        fileName = uuid+"_"+fileName;
        //完成文件上传
        try {
            multipartFile.transferTo(new File(path,fileName));
            return fileName;
        } catch (IOException e) {
            System.out.println("上传文件失败！");
            return "null";
        }
    }
}

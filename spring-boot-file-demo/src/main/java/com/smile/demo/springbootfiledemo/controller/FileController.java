package com.smile.demo.springbootfiledemo.controller;

import com.smile.demo.springbootfiledemo.util.MyFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 基于Spring boot web自带的MultipartFile
 * @author yangjunqiang
 */
@RestController
public class FileController {

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    // 获取配置文件中的文件路径
    @Value("${file.path}")
    private String fileSavePath;

    @PostMapping("file-upload")
    public String demo(@RequestParam("file") MultipartFile multipartFile){


        try {
            MyFileUtils.saveFile(multipartFile, fileSavePath);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件:{} 上传失败， 错误信息:{}", multipartFile.getOriginalFilename(), e.getMessage());
        }

        return "success";
    }


    /**
     * 批量上传
     * @param multipartFiles
     */
    @PostMapping("")
    public void batchFileUpload(@RequestParam("files") MultipartFile[] multipartFiles){

        for(MultipartFile multipartFile : multipartFiles){
            try {
                MyFileUtils.saveFile(multipartFile, fileSavePath);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("文件:{} 上传失败， 错误信息:{}", multipartFile.getOriginalFilename(), e.getMessage());
            }
        }
    }


    @GetMapping("download-demo/{fileName}")
    public String download(@PathVariable String fileName, HttpServletResponse response) throws Exception{

        File file = new File(fileSavePath + fileName);
        if(!file.exists()){
            logger.warn("文件不存在");
        }else{
            // 设置强制下载，不让浏览器解析
            response.setContentType("application/force-download");
            // 配置文件名
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            byte[] bytes = new byte[1024];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            // 输出流
            OutputStream outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(bytes);
            while(i != -1){
                outputStream.write(bytes, 0, i);
                i = bufferedInputStream.read(bytes);
            }
            bufferedInputStream.close();
            outputStream.close();
        }

        return "success";
    }

}

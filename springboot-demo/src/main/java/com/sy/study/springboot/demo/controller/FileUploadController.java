package com.sy.study.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("file")
public class FileUploadController {

    @GetMapping("")
    public String filePage(Model model){
        return "file";
    }

    @PostMapping("/upload")
    @ResponseBody
    public Object upload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        ServletContext sc = request.getServletContext();
        String filename = file.getOriginalFilename();
        Map<String, Object> map = new HashMap<>();
        if(!filename.endsWith(".jpg")){
            map.put("status","0");
            map.put("message","不是jpg格式图片");
            return map;
        }
        InputStream inputStream = file.getInputStream();
        File temp = new File(ResourceUtils.getURL("classpath:").getPath()+"/static/img/temp.jpg");
        if (!temp.exists()){
            temp.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(temp);
        byte[] bt=new byte[1024];
        int read=0;
        while ((read = inputStream.read(bt))>0){
            outputStream.write(bt,0,read);
        }
        inputStream.close();
        outputStream.close();
        map.put("status","1");
        return map;
    }
}

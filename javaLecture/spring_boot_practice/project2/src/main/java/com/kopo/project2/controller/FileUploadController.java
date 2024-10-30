package com.kopo.project2.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    @PostMapping("/upload-image")
    @ResponseBody
    public ResponseEntity<?> uploadImage(@RequestParam("upload") MultipartFile file) {
        return uploadFile(file, "images/");
    }

    @PostMapping("/upload-video")
    @ResponseBody
    public ResponseEntity<?> uploadVideo(@RequestParam("upload") MultipartFile file) {
        return uploadFile(file, "videos/");
    }

    @PostMapping("/upload-file")
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("upload") MultipartFile file) {
        return uploadFile(file, "files/");
    }

    private ResponseEntity<?> uploadFile(MultipartFile file, String directory) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = "/uploads/" + directory + fileName;
            File dest = new File(filePath);
            file.transferTo(dest);

            Map<String, Object> response = new HashMap<>();
            response.put("url", "/uploads/" + directory + fileName);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("파일 업로드 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

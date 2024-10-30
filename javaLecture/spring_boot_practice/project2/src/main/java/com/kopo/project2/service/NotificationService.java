package com.kopo.project2.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kopo.project2.mapper.NotificationDB;
import com.kopo.project2.model.Notification;

@Service
public class NotificationService {

    @Autowired
    private NotificationDB notificationDB;

    // 모든 공지사항 조회
    public List<Notification> getAllNotifications() {
        return notificationDB.findAllNotifications();
    }

    // 공지사항 ID로 조회
    public Notification getNotificationById(int id) {
        return notificationDB.findNotificationById(id);
    }

    // 새로운 공지사항 추가
    public void addNotification(Notification notification) {
        notificationDB.addNotification(notification);
    }

    // 공지사항 수정
    public void updateNotification(Notification notification) {
        notificationDB.updateNotification(notification);
    }

    // 공지사항 삭제
    public void deleteNotification(int id) {
        notificationDB.deleteNotification(id);
    }
    
    public String uploadFile(MultipartFile file, String fileType) {
        try {
            String directory = fileType + "s/"; // "images/", "videos/", "files/" 등의 폴더 구분
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = "/uploads/" + directory + fileName;
            File dest = new File(filePath);
            file.transferTo(dest);

            // DB에 저장된 파일 경로 추가
            notificationDB.insertFileRecord(fileType, filePath);

            return filePath; // 업로드된 파일의 경로를 반환
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드에 실패했습니다.", e);
        }
    }
    
//    public void createNotification(String title, String content, List<String> filePaths) {
//        Notification notification = new Notification();
//        notification.setTitle(title);
//        notification.setContent(content);
//        notificationMapper.insertNotification(notification);
//
//        // 공지사항과 업로드된 파일을 연결
//        for (String filePath : filePaths) {
//            notificationMapper.insertNotificationFile(notification.getId(), filePath);
//        }
//    }
}

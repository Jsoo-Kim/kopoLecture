package com.kopo.project2.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kopo.project2.model.Notification;

@Repository
public class NotificationDB {
    @Autowired
    private NotificationMapper notificationMapper;

    // 모든 공지사항 조회
    public List<Notification> findAllNotifications() {
        return notificationMapper.findAll();
    }

    // 공지사항 ID로 조회
    public Notification findNotificationById(int id) {
        return notificationMapper.findById(id);
    }

    // 새로운 공지사항 추가
    public void addNotification(Notification notification) {
        notificationMapper.insertNotification(notification);
    }

    // 공지사항 수정
    public void updateNotification(Notification notification) {
        notificationMapper.updateNotification(notification);
    }

    // 공지사항 삭제
    public void deleteNotification(int id) {
        notificationMapper.deleteNotification(id);
    }
    
    public void insertFileRecord(String fileType, String filePath) {
    	notificationMapper.insertFileRecord(fileType, filePath);  	
    }

}
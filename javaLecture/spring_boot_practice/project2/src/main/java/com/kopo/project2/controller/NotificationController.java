package com.kopo.project2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kopo.project2.model.Notification;
import com.kopo.project2.service.NotificationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // 공지사항 목록 페이지
    @GetMapping("/notification")
    public String getAllNotifications(HttpSession session, Model model) {
        Object isLogin = session.getAttribute("is_login");
        Object userType = session.getAttribute("user_type");

        if (isLogin != null && (boolean) isLogin) {
            List<Notification> notifications = notificationService.getAllNotifications();
            model.addAttribute("notifications", notifications);
            model.addAttribute("isLogin", true);
            model.addAttribute("userType", userType);
            return "notification";
        } else {
            return "redirect:/";
        }
    }

    // 공지사항 작성 페이지 이동
    @GetMapping("/create")
    public String createNotificationPage(HttpSession session) {
        Object userType = session.getAttribute("user_type");
        if ("admin".equals(userType)) {
            return "create_notification";
        } else {
            return "redirect:/notification";
        }
    }

    // 새로운 공지사항 작성 처리
    @PostMapping("/create")
    public String createNotification(@ModelAttribute Notification notification, HttpSession session) { 
    	Object userType = session.getAttribute("user_type");
        if ("admin".equals(userType)) {
            notificationService.addNotification(notification);
            return "redirect:/notification";
        } else {
            return "redirect:/";
        }
    }

    // 공지사항 상세보기 페이지
    @GetMapping("/view/{id}")
    public String viewNotification(@PathVariable int id, Model model) {
        Notification notification = notificationService.getNotificationById(id);
        model.addAttribute("notification", notification);
        return "view_notification";
    }

    // 공지사항 수정 페이지 이동
    @GetMapping("/edit/{id}")
    public String editNotificationPage(@PathVariable int id, HttpSession session, Model model) {
        Object userType = session.getAttribute("user_type");
        if ("admin".equals(userType)) {
            Notification notification = notificationService.getNotificationById(id);
            model.addAttribute("notification", notification);
            return "edit_notification";
        } else {
            return "redirect:/";
        }
    }

    // 공지사항 수정 처리
    @PostMapping("/edit")
    public String editNotification(@ModelAttribute Notification notification, HttpSession session) {
        Object userType = session.getAttribute("user_type");
        if ("admin".equals(userType)) {
            notificationService.updateNotification(notification);
            return "redirect:/notification";
        } else {
            return "redirect:/";
        }
    }

    // 공지사항 삭제 처리
    @PostMapping("/delete/{id}")
    public String deleteNotification(@PathVariable int id, HttpSession session) {
        Object userType = session.getAttribute("user_type");
        if ("admin".equals(userType)) {
            notificationService.deleteNotification(id);
        }
        return "redirect:/notification";
    }

}

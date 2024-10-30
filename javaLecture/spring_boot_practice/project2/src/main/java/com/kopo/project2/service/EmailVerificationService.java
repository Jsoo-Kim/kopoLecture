package com.kopo.project2.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kopo.project2.Sha256EncryptUtil;
import com.kopo.project2.mapper.UserDB;
import com.kopo.project2.model.User;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Service
public class EmailVerificationService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    private UserDB db;
    
    // 이메일 회원가입
    public void joinByEmail(String name, String email, String password, String password2, String sex, String address, String phone) throws MessagingException {
        // 비밀번호 일치 여부 검증
        if (!password.equals(password2)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 이메일 형식 검증
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("유효하지 않은 이메일 형식입니다.");
        }

        // 이메일 중복 체크
        if (db.findUserByEmail(email) != null) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        
        // 이메일 인증 링크 생성
        String token = generateVerificationToken();
        redisTemplate.opsForValue().set(token, email, 10, TimeUnit.MINUTES); // Redis에 토큰 저장

        // 인증 이메일 전송
        sendVerificationEmail(email, token);

        // 회원 추가\
        User user = new User(name, email, password, sex, address, phone);
        db.insertUserByEmail(user);
    }
    
    // 이메일 유효성 검증
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    // 이메일 인증 링크 전송
    public void sendVerificationEmail(String email, String token) throws MessagingException {
        String verificationLink = "http://localhost:8080/verify?token=" + token;
        String htmlContent = "<h1>이메일 인증</h1><p>인증 링크를 클릭하세요: <a href='" + verificationLink + "'>인증 링크</a></p>";
        emailService.sendHtmlEmail(email, "이메일 인증", htmlContent);
    }

    // 토큰 생성
    private String generateVerificationToken() {
        return java.util.UUID.randomUUID().toString();
    }

    // 토큰 검증 및 사용자 인증 처리
    public boolean verifyToken(String token) {
        String email = redisTemplate.opsForValue().get(token);
        if (email != null) {
            db.updateVerificationStatus(email, true);
            redisTemplate.delete(token);  // 토큰 삭제
            return true;
        }
        return false;
    }

    // 이메일을 통한 로그인 처리
    public String loginByEmail(String email, String password, HttpSession session, Model model) {
    	try {
    		User user;
            try {
                user = db.findUserByEmailAndPassword(email, password); 
            } catch (Exception e) {
                // 데이터베이스 접근 오류 처리
                model.addAttribute("message", "해당하는 이메일이 존재하지 않습니다.");
                model.addAttribute("messageType", "error");
                return "email_login";
            }
	        
	        if (user != null) {
	            session.setAttribute("is_login", true);
	            session.setAttribute("user_type", user.user_type);
	            session.setAttribute("user_id", user.id);
	
	            // 관리자 로그인 처리
	            if ("admin".equals(user.user_type)) {
	                return "redirect:/admin";
	            }
	            
	            // 일반 사용자 로그인 처리
	            return "redirect:/mypage";
	            
	        } else {
	            model.addAttribute("message", "로그인에 실패했습니다. 아이디와 패스워드를 확인해주세요.");
	            model.addAttribute("messageType", "error");
	            return "email_login";
	        }
        } catch (IllegalArgumentException e) {
            // 잘못된 인자가 들어왔을 경우
            model.addAttribute("message", "잘못된 요청입니다. 입력값을 다시 확인해주세요.");
            model.addAttribute("messageType", "error");
            return "email_login";
        } catch (Exception e) {
            // 그 외 모든 예외 처리
            e.printStackTrace(); // 로그에 예외 정보 출력
            model.addAttribute("message", "서버에 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
            model.addAttribute("messageType", "error");
            return "email_login";
        }
    	
    }
    	
}

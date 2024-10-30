package com.kopo.project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kopo.project2.service.EmailVerificationService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmailController {
	@Autowired
	private EmailVerificationService verificationService;

	@GetMapping("/email_join")
	public String emailJoinPage() {
		return "email_join";
	}

	@PostMapping("/email_join_action")
	public String signup(
			@RequestParam("name") String name, 
			@RequestParam("email") String email,
			@RequestParam("password") String password, 
			@RequestParam("password2") String password2,
			@RequestParam("sex") String sex, 
			@RequestParam("address") String address,
			@RequestParam("phone") String phone, 
			Model model) {
		try {
			verificationService.joinByEmail(name, email, password, password2, sex, address, phone);
			model.addAttribute("message", "Verification email sent to " + email);
			return "redirect:/login";
		} catch (IllegalArgumentException e) {
			model.addAttribute("message", "아이디와 비밀번호를 다시 확인해주세요.");
			return "email_join";
		} catch (MessagingException e) {
			model.addAttribute("message", "이메일 보내기를 실패했습니다.");
			return "email_join";
		}
	}

	@GetMapping("email_login")
	public String loginPage() {
		return "email_login";
	}

	@PostMapping("/email_login_action")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession session) {
		verificationService.loginByEmail(email, password, session, model);
		return "email_login";
	}

	@GetMapping("/verify")
	public String verifyEmail(@RequestParam("token") String token) {
		boolean isValid = verificationService.verifyToken(token);
		if (isValid) {
			return "이메일 인증에 성공했습니다.";
		} else {
			return "유효하지 않은 토큰이거나 만료된 토큰입니다.";
		}
	}
}

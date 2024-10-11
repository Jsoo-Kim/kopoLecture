package com.kopo.project2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kopo.project2.Sha256EncryptUtil;
import com.kopo.project2.mapper.DB;
import com.kopo.project2.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {
	
    @Autowired
    private DB db;  // DB 클래스 주입 (MyBatis Mapper를 직접 주입할 수도 있지만, 이후 사용자 DB나 관리자 DB 등으로 세분화가 가능하므로 DB.java라는 중간 레이어 사용)
    
    
	@GetMapping("/")
	public String index(HttpSession session, Model model) {
	    Object isLogin = session.getAttribute("is_login");
	    Object userType = session.getAttribute("user_type");
	    model.addAttribute("isLogin", isLogin != null);
	    model.addAttribute("userType", userType);
		return "index";
	}

	@GetMapping("join")
	public String joinPage() {
		return "join";
	}

	@PostMapping("join_action")
	public String joinAction(@RequestParam("name") String name, @RequestParam("id") String id,
			@RequestParam("password") String password, @RequestParam("password2") String password2,
			@RequestParam("sex") String sex, @RequestParam("address") String address,
			@RequestParam("phone") String phone, Model model) {

		// 아이디 중복 검증
		if (db.isUserIdExists(id)) {
			model.addAttribute("message", "이미 존재하는 아이디입니다.");
			model.addAttribute("messageType", "error");
			return "join";
		}

		// 회원가입 검증: 필수 값 체크 및 패스워드 일치 여부
		if (name.isEmpty() || id.isEmpty() || password.isEmpty() || password2.isEmpty()) {
			model.addAttribute("message", "필수 값이 누락되었습니다.");
			model.addAttribute("messageType", "error");
			return "join";
		}

		if (!password.equals(password2)) {
			model.addAttribute("message", "패스워드 확인 값이 다릅니다.");
			model.addAttribute("messageType", "error");
			return "join";
		}

        // 회원 추가
        User user = new User(name, id, Sha256EncryptUtil.shaEncoder(password), sex, address, phone);
        db.insertUser(user);
        
		model.addAttribute("message", "회원 가입이 완료 되었습니다.");
		model.addAttribute("messageType", "success");
		return "redirect:/login";
	}

	@GetMapping("login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("login_action")
	public String loginAction(@RequestParam("id") String id, @RequestParam("password") String password, Model model,
			HttpSession session) {
		User user = db.findUserByIdAndPassword(id, password);
		
//		System.out.println(user);
//		System.out.println("user_type: " + user.user_type);
//		System.out.println("id: " + user.id);

		if (user != null) {
			session.setAttribute("is_login", true);
			session.setAttribute("user_type", user.user_type);
			session.setAttribute("user_id", user.id);

			// 관리자 로그인
			if ("admin".equals(user.user_type)) {
				return "redirect:/admin";
			}
			// 일반 사용자 로그인
			return "redirect:/mypage";
		} else {
			model.addAttribute("message", "로그인에 실패했습니다. 아이디와 패스워드를 확인해주세요.");
			model.addAttribute("messageType", "error");
			return "login";
		}
	}
	
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션 초기화
        session.invalidate();
        return "redirect:/";
    }

	// 관리자 페이지 (회원 목록 조회 및 관리)
	@GetMapping("/admin")
	public String adminPage(Model model, HttpSession session) {
		if (!"admin".equals(session.getAttribute("user_type"))) {
			// 관리자가 아닌 경우 접근 불가 처리
			return "redirect:/";
		}

		List<User> userList = db.getAllUsers();
		model.addAttribute("users", userList);
		return "admin";
	}

	// 회원 정보 수정 페이지로 이동 (관리자 전용)
	@GetMapping("/admin/edit/{id}")
	public String editUserPage(@PathVariable("id") String userId, Model model, HttpSession session) {
//		System.out.println("userId for update: " + userId);

		if (!"admin".equals(session.getAttribute("user_type"))) {
			// 관리자가 아닌 경우 접근 불가 처리
			return "redirect:/";
		}

		User user = db.findUserById(userId);
//		System.out.println(user);

		if (user == null) {
			// user가 존재하지 않을 경우 에러 처리
			model.addAttribute("message", "해당 사용자를 찾을 수 없습니다.");
			model.addAttribute("messageType", "error");
			List<User> users = db.getAllUsers();
			model.addAttribute("users", users);
			return "admin";
		}

		model.addAttribute("user", user);
		return "edit_user";
	}

	// 회원 정보 수정 처리 (관리자 전용)
	@PostMapping("/admin/edit_action")
	public String editUserAction(@RequestParam("id") String userId, @RequestParam("name") String name,
			@RequestParam("sex") String sex, @RequestParam("address") String address,
			@RequestParam("phone") String phone, Model model, HttpSession session) {
		if (!"admin".equals(session.getAttribute("user_type"))) {
			// 관리자가 아닌 경우 접근 불가 처리
			return "redirect:/";
		}

        User user = new User(userId, name, sex, address, phone);
        db.updateUser(user);
        
		model.addAttribute("message", "회원 정보가 수정되었습니다.");
		model.addAttribute("messageType", "success");
		return "redirect:/admin";
	}

	// 회원 비밀번호 수정 (관리자 전용)
	@PostMapping("/admin/edit_password_action")
	public String adminEditPassword(@RequestParam("id") String userId, @RequestParam("password") String password,
			@RequestParam("password2") String password2, Model model, HttpSession session) {

		if (!"admin".equals(session.getAttribute("user_type"))) {
			return "redirect:/";
		}

		// 비밀번호 검증
		if (!password.equals(password2)) {
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("messageType", "error");
			return "edit_user";
		}
		if (password.isEmpty() || password2.isEmpty()) {
			model.addAttribute("message", "비밀번호가 입력되지 않았습니다.");
			model.addAttribute("messageType", "error");
			return "edit_user";
		}

		// 비밀번호 암호화 후 업데이트
		db.updatePassword(userId, password2);
		
		model.addAttribute("message", "비밀번호가 성공적으로 수정되었습니다.");
		model.addAttribute("messageType", "success");
		return "redirect:/admin";
	}

	// 일반 사용자 마이페이지
	@GetMapping("/mypage")
	public String mypage(Model model, HttpSession session) {
		String userId = (String) session.getAttribute("user_id");
		if (userId == null || !"guest".equals(session.getAttribute("user_type"))) {
			// 로그인이 되어 있지 않거나 사용자가 일반 유저가 아닌 경우
			return "redirect:/login";
		}

		User user = db.findUserById(userId);
//		System.out.println("user_id: " + user.id);
		model.addAttribute("user", user);
		return "mypage";
	}

	@PostMapping("/delete_user")
	public String deleteUser(@RequestParam("id") String userId, Model model, HttpSession session) {
		if (!"admin".equals(session.getAttribute("user_type"))) {
			// 관리자가 아닌 경우 접근 불가 처리
			return "redirect:/";
		}

		db.deleteUser(userId);
		
		model.addAttribute("message", "사용자가 삭제되었습니다.");
		model.addAttribute("messageType", "success");
		return "redirect:/admin";
	}

}

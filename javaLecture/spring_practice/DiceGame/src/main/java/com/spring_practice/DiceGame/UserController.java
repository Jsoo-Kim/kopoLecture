package com.spring_practice.DiceGame;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(DiceController.class);
	UserDB userDB = new UserDB();
	
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		
		if (session.getAttribute("is_login") == null || !(boolean)session.getAttribute("is_login")) {
			return "redirect:/signup_form";	
		} 
		
		ArrayList<User> list = userDB.selectData();
		String htmlText = "";
		for (int i = 0; i < list.size(); i++) {
			htmlText += "<tr>";
			htmlText += "<td>";
			htmlText += list.get(i).name;
			htmlText += "</td>";
			htmlText += "<td>";
			htmlText += list.get(i).userId;
			htmlText += "</td>";
			htmlText += "<td>";
			htmlText += list.get(i).userPw;
			htmlText += "</td>";
			htmlText += "<td>";
			htmlText += list.get(i).gender;
			htmlText += "</td>";
			htmlText += "<td>";
			htmlText += list.get(i).address;
			htmlText += "</td>";
		    htmlText += "<td>";
		    htmlText += "<a href='update_form?idx=" + list.get(i).id + "'>수정</a>";
		    htmlText += "</td>";  
		    htmlText += "<td>";
		    htmlText += "<a href='delete_action?idx=" + list.get(i).id + "'>삭제</a>";
		    htmlText += "</td>";  
			htmlText += "</tr>";
		}
		model.addAttribute("list", htmlText);
		return "userList";
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public String createTable(Locale locale, Model model) {
		userDB.createTable();
		model.addAttribute("message", "테이블이 생성되었습니다.");
		return "message";
	}

	@RequestMapping(value = "/signup_form", method = RequestMethod.GET)
	public String signup_form(Locale locale, Model model) {
		return "signup_form";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(Locale locale, Model model, ServletRequest request) {
		// spring의 한글 깨짐 현상 방지하려면 이렇게 해야 함! 스프링 부트에는 없는 현상
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		userPw = Sha256EncryptUtil.shaEncoder(userPw);
		
		String now = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
		User user = new User(name, userId, userPw, gender, address, now);
		userDB.insertData(user);
		model.addAttribute("message", "회원가입이 완료되었습니다.");
		return "redirect:/";
	}

	@RequestMapping(value = "/signin_form", method = RequestMethod.GET)
	public String signin_form(Locale locale, Model model) {
		return "signin_form";
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin(Locale locale, Model model, HttpSession session,
			@RequestParam("userId") String userId,
			@RequestParam("userPw") String userPw) {
		
		userPw = Sha256EncryptUtil.shaEncoder(userPw);
		boolean result = userDB.signin(new User(userId, userPw));
		if (result) {
			model.addAttribute("message", "로그인에 성공했습니다.");
			session.setAttribute("is_login", true);  // 세션에 'key, value'를 'is_login, true' 로 저장
			return "redirect:/";
		} else {
			model.addAttribute("message", "로그인에 실패했습니다.");
			session.setAttribute("is_login", false);
			return "signin_form";
		}		
	}
	
	// 수정, 삭제는 Dice Game 목록 부분에 구현했습니다 
	// 취업 과제로 시간이 너무 부족하여 비밀번호 확인, 암호화, 세션 등 세세한 부분은 신경 쓰지 못습니다 ㅜㅜ
	
//	@RequestMapping(value = "/update_form", method = RequestMethod.GET)
//	public String updateForm(Locale locale, Model model, 
//			@RequestParam("id") int id) {
//		User user = userDB.detailData(new User(id));
//		System.out.println("dice : " + dice);
//		model.addAttribute("dice", dice);
//		return "update_form";
//	}
//	
//	@RequestMapping(value = "/update_action", method = RequestMethod.GET)
//	public String updateAction(Locale locale, Model model,
//			@RequestParam("idx") int idx,
//			@RequestParam("user") int user,
//			@RequestParam("computer") int computer,
//			@RequestParam("result") String result,
//			@RequestParam("created") String created) {
//			userDB.updateData(new Dice(idx, user, computer, result, created));
//		model.addAttribute("message", "데이터가 수정되었습니다.");
//		return "redirect:/";
//	}
//
//	@RequestMapping(value = "/delete_action", method = RequestMethod.GET)
//	public String deleteForm(Locale locale, Model model, 
//			@RequestParam("idx") int idx) {
//		userDB.deleteData(new Dice(idx));
//		return "redirect:/";
//	}

}

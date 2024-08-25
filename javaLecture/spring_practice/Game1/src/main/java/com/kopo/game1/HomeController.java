package com.kopo.game1;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user1 = (String) session.getAttribute("user1");
		String user2 = (String) session.getAttribute("user2");
		String user3 = (String) session.getAttribute("user3");
		String user4 = (String) session.getAttribute("user4");
		String user5 = (String) session.getAttribute("user5");
		String user6 = (String) session.getAttribute("user6");
		model.addAttribute("user1", user1);
		model.addAttribute("user2", user2);
		model.addAttribute("user3", user3);
		model.addAttribute("user4", user4);
		model.addAttribute("user5", user5);
		model.addAttribute("user6", user6);
		return "home";
	}
	
	@RequestMapping(value = "/input_user", method = RequestMethod.GET)
	public String inputUser(Locale locale, Model model) {
		return "iu";
	}

	@RequestMapping(value = "/input_action", method = RequestMethod.POST)
	public String inputAction(Locale locale, Model model, HttpServletRequest request) {
		
		// POST 요청은 RequestParam을 쓰면 한글 깨짐! 그래서 아래 try-catch 부분 넣어주고 @RequestParam 대신 request.getParameter로 입력값 받아옴!
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String user1 = (String) request.getParameter("user1");
		String user2 = (String) request.getParameter("user2");
		String user3 = (String) request.getParameter("user3");
		String user4 = (String) request.getParameter("user4");
		String user5 = (String) request.getParameter("user5");
		String user6 = (String) request.getParameter("user6");
	
		HttpSession session = request.getSession();
		session.setAttribute("user1", user1);
		session.setAttribute("user2", user2);
		session.setAttribute("user3", user3);
		session.setAttribute("user4", user4);
		session.setAttribute("user5", user5);
		session.setAttribute("user6", user6);
		return "message";
	}
	
	@RequestMapping(value = "/game", method = RequestMethod.GET)
	public String game(Locale locale, Model model, HttpServletRequest request) {
		
		// 지뢰 좌표 생성
		Random random = new Random();
		int bombNumber = random.nextInt(25);
		int yNumber = (int) Math.floor(bombNumber / 5);
		int xNumber = bombNumber % 5;
		
		HttpSession session = request.getSession();
		session.setAttribute("y", "" + yNumber);
		session.setAttribute("x", "" + xNumber);
		System.out.println(yNumber + ", " + xNumber);
		
		return "game";
	}
	
	@ResponseBody // jsp 파일 말고 문자열이 그대로 return 됨
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String check(Locale locale, Model model, HttpServletRequest request
			, @RequestParam("x") String x
			, @RequestParam("y") String y) {
		HttpSession session = request.getSession();
		String sessionX = (String) session.getAttribute("x");
		String sessionY = (String) session.getAttribute("y");
		if (sessionX.equals(x) && sessionY.equals(y)) {
			return "bomb";
		}
		return "safe";
	}

}

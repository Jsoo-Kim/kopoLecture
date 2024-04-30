package com.spring_practice.t1;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET) // 현재 localhost:8088/t1(t1은 프로젝트 이름) 까지가 디폴트! value가 의미하는 건 localhost:8088/t1/
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		// 아래는 오늘 날짜, 시간 출력하는 코드
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home"; // home.jsp 파일을 불러줘
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET) // 현재 localhost:8088/t1(t1은 프로젝트 이름) 까지가 디폴트! value가 의미하는 건 localhost:8088/t1/
	public String h2(Locale locale, Model model) { // 메소드 이름은 겹치지만 않으면 아무거나
		model.addAttribute("aabb", "파인애플"); // 변수 aabb에 "파인애플" 이라는 값 세팅
		return "list"; // list.jsp 파일을 불러줘
	}
	
}

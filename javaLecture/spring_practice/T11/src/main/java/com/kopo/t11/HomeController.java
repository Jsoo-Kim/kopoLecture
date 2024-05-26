package com.kopo.t11;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createTable(Locale locale, Model model) {
		DB db = new DB<Student>("c:/kopo/t11.sqlite", "st");
		db.createTable(new Student());
		
		model.addAttribute("message", "테이블이 생성되었습니다.");
		return "message";
	}
		
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertPage(Locale locale, Model model) {
		return "i1";
	}

	@RequestMapping(value = "/insert_action", method = RequestMethod.GET)
	public String insertAction(Locale locale, Model model
			, @RequestParam("name") String name
			, @RequestParam("middle_score") int middleScore
			, @RequestParam("final_score") int finalScore) {
		String now = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
		DB db = new DB<Student>("c:/kopo/t11.sqlite", "st");
		db.insertData(new Student(name, middleScore, finalScore, now));
		model.addAttribute("message", "데이터가 입력되었습니다.");
		return "message";
	}
}

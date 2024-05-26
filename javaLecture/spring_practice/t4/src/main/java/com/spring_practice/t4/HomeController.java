package com.spring_practice.t4;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		DB db = new DB<Student>("d:\\gitMaster\\kopoLecture\\javaLecture\\spring_practice\\students.db", "students");
		ArrayList<Student> students = db.selectData(new Student());
//		System.out.println(Arrays.toString(students));
		
		String htmlText = "";
		
		for (int i = 0; i < students.size(); i++) {
			htmlText = htmlText + "<tr>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + students.get(i).id;
			htmlText = htmlText + "</td>";			
			htmlText = htmlText + "<td>";
			htmlText = htmlText + students.get(i).name;
			htmlText = htmlText + "</td>";			
			htmlText = htmlText + "<td>";
			htmlText = htmlText + students.get(i).middleScore;
			htmlText = htmlText + "</td>";			
			htmlText = htmlText + "<td>";
			htmlText = htmlText + students.get(i).finalScore;
			htmlText = htmlText + "</td>";			
			htmlText = htmlText + "<td>";
			htmlText = htmlText + students.get(i).created;
			htmlText = htmlText + "</td>";			
			htmlText = htmlText + "</tr>";
		}
		model.addAttribute("list", htmlText);
		return "home";
	}

	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createTable(Locale locale, Model model) {
		DB db = new DB<Student>("d:\\gitMaster\\kopoLecture\\javaLecture\\spring_practice\\students.db", "students");
		db.createTable( new Student());
		
		model.addAttribute("message", "테이블이 생성되었습니다.");
		return "message";
	}

	@RequestMapping(value = "/student_form", method = RequestMethod.GET)
	public String studentForm(Locale locale, Model model) {
		
		return "student_form";
	}
	
	@RequestMapping(value = "/insert_action", method = RequestMethod.GET)
	public String insertAction(Locale locale, Model model,
			@RequestParam("name") String name,
			@RequestParam("middle_score") int middleScore,
			@RequestParam("final_score") int finalScore) {
		
//		Date nowDate = new Date(); // 현재 시간
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String strNowDate = simpleDateFormat.format(nowDate);
		String now = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
		
		DB db = new DB<Student>("d:\\gitMaster\\kopoLecture\\javaLecture\\spring_practice\\students.db", "students");
		db.insertData(new Student(name, middleScore, finalScore, now));
		model.addAttribute("message", "데이터가 입력되었습니다.");
		return "student_form";
	}
	
}

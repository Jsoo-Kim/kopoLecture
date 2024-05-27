package com.spring_practice.t4;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
	
//	DB db = new DB<Student>("d:\\gitMaster\\kopoLecture\\javaLecture\\spring_practice\\students.db", "students");
	DB db = new DB<Student>("/Users/kimjisoo/Desktop/Jisoo/스마트금융/kopoLecture/javaLecture/spring_practice/students.db", "students");
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}

	@RequestMapping(value = "/detail_list", method = RequestMethod.GET)
	public String detailList(Locale locale, Model model) {
		ArrayList<Student> students = db.selectData(new Student());
//		System.out.println(students.toString());

		// 람다는 스프링 버전 3.2.x 이상에 JDK 8 이상부터 지원!
		students.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o2.totalScore - o1.totalScore;
			}
		});

		String htmlText = "";
		
		for (int i = 0; i < students.size(); i++) {
			Student student = (Student) db.detailData(students.get(i));
			
			htmlText = htmlText + "<tr>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + (i + 1);
			htmlText = htmlText + "</td>";			
			htmlText = htmlText + "<td>";
			htmlText = htmlText + student.id;
			htmlText = htmlText + "</td>";			
			htmlText = htmlText + "<td>";
			htmlText = htmlText + student.name;
			htmlText = htmlText + "</td>";			
			htmlText = htmlText + "<td>";
			htmlText = htmlText + student.middleScore;
			htmlText = htmlText + "</td>";			
			htmlText = htmlText + "<td>";
			htmlText = htmlText + student.finalScore;
			htmlText = htmlText + "</td>";			
			htmlText = htmlText + "<td>";
			htmlText = htmlText + student.totalScore;
			htmlText = htmlText + "</td>";			
			htmlText = htmlText + "<td>";
			htmlText = htmlText + student.avgScore;
			htmlText = htmlText + "</td>";			
			htmlText = htmlText + "<td>";
			htmlText = htmlText + student.created;
			htmlText = htmlText + "</td>";	
		    htmlText = htmlText + "<td>";
		    htmlText = htmlText + "<a href='update_form?id=" + student.id + "'>수정</a>";
		    htmlText = htmlText + "</td>";  
			htmlText = htmlText + "</tr>";
		}
		
		model.addAttribute("list", htmlText);
		return "detail_list";
	}

	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createTable(Locale locale, Model model) {
		db.createTable(new Student());
		
		model.addAttribute("message", "테이블이 생성되었습니다.");
		return "message";
	}

	@RequestMapping(value = "/student_form", method = RequestMethod.GET)
	public String studentForm(Locale locale, Model model) {
		return "student_form";
	}
	
	// 아직 body 로 보내는 걸 안 배워서 POST 가 아니라 GET 으로 한 건가?
	@RequestMapping(value = "/insert_action", method = RequestMethod.GET)
	public String insertAction(Locale locale, Model model,
			@RequestParam("name") String name,
			@RequestParam("middle_score") int middleScore,
			@RequestParam("final_score") int finalScore) {
		
//		Date nowDate = new Date(); // 현재 시간
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String strNowDate = simpleDateFormat.format(nowDate);
		String now = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
		
		db.insertData(new Student(name, middleScore, finalScore, now));
		model.addAttribute("message", "데이터가 입력되었습니다.");
		return "student_form";
	}

	@RequestMapping(value = "/update_form", method = RequestMethod.GET)
	public String updateForm(Locale locale, Model model, 
			@RequestParam("id") int id) {
		Student student = (Student) db.detailStudent(id);
		model.addAttribute("student", student);
		return "update_form";
	}
	
	@RequestMapping(value = "/update_action", method = RequestMethod.GET)
	public String updateAction(Locale locale, Model model,
			@RequestParam("id") int id,
			@RequestParam("name") String name,
			@RequestParam("middle_score") int middleScore,
			@RequestParam("final_score") int finalScore,
			@RequestParam("created") String created) {
//		Student student = (Student) db.detailStudent(id); // DB 2번 타게 됨 
		db.updateData(new Student(id, name, middleScore, finalScore, created));
		model.addAttribute("message", "데이터가 수정되었습니다.");
		return "redirect:/detail_list";
	}
	
	
}

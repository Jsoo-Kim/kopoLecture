package com.spring_practie.t2;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller // 컨트롤러 어노테이션만 있으면 컨트롤러가 됨
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) { 
		DataReader dataReader = new DataReader("d:\\gitMaster\\kopoLecture\\javaLecture\\s.sqlite", "student_grades");
		dataReader.open();
		try {
			dataReader.createTable();
			model.addAttribute("query_result", dataReader.selectData());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			
		} finally {
			dataReader.close();
		}

		return "home";
	}

	@RequestMapping(value = "/aa", method = RequestMethod.GET)
	public String aa(Locale locale, Model model) { // 메소드 이름은 중요하지 않은데 보통 들어오는 리퀘스트 이름이랑 맞춰줌
		System.out.println("start time:" + LocalDateTime.now());
		DataReader dataReader = new DataReader("d:\\gitMaster\\kopoLecture\\javaLecture\\s.sqlite", "student_grades");
		dataReader.open();
		
		// 테이블 삭제 및 시퀀스인덱스(AutoIncrement) 초기화
//		try {
//			dataReader.deleteTable(); 
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		// insertData() 에서 throws Exception 해 놨으니까 여기에서 처리! 왜 여기에서 처리하지? 못 들었다
		try {
			dataReader.insertData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dataReader.close();
		return "a3";
	}

	@ResponseBody // 파일 말고 그냥 String으로 응답 주고 싶을 때
	@RequestMapping(value = "/b", method = RequestMethod.GET)
	public String b(Locale locale, Model model) { 
		// 아래처럼 할 수도 있지만 귀찮으니까 jsp 파일로 return 하자!
		String htmlText = "";
		htmlText = htmlText + "<html>";
		htmlText = htmlText + "<body>";
		htmlText = htmlText + "<p>sdsfdsfs</p>";
		htmlText = htmlText + "</body>";
		htmlText = htmlText + "</html>";
		return htmlText;
	}

	
}

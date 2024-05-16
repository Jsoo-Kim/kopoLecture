package com.spring_practice.t3;

import java.sql.SQLException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 컨트롤러 어노테이션만 있으면 컨트롤러가 됨
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	String dbUrl = "jdbc:oracle:thin:@192.168.110.111:1521:orcl";
	String dbUser = "JISOO";
	String dbPassword = "jisoo";
	String dbTableName = "student_grades";
	
	DataReader dataReader = new DataReader(dbUrl, dbUser, dbPassword, dbTableName);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createTable(Locale locale, Model model) throws SQLException {

		dataReader.open();
		try {
			dataReader.createTable();
			model.addAttribute("query_result", dataReader.selectData());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dataReader.close();
		}

		return "home";
	}

//	@RequestMapping(value = "/insert", method = RequestMethod.GET)
//	public String insertData(Locale locale, Model model) throws SQLException {
//
//		DataReader dataReader = new DataReader(dbUrl, dbUser, dbPassword, dbTableName);
//		dataReader.open();
//
//		try {
//			dataReader.insertData();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		dataReader.close();
//		return "home";
//	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteTable(Locale locale, Model model) throws SQLException {

		dataReader.open();

		try {
			dataReader.deleteTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dataReader.close();
		return "home";
	}
	
	@RequestMapping(value = "/drop", method = RequestMethod.GET)
	public String dropTable(Locale locale, Model model) throws SQLException {
		
		dataReader.open();
		
		try {
			dataReader.dropTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dataReader.close();
		return "home";
	}
	
	@RequestMapping(value = "/insertPage", method = RequestMethod.GET)
	public String insertPage(Locale locale, Model model) {
		return "insertPage";
	}
	
	// 결국 localhost:8088/t2/insertAction?t1="이름"&s1=123 이렇게 쿼리스트링으로 주는 것! 폼에 굳이 입력 안 해도 똑같이 작동함
	@RequestMapping(value = "/insertAction", method = RequestMethod.GET)
	public String insertAction(Locale locale, Model model, @RequestParam("t1") String name, @RequestParam("s1") int score) throws SQLException {
		
		dataReader.open();
		
		try {
			dataReader.insertData(name, score);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dataReader.close();
	
		return "redirect:/";
	}
	

}

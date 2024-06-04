package com.spring_practice.jsweb;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
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
			htmlText += list.get(i).id;
			htmlText += "</td>";
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
			htmlText += list.get(i).created;
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
}

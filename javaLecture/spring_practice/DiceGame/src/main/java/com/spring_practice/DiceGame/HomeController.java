package com.spring_practice.DiceGame;

import java.util.ArrayList;
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
	DB db = new DB();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		ArrayList<Dice> list = db.selectData();
		String htmlText = "";
		for (int i = 0; i < list.size(); i++) {
			htmlText += "<tr>";
			htmlText += "<td>";
			htmlText += list.get(i).idx;
			htmlText += "</td>";
			htmlText += "<td>";
			htmlText += list.get(i).user;
			htmlText += "</td>";
			htmlText += "<td>";
			htmlText += list.get(i).computer;
			htmlText += "</td>";
			htmlText += "<td>";
			htmlText += list.get(i).result;
			htmlText += "</td>";
			htmlText += "<td>";
			htmlText += list.get(i).created;
			htmlText += "</td>";
			htmlText += "</tr>";
		}
		model.addAttribute("list", htmlText);
		return "list";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createTable(Locale locale, Model model) {
		db.createTable();
		model.addAttribute("message", "테이블이 생성되었습니다.");
		return "message";
	}

	@RequestMapping(value = "/play", method = RequestMethod.GET)
	public String play(Locale locale, Model model) {
		return "form";
	}

	@RequestMapping(value = "/play_action", method = RequestMethod.GET)
	public String playAction(Locale locale, Model model) {
		Dice dice = new Dice();
		dice.roll();
		db.insertData(dice);
		return "redirect:/";
	}
	
}

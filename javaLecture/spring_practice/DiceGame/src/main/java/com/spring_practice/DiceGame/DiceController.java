package com.spring_practice.DiceGame;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(DiceController.class);
	DiceDB diceDB = new DiceDB();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		ArrayList<Dice> list = diceDB.selectData();
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
		    htmlText += "<td>";
		    htmlText += "<a href='update_form?idx=" + list.get(i).idx + "'>수정</a>";
		    htmlText += "</td>";  
		    htmlText += "<td>";
		    htmlText += "<a href='delete_action?idx=" + list.get(i).idx + "'>삭제</a>";
		    htmlText += "</td>";  
			htmlText += "</tr>";
		}
		model.addAttribute("list", htmlText);
		return "list";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createTable(Locale locale, Model model) {
		diceDB.createTable();
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
		diceDB.insertData(dice);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/update_form", method = RequestMethod.GET)
	public String updateForm(Locale locale, Model model, 
			@RequestParam("idx") int idx) {
		Dice dice = diceDB.detailData(new Dice(idx));
		System.out.println("dice : " + dice);
		model.addAttribute("dice", dice);
		return "update_form";
	}
	
	@RequestMapping(value = "/update_action", method = RequestMethod.GET)
	public String updateAction(Locale locale, Model model,
			@RequestParam("idx") int idx,
			@RequestParam("user") int user,
			@RequestParam("computer") int computer,
			@RequestParam("result") String result,
			@RequestParam("created") String created) {
			diceDB.updateData(new Dice(idx, user, computer, result, created));
		model.addAttribute("message", "데이터가 수정되었습니다.");
		return "redirect:/";
	}

	@RequestMapping(value = "/delete_action", method = RequestMethod.GET)
	public String deleteForm(Locale locale, Model model, 
			@RequestParam("idx") int idx) {
		diceDB.deleteData(new Dice(idx));
		return "redirect:/";
	}
}

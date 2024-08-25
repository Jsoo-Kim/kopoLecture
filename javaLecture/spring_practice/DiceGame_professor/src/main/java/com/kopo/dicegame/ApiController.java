package com.kopo.dicegame;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {
	@ResponseBody
	@RequestMapping(value = "/list_api", method = RequestMethod.GET)
	public ArrayList<Dice> listApi(Locale locale, Model model) {
		DB db = new DB();
		ArrayList<Dice> list = db.selectData(); 
		return list;
	}
}

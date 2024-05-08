package com.spring_practie.t2;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 꼭 HomeController에 추가하지 않아도 Controller 어노테이션을 이용하면 됨
public class H2 { 
	@ResponseBody
	@RequestMapping(value = "/b2", method = RequestMethod.GET)
	public String b(Locale locale, Model model) {
		return "22222";
	}
}

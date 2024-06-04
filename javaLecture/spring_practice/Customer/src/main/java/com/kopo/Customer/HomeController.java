package com.kopo.Customer;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		if (session.getAttribute("is_login") == null || !(boolean)session.getAttribute("is_login")) {
			return "redirect:/sign_in";
		}
		
		DB db = new DB();
		ArrayList<Customer> list = (ArrayList<Customer>) db.getAllCustomers();
		String htmlText = "";
		for (int i = 0; i < list.size(); i++) {
			htmlText = htmlText + "<tr>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).idx;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).name;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).id;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).pw;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).gender;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).address;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).created;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "</tr>";
		}
		model.addAttribute("list", htmlText);

		return "list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Locale locale, Model model) {
		DB db = new DB();
		db.createTable();
		model.addAttribute("message", "테이블이 생성되었습니다.");
		return "message";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Locale locale, Model model) {
		return "form";
	}

	@RequestMapping(value = "/join_action", method = RequestMethod.POST)
	public String joinAction(Locale locale, Model model, ServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String genderStr = request.getParameter("gender");
		String address = request.getParameter("address");
		pw = Sha256EncryptUtil.shaEncoder(pw);
		
		// 전달받은 성별 문자열을 정수형으로 변환하여 저장
		int gender = genderStr.equalsIgnoreCase("남") ? 1 : 0;
		System.out.println(gender);

		// 고객 정보를 데이터베이스에 입력
		String now = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());

		// 고객 정보를 생성
		Customer customer = new Customer(name, id, pw, gender, address, now);

		// 데이터베이스에 데이터 삽입
		DB db = new DB();
		db.insertData(customer);

		// 메시지 추가
		model.addAttribute("message", "데이터가 입력 되었습니다");

		return "message";
	}

	@RequestMapping(value = "/sign_in", method = RequestMethod.GET)
	public String signIn(Locale locale, Model model) {
		return "sign_in";
	}

	@RequestMapping(value = "/sign_in_action", method = RequestMethod.GET)
	public String signInAction(Locale locale, Model model
			, @RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session) {
		pw = Sha256EncryptUtil.shaEncoder(pw);
		DB db = new DB();
		boolean result = db.signinAction(new Customer(id, pw));
		if (result) {
			model.addAttribute("message", "로그인이 되었습니다.");
			session.setAttribute("is_login", true);
		} else {
			model.addAttribute("message", "로그인이 실패했습니다. 아이디와 패스워드를 확인하세요.");
			session.setAttribute("is_login", false);
		}
		return "message";
	}
}

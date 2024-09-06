package com.kopo.game1;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    List<String> users = (List<String>) session.getAttribute("users");

	    if (users == null || users.isEmpty()) {
	        System.out.println("사용자 목록이 없습니다.");
	    } else {
	        System.out.println("사용자 목록: " + users);
	    }

	    model.addAttribute("users", users);
	    model.addAttribute("turn", session.getAttribute("turn"));
	    return "home";
	}
	
	@RequestMapping(value = "/input_user", method = RequestMethod.GET)
	public String inputUser(Locale locale, Model model) {
		return "iu";
	}

	@RequestMapping(value = "/input_action", method = RequestMethod.POST)
	public String inputAction(Locale locale, Model model, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		int playerCount = Integer.parseInt(request.getParameter("playerCount"));
		int mapSize = Integer.parseInt(request.getParameter("mapSize"));
		
		List<String> users = new ArrayList<>();
		for (int i = 1; i <= playerCount; i++) {
			users.add(request.getParameter("user" + i));
		}
		session.setAttribute("users", users);
		session.setAttribute("mapSize", mapSize);
		session.setAttribute("turn", 0);
		
		System.out.println(users);
		return "redirect:/";
	}

	@RequestMapping(value = "/game", method = RequestMethod.GET)
	public String game(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int mapSize = (int) session.getAttribute("mapSize");

		Random random = new Random();
		int bombNumber = random.nextInt(mapSize * mapSize);
		int yNumber = (int) Math.floor(bombNumber / mapSize);
		int xNumber = bombNumber % mapSize;

		session.setAttribute("yNumber", "" + yNumber);
		session.setAttribute("xNumber", "" + xNumber);
		model.addAttribute("mapSize", mapSize);
		return "game";
	}

	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Map<String, Object> check(Locale locale, Model model, HttpServletRequest request, 
			@RequestParam("x") String x, 
			@RequestParam("y") String y) {
		HttpSession session = request.getSession();
		String sessionX = (String) session.getAttribute("xNumber");
		String sessionY = (String) session.getAttribute("yNumber");
		int turn = (int) session.getAttribute("turn");

		Map<String, Object> response = new HashMap<>();
		
		// Update turn
		List<String> users = (List<String>) session.getAttribute("users");
		turn = (turn + 1) % users.size();
		session.setAttribute("turn", turn);
		
	    response.put("turn", turn); 
	    response.put("currentPlayer", users.get(turn)); 	
		
		if (sessionX.equals(x) && sessionY.equals(y)) {
			response.put("result", "bomb");
		} else {
			response.put("result", "safe");
		}

		return response;
	}

	@RequestMapping(value = "/game_over", method = RequestMethod.GET)
	public String gameOver(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int turn = (int) session.getAttribute("turn");
		List<String> users = (List<String>) session.getAttribute("users");
		model.addAttribute("loser", users.get(turn));
		return "gameover";
	}
}


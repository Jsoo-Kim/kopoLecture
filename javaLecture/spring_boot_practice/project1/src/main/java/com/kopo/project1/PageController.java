package com.kopo.project1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
public class PageController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("join")
    public String joinPage() {
        return "join";
    }
    
    @PostMapping("join_action")
    public String joinAction(Model model, @RequestParam("name") String name, @RequestParam("id") String id, @RequestParam("password") String password, @RequestParam("password2") String password2, @RequestParam("sex") String sex, @RequestParam("address") String address, @RequestParam("phone") String phone) {
        DB db = new DB();
        if (password.equals(password2)) {
            db.insertData(new User(name, id, password, sex, address, phone));
            model.addAttribute("message", "회원 가입이 완료 되었습니다.");
        } else {
            model.addAttribute("message", "패스워드 확인 값이 다릅니다.");
        }
    
        return "message";
    }

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }
    
    @PostMapping("login_action")
    public String loginAction(Model model, @RequestParam("id") String id, @RequestParam("password") String password, HttpSession session) {
        DB db = new DB();
        boolean result = db.loginData(new User(id, password));
        if (result) {
            session.setAttribute("is_login", true);
            model.addAttribute("message", "로그인 되었습니다.");
        } else {
            session.setAttribute("is_login", false);
            model.addAttribute("message", "로그인이 실패했습니다. 아이디와 패스워드를 확인해주세요.");
        }
    
        return "message";
    }
                
}

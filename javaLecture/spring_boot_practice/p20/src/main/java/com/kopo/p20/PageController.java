package com.kopo.p20;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
// 여기 있는 이름으로 Model에 담기는 게 있으면, Session의 속성으로 옮겨담는 역할
@SessionAttributes({ "users", "mapSize", "turn", "xNumber", "yNumber" }) 
public class PageController {

    // 세션 초기화를 위한 메소드들
    /* Spring MVC는 요청이 컨트롤러로 들어올 때, 요청 핸들러 메서드에 필요한 모델 데이터를 준비하기 위해 
       먼저 @ModelAttribute가 붙은 메서드들을 자동으로 호출함 */
    @ModelAttribute("users")
    public List<String> users() {
        return new ArrayList<>();
    }

    // @ModelAttribute("turn")
    // public Integer turn() {
    //     return 0;
    // }

    @GetMapping("/")
    public String home(Model model, 
                       @SessionAttribute(value = "users", required = false) List<String> users, 
                       @SessionAttribute(value = "turn", required = false) Integer turn) {
        if (users == null) {
            users = new ArrayList<>();  // 세션에 users가 없을 경우 초기화
        }
        if (turn == null) {
            turn = 0;  // 세션에 turn이 없을 경우 초기화
        }

        model.addAttribute("turn", turn);

        return "home"; // home.html 템플릿을 렌더링
    }

    @GetMapping("/input_user")
    public String inputUser() {
        return "iu"; // iu.html 템플릿을 렌더링
    }

    @PostMapping("/input_action")
    public String inputAction(@RequestParam("playerCount") int playerCount,
                              @RequestParam("mapSize") int mapSize,
                              @RequestParam Map<String, String> requestParams,
                              @SessionAttribute("users") List<String> users,
                              Model model) {
        users.clear();
        for (int i = 1; i <= playerCount; i++) {
            users.add(requestParams.get("user" + i));
        }

        model.addAttribute("mapSize", mapSize);
        model.addAttribute("turn", 0);

        return "redirect:/"; // 홈 페이지로 리다이렉트
    }

    @GetMapping("/game")
    public String game(Model model, 
                       @SessionAttribute("mapSize") int mapSize) {
        Random random = new Random();
        int bombNumber = random.nextInt(mapSize * mapSize);
        int yNumber = bombNumber / mapSize;
        int xNumber = bombNumber % mapSize;

        model.addAttribute("xNumber", xNumber);
        model.addAttribute("yNumber", yNumber);
        model.addAttribute("mapSize", mapSize);

        return "game"; // game.html 템플릿을 렌더링
    }

    @GetMapping("/game_over")
    public String gameOver(Model model, 
                           @SessionAttribute("turn") Integer turn,
                           @SessionAttribute("users") List<String> users,
                           SessionStatus sessionStatus) {
        model.addAttribute("loser", users.get(turn));

        // 게임이 끝나면 세션을 종료합니다.
        sessionStatus.setComplete();

        return "gameover"; // gameover.html 템플릿을 렌더링
    }
}

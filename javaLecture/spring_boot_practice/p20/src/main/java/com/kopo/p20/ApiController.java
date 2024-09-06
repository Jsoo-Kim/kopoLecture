package com.kopo.p20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@SessionAttributes({ "turn" })
public class ApiController {

    @ResponseBody
    @GetMapping("/check")
    public Map<String, Object> check(@RequestParam("x") int x,
                                     @RequestParam("y") int y,
                                     @SessionAttribute("xNumber") int sessionX,
                                     @SessionAttribute("yNumber") int sessionY,
                                     @SessionAttribute("turn") Integer turn,
                                     @SessionAttribute("users") List<String> users,
                                     Model model) {
        Map<String, Object> response = new HashMap<>();

        turn = (turn + 1) % users.size();
        model.addAttribute("turn", turn);

        response.put("turn", turn);
        response.put("currentPlayer", users.get(turn));

        if (sessionX == x && sessionY == y) {
            response.put("result", "bomb");
        } else {
            response.put("result", "safe");
        }

        return response;
    }
}

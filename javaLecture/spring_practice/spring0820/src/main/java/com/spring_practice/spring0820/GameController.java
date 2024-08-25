package com.spring_practice.spring0820;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    private final List<String> participants = new ArrayList<>();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("participants", participants);
        return "addform";
    }

    @RequestMapping(value = "/addParticipant", method = RequestMethod.POST)
    public String addParticipant(@RequestParam("name") String name, Model model) {
        if (name != null && !name.trim().isEmpty()) {
            participants.add(name);
        }
        model.addAttribute("participants", participants);
        return "addform";
    }
    
    @RequestMapping(value = "/participants", method = RequestMethod.GET)
    public String participants(Model model) {
        StringBuilder htmlText = new StringBuilder();
        htmlText.append("<ul>");
        for (String participant : participants) {
            htmlText.append("<li>").append(participant).append("</li>");
        }
        htmlText.append("</ul>");
        model.addAttribute("list", htmlText.toString());
    	return "participants";
    }
}
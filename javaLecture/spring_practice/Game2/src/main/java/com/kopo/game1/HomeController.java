
package com.kopo.game1;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private static final String[] players = {"Player1", "Player2", "Player3", "Player4", "Player5", "Player6"};
    private int currentPlayerIndex = 0;

    @RequestMapping("/")
    public String home(Locale locale, Model model) {
        return "home";
    }

    @RequestMapping("/game")
    public String game(@RequestParam(name = "size", defaultValue = "5") int size,
                       @RequestParam(name = "players", defaultValue = "6") int numPlayers,
                       Model model, HttpSession session) {

        int[][] board = new int[size][size];
        placeMines(board, size);

        model.addAttribute("board", board);
        model.addAttribute("size", size);
        session.setAttribute("players", numPlayers);
        session.setAttribute("currentPlayer", players[currentPlayerIndex]);

        return "game";
    }

    @RequestMapping("/check")
    @ResponseBody
    public String check(@RequestParam("y") int y, @RequestParam("x") int x, HttpSession session) {
        int[][] board = (int[][]) session.getAttribute("board");
        int currentPlayer = currentPlayerIndex;
        String result;

        if (board[y][x] == -1) {
            result = "{"status":"bomb","player":"" + players[currentPlayer] + ""}";
        } else {
            result = "{"status":"safe"}";
        }

        currentPlayerIndex = (currentPlayerIndex + 1) % (int) session.getAttribute("players");
        session.setAttribute("currentPlayer", players[currentPlayerIndex]);

        return result;
    }

    @RequestMapping("/gameover")
    public String gameover(@RequestParam("player") String player, Model model) {
        model.addAttribute("player", player);
        return "gameover";
    }

    private void placeMines(int[][] board, int size) {
        Random rand = new Random();
        int count = 0;
        int mines = size; // Example: number of mines equals the size of the board

        while (count < mines) {
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            if (board[row][col] != -1) {
                board[row][col] = -1;
                count++;
            }
        }
    }
}

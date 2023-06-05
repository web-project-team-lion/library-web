package com.example.webproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class HelloController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        String nickname = (String) session.getAttribute("nickname");

        model.addAttribute("nickname", nickname);
        return "home";
    }

    @GetMapping("/login")
    public String login() {return "login";}

    @RequestMapping("/board")
    public String product() {return "board";}
}
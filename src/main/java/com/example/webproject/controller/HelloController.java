package com.example.webproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {return "home";}

    @GetMapping("/login")
    public String login() {return "login";}

    @RequestMapping("/board")
    public String board() {return "board";}

    @RequestMapping("/boardForm")
    public String boardForm() {return "boardForm";}

    @RequestMapping("/boardDetail")
    public String boardDetail() {return "boardDetail";}
}

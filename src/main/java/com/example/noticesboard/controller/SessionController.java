package com.example.noticesboard.controller;

import com.example.noticesboard.entity.dto.MemberDto;
import com.example.noticesboard.serivce.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SessionController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/register")
    public String register() {
        return "/register";
    }

    @PostMapping("/registerOk")
    public String registerOk(MemberDto memberDto) {
        userService.createAccount(memberDto);
        return "redirect:/login";
    }


}

package com.example.moviesandserialswebsite.controller;

import com.example.moviesandserialswebsite.dao.UserRepository;
import com.example.moviesandserialswebsite.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user-profile")
    public String profile(Principal principal, Model model){
        User username = userRepository.findUserByLogin(principal.getName());
        model.addAttribute("username",username.getLogin());
        return "user-profile";
    }
}

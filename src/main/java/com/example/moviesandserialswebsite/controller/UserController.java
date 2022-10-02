package com.example.moviesandserialswebsite.controller;

import com.example.moviesandserialswebsite.dao.UserRepository;
import com.example.moviesandserialswebsite.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
@Controller
@RequestMapping("/profile")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user-profile")
    public String profile(Principal principal, Model model){
        User username = userRepository.findUserByEmail(principal.getName());
        model.addAttribute("username",username);
        return "profile/user-profile";
    }

    @GetMapping("/edit")
    public String edit(){
        return "profile/edit";
    }

    @GetMapping("/account")
    public String account(){
        return "profile/account";
    }
}

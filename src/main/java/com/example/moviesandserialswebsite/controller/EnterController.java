package com.example.moviesandserialswebsite.controller;

import com.example.moviesandserialswebsite.dao.UserRepository;
import com.example.moviesandserialswebsite.entity.Role;
import com.example.moviesandserialswebsite.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnterController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model userModel) {
        User user = new User();
        userModel.addAttribute("user",user);
        return "registration";
    }

    @PostMapping("/registration")
    public String add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return "redirect:/";
    }
}

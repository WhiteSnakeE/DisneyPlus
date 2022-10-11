package com.example.moviesandserialswebsite.controller;


import com.example.moviesandserialswebsite.dao.MoviesRepository;
import com.example.moviesandserialswebsite.dao.ProfilesRepository;
import com.example.moviesandserialswebsite.dao.UserRepository;
import com.example.moviesandserialswebsite.entity.*;
import com.example.moviesandserialswebsite.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/profile")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MoviesRepository moviesRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ProfilesRepository profilesRepository;

    public String name;

    public String getName() {
      if(name==null){
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          String currentPrincipalName = authentication.getName();
          User user  = userRepository.findUserByEmail(currentPrincipalName);
          name = profilesRepository.currentProfile(user.getId());
      }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @GetMapping("/user-profile")
    public String profile(Principal principal, Model model){
        User username = userRepository.findUserByEmail(principal.getName());
        List<Profile> profiles = username.getProfiles();
        model.addAttribute("username",username);
        model.addAttribute("profiles",profiles);
        return "profile/user-profile";
    }
    @PostMapping("/user-profile")
    public String changeProfile(@RequestParam("profileName") String profileName){
        userService.setProfileName(profileName);
        name = profileName;

        return "redirect:user-profile";
    }

    @GetMapping("/add-profile")
    public String addProfile(){
        return "profile/add-profile";
    }

    @PostMapping("/add-profile")
    public String addProfile(@RequestParam("addProfile") String addProfile,Principal principal){
       User user = userRepository.findUserByEmail(principal.getName());
        profilesRepository.addProfileToUser(user.getId(), addProfile);

        return "redirect:user-profile";
    }

    @GetMapping("/edit")
    public String edit(){
        return "profile/edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("edit") String editProfile,Principal principal){
        String currentProfile = getName();
        return "profile/user-profile";
    }


    @GetMapping("/account")
    public String account(){
        return "profile/account";
    }

    @GetMapping("/watch-list")
    public String watchList(Model model, Principal principal){
        User user  = userRepository.findUserByEmail(principal.getName());
        List<Movies> moviesList = moviesRepository.findMoviesByProfilesIdAndName(user.getId(),name);
        model.addAttribute("Movies",moviesList);
        if(moviesList.isEmpty()){
            List<Movies> movies =
                    moviesRepository.findMoviesByProfilesIdAndName(user.getId(),profilesRepository.currentProfile(user.getId()));
            model.addAttribute("movies",movies);
        }
        return "profile/watch-list";
    }
    @GetMapping("/subscribe")
    private String subPage(Principal principal,Model model){
        User user = userRepository.findUserByEmail(principal.getName());
        Subscribe subscribe = user.getSubscribe();
        if(subscribe.getEnd()!=null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
            String end = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(subscribe.getEnd());
            model.addAttribute("end", end);
        }
        else model.addAttribute("end", null);
        return "profile/subscribe";
    }

    @PostMapping("/subscribe")
    private String buySubscribe(Model model,Principal principal){
        User user = userRepository.findUserByEmail(principal.getName());
        user.getSubscribe().setEnd(LocalDateTime.now().plusMonths(1));
        user.getSubscribe().setStatus(true);
        user.getSubscribe().setStart(LocalDateTime.now());
        userRepository.save(user);
        Subscribe subscribe = user.getSubscribe();
        model.addAttribute("end",subscribe);
        model.addAttribute("start",user.getSubscribe().getStart());
        return "redirect:subscribe";
    }
}

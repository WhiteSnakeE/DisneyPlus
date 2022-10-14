package com.example.moviesandserialswebsite.controller;


import com.example.moviesandserialswebsite.dao.*;
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
import java.util.ArrayList;
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
    @Autowired
    IconRepository iconRepository;
    @Autowired
    IconTypeRepository iconTypeRepository;

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
        Profile profile = profilesRepository.findProfileByIdAndSUserName(username.getId(),getName());
        String icon = profile.getIcon().getIcon();
        model.addAttribute("icon",icon);
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
        return "/first-profile-step-one";
    }

//    @PostMapping("/add-profile")
//    public String addProfile(@RequestParam("addProfile") String addProfile,Principal principal){
//       User user = userRepository.findUserByEmail(principal.getName());
//        profilesRepository.addProfileToUser(user.getId(), addProfile);
//
//        return "redirect:user-profile";
//    }

    @GetMapping("/edit")
    public String edit(Model model){
        List<Icon> iconList = iconRepository.findIconsByIconType(iconTypeRepository.disney());
        model.addAttribute("disney",iconTypeRepository.disney());
        model.addAttribute("icons",iconList);
        model.addAttribute("profile",getName());
        return "profile/edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("profileIcon") String profileIcon,Principal principal){
        User user = userRepository.findUserByEmail(principal.getName());
            Profile profile = profilesRepository.findProfileByIdAndSUserName(user.getId(),getName());
            profile.setIcon(iconRepository.findByName(profileIcon));
            profilesRepository.save(profile);

        return "redirect:edit";
    }


    @GetMapping("/account")
    public String account(Model model,Principal principal) {
        User user = userRepository.findUserByEmail(principal.getName());
        model.addAttribute("user",user);
        return "profile/account";
    }
    @PostMapping("/account")
    public String account(Principal principal,@ModelAttribute User updateUser) {
        User user = userRepository.findUserByEmail(principal.getName());
        user.setLogin(updateUser.getLogin());
        if(!user.getEmail().equals(updateUser.getEmail())){
            user.setEmail(updateUser.getEmail());
            userRepository.save(user);
            return "/login";
        }
        userRepository.save(user);
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

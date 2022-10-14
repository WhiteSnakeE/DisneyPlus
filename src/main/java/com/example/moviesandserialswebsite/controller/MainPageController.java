package com.example.moviesandserialswebsite.controller;
import com.example.moviesandserialswebsite.dao.*;
import com.example.moviesandserialswebsite.entity.Icon;
import com.example.moviesandserialswebsite.entity.Movies;
import com.example.moviesandserialswebsite.entity.Profile;
import com.example.moviesandserialswebsite.entity.User;
import com.example.moviesandserialswebsite.service.UserServiceImpl;
import com.example.moviesandserialswebsite.typeReader.TypeReaderDisney;
import com.example.moviesandserialswebsite.typeReader.TypeReaderMarvel;
import com.example.moviesandserialswebsite.typeReader.TypeReaderNG;
import com.example.moviesandserialswebsite.typeReader.TypeReaderPixar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.List;

@Controller
public class MainPageController {
    @Autowired
    private MoviesRepository moviesRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    TypeReaderDisney typeReaderDisney;
    @Autowired
    TypeReaderPixar typeReaderPixar;
    @Autowired
    TypeReaderMarvel typeReaderMarvel;
    @Autowired
    TypeReaderNG typeReaderNG;
    @Autowired
    IconRepository iconRepository;
    @Autowired
    UserController userController;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ProfilesRepository profilesRepository;
    @Autowired
    IconTypeRepository iconTypeRepository;
    @Autowired
    UserRepository userRepository;
    @GetMapping({"/", ""})
    public String mainPage(Model model, Principal principal) {

        if(principal!=null){
            User user = userRepository.findUserByEmail(principal.getName());
            List<Profile> profiles = user.getProfiles();
            if(profiles.isEmpty()){
                return "first-profile-step-one";
            }
        }

        String s1 = "Nostalgic Movies";
        String s2 = "Recommended for you";
        String s3 = "Hit Movies";
        String s4 = "Action And Adventure";
        String s5 = "Marvel Cinematic: Universe Phase one";
        List<Movies> moviesList1 = moviesRepository.findMoviesByTypesName(s1);
        List<Movies> moviesList2 = moviesRepository.findMoviesByTypesName(s2);
        List<Movies> moviesList3 = moviesRepository.findMoviesByTypesName(s3);
        List<Movies> moviesList4 = moviesRepository.findMoviesByTypesName(s4);
        List<Movies> moviesList5 = moviesRepository.findMoviesByTypesName(s5);
        model.addAttribute("moviesList1", moviesList1);
        model.addAttribute("moviesList2", moviesList2);
        model.addAttribute("moviesList3", moviesList3);
        model.addAttribute("moviesList4", moviesList4);
        model.addAttribute("moviesList5", moviesList5);
        model.addAttribute("type1", s1);
        model.addAttribute("type2", s2);
        model.addAttribute("type3", s3);
        model.addAttribute("type4", s4);
        model.addAttribute("type5", s5);
        return "main";
    }
    @PostMapping("/first-profile-step-one")
    public String addFirstProfileStepOne(@RequestParam("addProfile") String addProfile,
                                         Principal principal){
        User user = userRepository.findUserByEmail(principal.getName());
        profilesRepository.addProfileToUser(user.getId(), addProfile);
        userController.setName(addProfile);
        return "redirect:first-profile-step-two";
    }

    @GetMapping("/first-profile-step-two")
    public String addFirstProfile(Model model,Principal principal){
        List<Icon> iconList = iconRepository.findIconsByIconType(iconTypeRepository.disney());
        model.addAttribute("disney",iconTypeRepository.disney());
        model.addAttribute("icons",iconList);
        model.addAttribute("profile",userController.getName());
        return "first-profile-step-two";
    }
    @PostMapping("/first-profile-step-two")
    public String addFirstProfile(@RequestParam("profileIcon") String profileIcon,Principal principal){
        User user = userRepository.findUserByEmail(principal.getName());
        Profile profile = profilesRepository.findProfileByIdAndSUserName(user.getId(),userController.getName());
        profile.setIcon(iconRepository.findByName(profileIcon));
        profilesRepository.save(profile);
        return "redirect:profile/user-profile";
    }
}

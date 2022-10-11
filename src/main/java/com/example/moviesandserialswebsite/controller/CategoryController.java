package com.example.moviesandserialswebsite.controller;

import com.example.moviesandserialswebsite.dao.*;
import com.example.moviesandserialswebsite.entity.Movies;

import com.example.moviesandserialswebsite.entity.User;
import com.example.moviesandserialswebsite.service.MoviesService;
import com.example.moviesandserialswebsite.service.UserServiceImpl;
import com.example.moviesandserialswebsite.typeReader.TypeReaderDisney;
import com.example.moviesandserialswebsite.typeReader.TypeReaderMarvel;
import com.example.moviesandserialswebsite.typeReader.TypeReaderNG;
import com.example.moviesandserialswebsite.typeReader.TypeReaderPixar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private MoviesRepository moviesRepository;
    @Autowired
    private MoviesService moviesService;
    @Autowired
    private TypesRepository typesRepository;
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
    UserController userController;
    @Autowired
    UserServiceImpl userService;

    @Autowired
    ProfilesRepository profilesRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping({"/", ""})
    public String mainPage(Model model) {
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


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @GetMapping("/disney")
    public String disney(Model model) {
        String firstType =  typesRepository.findByName(typeReaderDisney.findFirstType()).getName();
        String secondType =  typesRepository.findByName(typeReaderDisney.findSecondType()).getName();
        String thirdType =  typesRepository.findByName(typeReaderDisney.findThirdType()).getName();
        String fourthType =  typesRepository.findByName(typeReaderDisney.findFourthType()).getName();
        String fifthType =  typesRepository.findByName(typeReaderDisney.findFifthType()).getName();

        List<Movies> firstTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.disney(),firstType);
        List<Movies> secondTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.disney(),secondType);
        List<Movies> thirdTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.disney(),thirdType);
        List<Movies> fourthTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.disney(),fourthType);
        List<Movies> fifthTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.disney(),fifthType);

        model.addAttribute("firstTypeMovies",firstTypeMovies);
        model.addAttribute("secondTypeMovies",secondTypeMovies);
        model.addAttribute("thirdTypeMovies",thirdTypeMovies);
        model.addAttribute("fourthTypeMovies",fourthTypeMovies);
        model.addAttribute("fifthTypeMovies",fifthTypeMovies);
        model.addAttribute("firstType",firstType);
        model.addAttribute("secondType",secondType);
        model.addAttribute("thirdType",thirdType);
        model.addAttribute("fourthType",fourthType);
        model.addAttribute("fifthType",fifthType);
        return "disney";
    }
    @GetMapping("/pixar")
    public String pixar(Model model) {
        String thirdType =  typesRepository.findByName(typeReaderPixar.findThirdType()).getName();
        String fourthType =  typesRepository.findByName(typeReaderPixar.findFourthType()).getName();
        String fifthType =  typesRepository.findByName(typeReaderPixar.findFifthType()).getName();
        String sixthType =  typesRepository.findByName(typeReaderPixar.findSixthType()).getName();

        List<Movies> thirdTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.pixar(),thirdType);
        List<Movies> fourthTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.pixar(),fourthType);
        List<Movies> fifthTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.pixar(),fifthType);
        List<Movies> sixthTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.pixar(),sixthType);

        model.addAttribute("thirdTypeMovies",thirdTypeMovies);
        model.addAttribute("fourthTypeMovies",fourthTypeMovies);
        model.addAttribute("fifthTypeMovies",fifthTypeMovies);
        model.addAttribute("sixthTypeMovies",sixthTypeMovies);
        model.addAttribute("thirdType",thirdType);
        model.addAttribute("fourthType",fourthType);
        model.addAttribute("fifthType",fifthType);
        model.addAttribute("sixthType",sixthType);
        return "pixar";
    }
    @GetMapping("/marvel")
    public String marvel(Model model) {
        String thirdType =  typesRepository.findByName(typeReaderMarvel.findThirdType()).getName();
        String fourthType =  typesRepository.findByName(typeReaderMarvel.findFourthType()).getName();
        String fifthType =  typesRepository.findByName(typeReaderMarvel.findFifthType()).getName();
        String sixthType =  typesRepository.findByName(typeReaderMarvel.findSixthType()).getName();

        List<Movies> thirdTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.marvel(),thirdType);
        List<Movies> fourthTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.marvel(),fourthType);
        List<Movies> fifthTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.marvel(),fifthType);
        List<Movies> sixthTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.marvel(),sixthType);

        model.addAttribute("thirdTypeMovies",thirdTypeMovies);
        model.addAttribute("fourthTypeMovies",fourthTypeMovies);
        model.addAttribute("fifthTypeMovies",fifthTypeMovies);
        model.addAttribute("sixthTypeMovies",sixthTypeMovies);
        model.addAttribute("thirdType",thirdType);
        model.addAttribute("fourthType",fourthType);
        model.addAttribute("fifthType",fifthType);
        model.addAttribute("sixthType",sixthType);
        return "marvel";
    }

    @GetMapping("/star-wars")
    public String starWars() {
        return "star-wars";
    }

    @GetMapping("/national-geographic")
    public String nationalGeographic(Model model) {
        String thirdType =  typesRepository.findByName(typeReaderNG.findThirdType()).getName();
        String fourthType =  typesRepository.findByName(typeReaderNG.findFourthType()).getName();
        String fifthType =  typesRepository.findByName(typeReaderNG.findFifthType()).getName();


        List<Movies> thirdTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.nationalGeographic(),thirdType);
        List<Movies> fourthTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.nationalGeographic(),fourthType);
        List<Movies> fifthTypeMovies = moviesRepository.findMoviesByCategoryAndTypesName(categoryRepository.nationalGeographic(),fifthType);


        model.addAttribute("thirdTypeMovies",thirdTypeMovies);
        model.addAttribute("fourthTypeMovies",fourthTypeMovies);
        model.addAttribute("fifthTypeMovies",fifthTypeMovies);

        model.addAttribute("thirdType",thirdType);
        model.addAttribute("fourthType",fourthType);
        model.addAttribute("fifthType",fifthType);

        return "national-geographic";
    }

}

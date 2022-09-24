package com.example.moviesandserialswebsite.controller;

import com.example.moviesandserialswebsite.dao.CategoryRepository;
import com.example.moviesandserialswebsite.dao.MoviesRepository;
import com.example.moviesandserialswebsite.dao.TypesRepository;
import com.example.moviesandserialswebsite.entity.Movies;
import com.example.moviesandserialswebsite.entity.Type;
import com.example.moviesandserialswebsite.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private MoviesRepository moviesRepository;
    @Autowired
    private MoviesService moviesService;
    @Autowired
    private TypesRepository typesRepository;
    @Autowired
    CategoryRepository categoryRepository;


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
    @GetMapping("/watch/{id}")
    private String watch(Model model, @PathVariable int id){
        Movies movies = moviesService.getMovie(id);
        model.addAttribute("movie",movies);
        return "watch";

    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @GetMapping("/disney")
    public String disney(Model model) {
        Type bestFriends =  typesRepository.findByName("Recommended for you");
        List<Type> types = new ArrayList<>();
        types.add(bestFriends);
        List<Movies> bestFriendsMovies = moviesRepository.findMoviesByCategoryAndTypesIn(categoryRepository.disney(),types);
        System.out.println( "fdff" + bestFriendsMovies.toString());
        model.addAttribute("bestFriendsMovies",bestFriendsMovies);
        model.addAttribute("bestFriends",bestFriends.getName());
        return "disney";
    }
    @GetMapping("/pixar")
    public String pixar() {
        return "pixar";
    }
    @GetMapping("/star-wars")
    public String starWars() {
        return "star-wars";
    }
    @GetMapping("/marvel")
    public String marvel(Model model) {
        Type bestFriends = typesRepository.findByName("Recommended for you");
        List<Movies> bestFriendsMovies = moviesRepository.findMoviesByCategory(categoryRepository.marvel());
        System.out.println("fdff" + bestFriendsMovies);
        model.addAttribute("bestFriendsMovies",bestFriendsMovies);
        model.addAttribute("bestFriends",bestFriends);
        return "marvel";
    }
    @GetMapping("/national-geographic")
    public String nationalGeographic() {
        return "national-geographic";
    }

}

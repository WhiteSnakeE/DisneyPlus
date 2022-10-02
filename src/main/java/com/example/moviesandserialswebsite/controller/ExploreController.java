package com.example.moviesandserialswebsite.controller;

import com.example.moviesandserialswebsite.dao.CategoryRepository;
import com.example.moviesandserialswebsite.dao.MoviesRepository;
import com.example.moviesandserialswebsite.entity.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/explore")
public class ExploreController {

    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/search")
    public String search(){
        return "explore/search";
    }
    @PostMapping("/search")
    public String search(Model model, @RequestParam String text){
       List<Movies> moviesList =  moviesRepository.findByNameContaining(text);
       model.addAttribute("Movies",moviesList);
        return "explore/search";
    }

    @GetMapping("/through-the-decades")
    public String throughTheDecades(Model model){
        List<Movies> movies1940s  =
                moviesRepository.findMoviesByCategoryAndYearBetween(categoryRepository.disney(),1939,1950);
        List<Movies> movies2010s  =
                moviesRepository.findMoviesByCategoryAndYearBetween(categoryRepository.disney(),2009,2020);
        model.addAttribute("Movies1940s",movies1940s);
        model.addAttribute("Movies2010s",movies2010s);
        return "explore/through-the-decades";
    }
}

package com.example.moviesandserialswebsite.controller;

import com.example.moviesandserialswebsite.dao.GenresRepository;
import com.example.moviesandserialswebsite.dao.MoviesRepository;
import com.example.moviesandserialswebsite.entity.Genre;
import com.example.moviesandserialswebsite.entity.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MoviesAndSerialsController {
    @Autowired
    MoviesRepository  moviesRepository;
    @Autowired
    GenresRepository genresRepository;


//    @GetMapping("/movies")
//    public String movies(Model model){
//        List<Genre> genres = genresRepository.findAll();
//        List<Movies> allMovies = moviesRepository.findAll();
//        model.addAttribute("genres",genres);
//        model.addAttribute("Movies",allMovies);
//        return "movies";
//    }
    @GetMapping("/movies")
    public String movies(Model model, @RequestParam(name ="genre",required = false) String name){
        List<Movies> moviesByGenre;
        if( name!=null && name.equals("ALL MOVIES A-Z")){
            moviesByGenre = moviesRepository.findMoviesByGenres(genresRepository.findGenreByName(name));
            Collections.sort(moviesByGenre);
        }
        else if (name!=null &&  name.length() > 0) {
            moviesByGenre = moviesRepository.findMoviesByGenres(genresRepository.findGenreByName(name));
        } else {
            moviesByGenre = moviesRepository.findAll();
        }
        List<Genre> genres = genresRepository.findAll();
        model.addAttribute("MoviesByGenre",moviesByGenre);
        model.addAttribute("genres",genres);
        return "movies";
    }
    @GetMapping("/serials")
    public String serials(){
        return "serials";
    }
}

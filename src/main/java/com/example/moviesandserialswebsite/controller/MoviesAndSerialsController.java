package com.example.moviesandserialswebsite.controller;

import com.example.moviesandserialswebsite.dao.GenresRepository;
import com.example.moviesandserialswebsite.dao.MoviesRepository;
import com.example.moviesandserialswebsite.dao.ProfilesRepository;
import com.example.moviesandserialswebsite.dao.UserRepository;
import com.example.moviesandserialswebsite.entity.Genre;
import com.example.moviesandserialswebsite.entity.Movies;
import com.example.moviesandserialswebsite.entity.User;
import com.example.moviesandserialswebsite.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;

@Controller
public class MoviesAndSerialsController {
    @Autowired
    MoviesRepository  moviesRepository;
    @Autowired
    GenresRepository genresRepository;
    @Autowired
    private MoviesService moviesService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserController userController;
    @Autowired
    ProfilesRepository profilesRepository;

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

    @GetMapping("/movies/watch/{id}")
    private String watch(Model model, @PathVariable int id,Principal principal){
        User user = userRepository.findUserByEmail(principal.getName());
        boolean ifPresent = profilesRepository.ifUserHasThisMovie(user.getId(),userController.getName(),id);
        Movies movies = moviesService.getMovie(id);
        model.addAttribute("ifPresent",ifPresent);
        model.addAttribute("movie",movies);
        return "watch";
    }
    @PostMapping("/movies/watch/{id}")
    public String addToWatchList(@PathVariable int id, Principal principal){
        User user = userRepository.findUserByEmail(principal.getName());
        moviesRepository.addMovieToProfile(user.getId(), userController.getName(), id);
        return "redirect:{id}";
    }
}

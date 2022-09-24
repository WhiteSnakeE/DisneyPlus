package com.example.moviesandserialswebsite.service;

import com.example.moviesandserialswebsite.entity.Movies;

import java.util.List;

public interface MoviesService {

    public List<Movies> getAllMovies();

    public List<Movies> getAllMoviesByType();

//    public void saveMovies(Movies movie);
//
    public Movies getMovie(int id);
//
//    public void deleteMovie(int id);
}

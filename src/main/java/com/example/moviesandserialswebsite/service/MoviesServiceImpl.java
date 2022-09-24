package com.example.moviesandserialswebsite.service;

import com.example.moviesandserialswebsite.dao.MoviesRepository;
import com.example.moviesandserialswebsite.entity.Movies;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Repository
public class MoviesServiceImpl implements MoviesService {
    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public List<Movies> getAllMovies() {
        return moviesRepository.findAll();
    }

    @Override
    public List<Movies> getAllMoviesByType() {

        return null;
    }
    @Override
    public Movies getMovie(int id) {
        Movies movies = null;
        Optional<Movies> optional = moviesRepository.findById(id);
        if(optional.isPresent()){
            movies = optional.get();
        }
        return movies;
    }

//    @Override
//    public void saveMovies(Movies movie) {
//
//    }
//
//    @Override
//    public Movies getMovie(int id) {
//        return null;
//    }
//
//    @Override
//    public void deleteMovie(int id) {
//
//    }
}

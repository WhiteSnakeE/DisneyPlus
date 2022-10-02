package com.example.moviesandserialswebsite.dao;

import com.example.moviesandserialswebsite.entity.Category;
import com.example.moviesandserialswebsite.entity.Genre;
import com.example.moviesandserialswebsite.entity.Movies;
import com.example.moviesandserialswebsite.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.TreeSet;

public interface MoviesRepository extends JpaRepository<Movies,Integer> {
//    @Query("select u from Movies u where u.id in(select movies_id from movies_has_type where type_id = :id)")
    List<Movies> findMoviesByTypesIn(List<Type> types);
    List<Movies> findMoviesByTypesName(String name);
//    List<Movies> findMoviesByTypesName(Type name);
    List<Movies> findMoviesByCategory(Category category);
    List<Movies> findMoviesByCategoryAndTypesIn(Category category, List<Type> types);
    List<Movies> findMoviesByCategoryAndTypesName(Category category, String type);
    List<Movies> findMoviesByGenres(Genre genre);
    List<Movies> findMoviesByCategoryAndYearBetween(Category category,int start, int end);
    List<Movies> findByNameContaining(String name);


}


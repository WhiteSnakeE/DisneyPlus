package com.example.moviesandserialswebsite.dao;

import com.example.moviesandserialswebsite.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepository extends JpaRepository<Genre,Integer> {
    Genre findGenreByName(String name);
}

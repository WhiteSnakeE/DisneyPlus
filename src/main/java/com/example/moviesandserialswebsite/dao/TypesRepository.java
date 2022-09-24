package com.example.moviesandserialswebsite.dao;

import com.example.moviesandserialswebsite.entity.Movies;
import com.example.moviesandserialswebsite.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypesRepository extends JpaRepository<Type,Integer> {
    Type findByName(String name);
    Integer findIdByName(String name);
}

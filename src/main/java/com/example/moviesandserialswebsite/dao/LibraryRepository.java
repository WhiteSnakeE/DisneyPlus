package com.example.moviesandserialswebsite.dao;

import com.example.moviesandserialswebsite.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library,Integer> {
}

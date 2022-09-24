package com.example.moviesandserialswebsite.dao;

import com.example.moviesandserialswebsite.entity.Serials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerialsRepository extends JpaRepository<Serials,Integer> {
}

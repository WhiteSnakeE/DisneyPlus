package com.example.moviesandserialswebsite.dao;

import com.example.moviesandserialswebsite.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository  extends JpaRepository<Category,Integer> {
    @Query("select u from Category u where u.company = 'Disney'")
    Category disney();
    @Query("select u from Category u where u.company = 'Marvel'")
    Category marvel();
}

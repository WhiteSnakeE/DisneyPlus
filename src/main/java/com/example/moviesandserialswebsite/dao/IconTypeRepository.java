package com.example.moviesandserialswebsite.dao;

import com.example.moviesandserialswebsite.entity.Icon;
import com.example.moviesandserialswebsite.entity.IconType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IconTypeRepository extends JpaRepository<IconType,Integer> {
    @Query(value = "select type from icon_type where type ='Disney';",nativeQuery = true)
    String disney();
}

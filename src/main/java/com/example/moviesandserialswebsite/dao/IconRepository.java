package com.example.moviesandserialswebsite.dao;

import com.example.moviesandserialswebsite.entity.Icon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IconRepository extends JpaRepository<Icon,Integer> {
    @Query(value = "select name from icon;",nativeQuery = true)
    List<Icon> findAllIconName();
    @Query(value = "select * from icon where name = :name limit 1;",nativeQuery = true)
    Icon findByName(String name);
    @Query(value = "select * from icon where icon_type_id in(select id from icon_type where type = 'Disney');"
            ,nativeQuery = true)
    List<Icon> findIconsByIconType(String name);
}

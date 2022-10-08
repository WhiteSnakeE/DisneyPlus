package com.example.moviesandserialswebsite.dao;

import com.example.moviesandserialswebsite.entity.Profile;
import com.example.moviesandserialswebsite.entity.ProfileID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProfilesRepository extends CrudRepository<Profile, ProfileID> {

    @Query(value = "select name from profile where user_id = :id  limit 1;",nativeQuery = true)
    String currentProfile(int id);

    @Modifying
    @Transactional
    @Query(value = "insert into profile values(:id, :profileName);",nativeQuery = true)
    void addProfileToUser(int id, String profileName);
}

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

    @Query(value = "select * from profile where user_id = :id and name = :name limit 1;",nativeQuery = true)
    Profile findProfileByIdAndSUserName(int id,String name);

    @Modifying
    @Transactional
    @Query(value = "insert into profile values(:id, :profileName,null);",nativeQuery = true)
    void addProfileToUser(int id, String profileName);

    @Query(value = "SELECT CASE WHEN COUNT(*) = 1 THEN 'true' ELSE 'false' END from profile_has_movies " +
            "where profile_user_id = :userId and profile_name = :profileName\n" +
            " and movies_id in(select id from movies where id = :movieId);",nativeQuery = true)
    boolean ifUserHasThisMovie( int userId, String profileName, int movieId);

}

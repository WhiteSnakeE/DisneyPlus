package com.example.moviesandserialswebsite.dao;

import com.example.moviesandserialswebsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByLogin(String login);
    User findUserByEmail(String email);
}

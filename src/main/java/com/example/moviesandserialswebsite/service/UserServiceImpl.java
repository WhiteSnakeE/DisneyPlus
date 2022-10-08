package com.example.moviesandserialswebsite.service;

import com.example.moviesandserialswebsite.dao.ProfilesRepository;
import com.example.moviesandserialswebsite.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfilesRepository profilesRepository;

    String profileName;


    @Override
    public void setCurrentProfile(String profileName) {
       setProfileName(profileName);
    }


    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}

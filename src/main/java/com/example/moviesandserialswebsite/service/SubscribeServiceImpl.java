package com.example.moviesandserialswebsite.service;

import com.example.moviesandserialswebsite.dao.SubscribeRepository;
import com.example.moviesandserialswebsite.dao.UserRepository;
import com.example.moviesandserialswebsite.entity.Subscribe;
import com.example.moviesandserialswebsite.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscribeServiceImpl {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SubscribeRepository subscribeRepository;

    @Scheduled(fixedDelay = 1000)
    public void checkSubscribe() {
        List<Subscribe> subscribeList = subscribeRepository.findAll();
        for (Subscribe sub : subscribeList) {
            if (sub.isStatus()) {
                if (LocalDateTime.now().compareTo(sub.getEnd()) >= 0) {
                    sub.setStart(null);
                    sub.setStatus(false);
                    sub.setEnd(null);
                    subscribeRepository.save(sub);
                }
            }
        }
    }
}

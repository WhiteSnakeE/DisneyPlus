package com.example.moviesandserialswebsite.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class ProfileID implements Serializable {

    private Integer userId;


    private String name;

    public ProfileID(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}

package com.example.moviesandserialswebsite.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Setter
@Getter
//@ToString
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "company")
    private String company;
    @OneToMany(mappedBy = "category")
    private List<Serials> serials;
    @OneToMany(mappedBy = "category")
    private List<Movies> movies;
    public Category(String name) {
        this.company = name;
    }

}

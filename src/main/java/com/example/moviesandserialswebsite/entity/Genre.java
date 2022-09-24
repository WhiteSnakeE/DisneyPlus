package com.example.moviesandserialswebsite.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "genres")
    private List<Movies> movies;
    @ManyToMany(mappedBy = "genres")
    private List<Serials> serials;

    public Genre(String name) {
        this.name = name;
    }
}

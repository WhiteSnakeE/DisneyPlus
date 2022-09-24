package com.example.moviesandserialswebsite.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "types")
    @ToString.Exclude
    private List<Movies> movies;
    @ManyToMany(mappedBy = "types")
    @ToString.Exclude
    private List<Serials> serials;

    public Type(String name) {
        this.name = name;
    }
}

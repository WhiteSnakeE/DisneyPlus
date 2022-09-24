package com.example.moviesandserialswebsite.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
@Getter
@Setter

@NoArgsConstructor
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "year")
    private int year;
    @Column(name = "details")
    private String details;
    @Column(name = "age")
    private String age;
    @Column(name = "time")
    private String time;
    @Column(name = "logo")
    private String logo;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "movie")
    @ToString.Exclude
    private List<Library> libraries;
    @ManyToMany
    @JoinTable(name = "movies_has_genre",joinColumns = @JoinColumn(name = "movies_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ToString.Exclude
    private List<Genre> genres;
    @ManyToMany
    @JoinTable(name = "movies_has_type",joinColumns = @JoinColumn(name = "movies_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    @ToString.Exclude
    private List<Type> types;



    public Movies(String name, int year, String details, String age, String time, String logo, Category category) {
        this.name = name;
        this.year = year;
        this.details = details;
        this.age = age;
        this.time = time;
        this.logo = logo;
        this.category = category;
    }
}

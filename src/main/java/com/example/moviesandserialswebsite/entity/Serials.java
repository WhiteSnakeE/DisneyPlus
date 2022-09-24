package com.example.moviesandserialswebsite.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "serials")
@Getter
@Setter
//@ToString
@NoArgsConstructor
public class Serials {
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
    @Column(name = "seasons")
    private int seasons;
    @Column(name = "episodes")
    private int episodes;
    @Column(name = "logo")
    private String logo;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "serial")
    @ToString.Exclude
    private List<Library> libraries;
    @ManyToMany
    @JoinTable(name = "serials_has_genre",joinColumns = @JoinColumn(name = "serials_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ToString.Exclude
    private List<Genre> genres;
    @ManyToMany
    @JoinTable(name = "serials_has_type",joinColumns = @JoinColumn(name = "serials_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    @ToString.Exclude
    private List<Type> types;

    public Serials(String name, int year, String details, int seasons, int episodes, String logo) {
        this.name = name;
        this.year = year;
        this.details = details;
        this.seasons = seasons;
        this.episodes = episodes;
        this.logo = logo;
    }

}

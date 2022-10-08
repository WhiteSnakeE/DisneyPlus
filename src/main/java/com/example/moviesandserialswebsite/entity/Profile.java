package com.example.moviesandserialswebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.*;
import java.util.List;

@Entity
@IdClass(ProfileID.class)
@Table(name = "profile")
@NoArgsConstructor
@AllArgsConstructor
@Projection(name = "deadline", types = { ProfileID.class })
public class Profile {
    @Id
    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "id")})
    private User userId;
    @Id
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "profiles")
    private List<Movies> movies;


    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movies> getMovies() {
        return movies;
    }

    public void setMovies(List<Movies> movies) {
        this.movies = movies;
    }
}

package com.example.moviesandserialswebsite.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Setter
@Getter
@ToString
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "login")
    @ToString.Exclude
    private String login;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "icon")
    private String icon;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Subscribe subscribe;
    @OneToMany(mappedBy = "userId")
    List<Profile> profiles;
    public User() {
    }

    public User(String login, String email, String password, String icon, Role role) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.icon = icon;
        this.role = role;
    }


//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", login='" + login + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", icon='" + icon + '\'' +
//                ", role=" + role +
//                ", subscribe=" + subscribe +
//                '}';
//    }
}

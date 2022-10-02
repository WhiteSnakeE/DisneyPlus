package com.example.moviesandserialswebsite.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "subscribe")
@Setter
@Getter
@ToString
public class Subscribe {
    @Id
    @Column(name = "users_id")
    private int users_id;
    @Column(name = "status")
    private boolean status;
    @Column(name = "start")
    private LocalDateTime start;
    @Column(name = "end")
    private LocalDateTime end;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    public Subscribe() {
    }
    public Subscribe(int users_id, boolean status, LocalDateTime start, LocalDateTime end) {
        this.users_id = users_id;
        this.status = status;
        this.start = start;
        this.end = end;
    }

}

package com.example.moviesandserialswebsite.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "icon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Icon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    @ToString.Exclude
    String icon;
    @ManyToOne
    @JoinColumn(name = "icon_type_id")
    private IconType iconType;
    @OneToMany(mappedBy = "icon")
    List<Profile> profiles ;

}

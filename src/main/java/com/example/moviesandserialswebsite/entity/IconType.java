package com.example.moviesandserialswebsite.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "icon_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class IconType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "type")
    String type;
    @OneToMany(mappedBy = "iconType")
    @ToString.Exclude
    List<Icon> iconList ;

}

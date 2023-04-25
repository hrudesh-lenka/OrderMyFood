package com.restaurantsearchservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private Double distance;
    private String cuisine;
    private Double budget;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItems> menuItems;
}


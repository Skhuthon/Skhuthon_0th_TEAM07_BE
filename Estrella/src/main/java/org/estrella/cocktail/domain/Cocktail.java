package org.estrella.cocktail.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "korName")
    private String korName;
    @Column(name = "engName")
    private String engName;
    private String location;
    private String ingredients;
    private String garnish;
    @Column(length = 1000)
    private String preparation;
//    private String image;
}

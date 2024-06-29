package org.estrella.cocktail.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "korName")
    @Schema(description = "칵테일의 한글 이름", example = "더 해피 플레이스")
    private String korName;
    @Schema(description = "칵테일의 영어 이름", example = "The Happy Place")
    @Column(name = "engName")
    private String engName;
    private String location;
    private String ingredients;
    private String garnish;
    @Column(length = 1000)
    private String preparation;
//    private String image;
    private String color;
}

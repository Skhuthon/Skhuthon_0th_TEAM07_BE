package org.estrella.category.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.estrella.cocktail.domain.Cocktail;

import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Cocktail> cocktails;
}


package org.estrella.category.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
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
}

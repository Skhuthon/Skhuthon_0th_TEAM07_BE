package org.estrella.category.domain;

import org.estrella.cocktail.domain.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT c.name FROM Category c")
    List<String> findAllCategoryNames();



}

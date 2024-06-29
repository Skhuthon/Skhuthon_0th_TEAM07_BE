package org.estrella.cocktail.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CocktailRepository extends JpaRepository<Cocktail, Integer> {
    @Query("SELECT c FROM Cocktail c WHERE c.korName LIKE %:name% OR c.engName LIKE %:name%")
    List<Cocktail> findByKorNameContainingOrEngNameContaining(@Param("name") String name);
}

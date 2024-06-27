package org.estrella.cocktail.application;

import org.estrella.cocktail.domain.Cocktail;
import org.estrella.cocktail.domain.CocktailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocktailService {


    private final CocktailRepository cocktailRepository;

    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public List<Cocktail> getAllCocktails() {
        return cocktailRepository.findAll();
    }

    public Cocktail getCocktailById(Integer id) {
        return cocktailRepository.findById(id).orElse(null);
    }

}
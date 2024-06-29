package org.estrella.cocktail.application;

import org.estrella.cocktail.api.dto.response.CocktailInfoResDto;
import org.estrella.cocktail.api.dto.response.CocktailListResDto;
import org.estrella.cocktail.domain.Cocktail;
import org.estrella.cocktail.domain.CocktailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CocktailService {


    private final CocktailRepository cocktailRepository;

    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public CocktailListResDto cocktailFindAll() {
        List<Cocktail> cocktails = cocktailRepository.findAll();
        List<CocktailInfoResDto> cocktailInfoResDtoList = cocktails.stream()
                .map(CocktailInfoResDto::from)
                .toList();
        return CocktailListResDto.from(cocktailInfoResDtoList);
    }


    public CocktailInfoResDto getCocktailById(Integer id) {
        Cocktail cocktail = cocktailRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return CocktailInfoResDto.from(cocktail);
    }

    public List<Integer> findCocktailIdsByName(String name) {
        List<Cocktail> cocktails = cocktailRepository.findByKorNameContainingOrEngNameContaining(name);
        return cocktails.stream()
                .map(Cocktail::getId)
                .collect(Collectors.toList());
    }
}
package org.estrella.cocktail.application;

import org.estrella.cocktail.api.dto.response.CocktailInfoResDto;
import org.estrella.cocktail.api.dto.response.CocktailListResDto;
import org.estrella.cocktail.domain.Cocktail;
import org.estrella.cocktail.domain.CocktailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CocktailService {


    private final CocktailRepository cocktailRepository;

    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public CocktailListResDto cocktailFindAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cocktail> cocktailPage = cocktailRepository.findAll(pageable);
        List<CocktailInfoResDto> cocktailInfoResDtoList = cocktailPage.stream()
                .map(CocktailInfoResDto::from)
                .collect(Collectors.toList());
        return CocktailListResDto.builder()
                .cocktails(cocktailInfoResDtoList)
                .currentPage(cocktailPage.getNumber())
                .totalPages(cocktailPage.getTotalPages())
                .totalItems(cocktailPage.getTotalElements())
                .build();
    }


    public CocktailInfoResDto getCocktailById(Integer id) {
        Cocktail cocktail = cocktailRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return CocktailInfoResDto.from(cocktail);
    }

    public List<Integer> findCocktailIdsByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cocktail> cocktailPage = cocktailRepository.findByKorNameContainingOrEngNameContaining(name, pageable);
        return cocktailPage.stream()
                .map(Cocktail::getId)
                .collect(Collectors.toList());
    }
}
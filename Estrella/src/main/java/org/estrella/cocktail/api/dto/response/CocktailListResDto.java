package org.estrella.cocktail.api.dto.response;

import lombok.Builder;
import org.estrella.cocktail.domain.Cocktail;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.BindParam;

import java.util.List;

@Builder
public record CocktailListResDto(
        List<CocktailInfoResDto> cocktails,
        int currentPage,
        int totalPages,
        long totalItems
) {
    public static CocktailListResDto from(Page<CocktailInfoResDto> cocktailPage){
        return CocktailListResDto.builder()
                .cocktails(cocktailPage.getContent())
                .currentPage(cocktailPage.getNumber())
                .totalPages(cocktailPage.getTotalPages())
                .totalItems(cocktailPage.getTotalElements())
                .build();
    }
}


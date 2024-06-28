package org.estrella.cocktail.api.dto.response;

import lombok.Builder;
import org.estrella.cocktail.domain.Cocktail;
import org.springframework.web.bind.annotation.BindParam;

import java.util.List;

@Builder
public record CocktailListResDto(
        List<CocktailInfoResDto> cocktails
) {
    public static CocktailListResDto from(List<CocktailInfoResDto> cocktails){
        return CocktailListResDto.builder()
                .cocktails(cocktails)
                .build();
    }
}


package org.estrella.cocktail.api.dto.response;

import lombok.Builder;
import org.estrella.cocktail.domain.Cocktail;

@Builder
public record CocktailInfoResDto(
        Integer id,
        String korName,
        String engName,
        String location,
        String ingredients,
        String garnish,
        String content,
        String image,
        String color,
        String categoryName,
        Integer abv


) {
    public static CocktailInfoResDto from(Cocktail cocktail){
        return CocktailInfoResDto.builder()
                .id(cocktail.getId())
                .korName(cocktail.getKorName())
                .engName(cocktail.getEngName())
                .location(cocktail.getLocation())
                .ingredients(cocktail.getIngredients())
                .garnish(cocktail.getGarnish())
                .content(cocktail.getContent())
                .image(cocktail.getImage())
                .color(cocktail.getColor())
                .abv(cocktail.getAbv())
                .categoryName(cocktail.getCategoryName())
                .build();
    }
}

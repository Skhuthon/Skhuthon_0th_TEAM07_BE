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
        String preparation,
        String image,
        String color


) {
    public static CocktailInfoResDto from(Cocktail cocktail){
        return CocktailInfoResDto.builder()
                .id(cocktail.getId())
                .korName(cocktail.getKorName())
                .engName(cocktail.getEngName())
                .location(cocktail.getLocation())
                .ingredients(cocktail.getIngredients())
                .garnish(cocktail.getGarnish())
                .preparation(cocktail.getPreparation())
//                .image(cocktail.getImage())
            //    .color(cocktail.getColor())
                .build();
    }
}

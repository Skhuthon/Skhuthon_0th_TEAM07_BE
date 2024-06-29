package org.estrella.category.application;

import org.estrella.cocktail.api.dto.response.CocktailInfoResDto;
import org.estrella.cocktail.domain.CocktailRepository;
import org.estrella.category.domain.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CocktailRepository cocktailRepository;

    public CategoryService(CategoryRepository categoryRepository, CocktailRepository cocktailRepository) {
        this.categoryRepository = categoryRepository;
        this.cocktailRepository = cocktailRepository;
    }

    public List<String> getAllCategoryNames() {
        return categoryRepository.findAllCategoryNames();
    }
    public List<Integer> getAllCategoryIds() {
        return categoryRepository.findAllCategoryIds();
    }

    public List<CocktailInfoResDto> getCocktailsByCategoryId(Integer categoryId) {
        return cocktailRepository.findByCategoryCategoryId(categoryId).stream()
                .map(CocktailInfoResDto::from)
                .collect(Collectors.toList());
    }
}

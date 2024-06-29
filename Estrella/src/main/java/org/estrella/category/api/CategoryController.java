package org.estrella.category.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.estrella.category.application.CategoryService;
import org.estrella.cocktail.api.dto.response.CocktailInfoResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cocktails/categories")
@Tag(name = "CategoryController", description = "CategoryController API 목록")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(
            summary = "모든 칵테일 카테고리 조회",
            description = "모든 칵테일 카테고리를 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = List.class),
                                    examples = @ExampleObject(
                                            name = "예제 데이터",
                                            value = "[\"All Day Type Cocktail\", \"Before Dinner Cocktail\", \"After Dinner Cocktail\"]"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<List<String>> getAllCategoryNames() {
        List<String> categoryNames = categoryService.getAllCategoryNames();
        return new ResponseEntity<>(categoryNames, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    @Operation(
            summary = "카테고리 ID로 칵테일 조회",
            description = "카테고리 ID로 칵테일을 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = List.class),
                                    examples = @ExampleObject(
                                            name = "예제 데이터",
                                            value = "[{\"id\": 1, \"korName\": \"모히토\", \"engName\": \"Mojito\", \"location\": \"쿠바\", \"ingredients\": \"럼, 라임, 민트\", \"garnish\": \"민트 잎\", \"content\": \"민트잎에 라임즙을 섞고 럼을 넣고 소다를 넣는다\", \"image\": \"image_url\"}]"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<List<CocktailInfoResDto>> getCocktailsByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        List<CocktailInfoResDto> cocktails = categoryService.getCocktailsByCategoryId(categoryId);
        return new ResponseEntity<>(cocktails, HttpStatus.OK);
    }
}

package org.estrella.cocktail.api;

import org.estrella.cocktail.api.dto.response.CocktailInfoResDto;
import org.estrella.cocktail.api.dto.response.CocktailListResDto;
import org.estrella.cocktail.application.CocktailService;
import org.estrella.cocktail.domain.Cocktail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cocktails")
public class CocktailController {


    private final CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping
    public ResponseEntity<CocktailListResDto> getAllCocktails() {
        CocktailListResDto cocktailListResDto = cocktailService.cocktailFindAll();
        return new ResponseEntity<>(cocktailListResDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCocktailById(@PathVariable Integer id) {
        CocktailInfoResDto cocktailInfoResDto = cocktailService.getCocktailById(id);
        if (cocktailInfoResDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("칵테일이 존재하지 않습니다!");
        }
        return ResponseEntity.ok(cocktailInfoResDto);
    }


}
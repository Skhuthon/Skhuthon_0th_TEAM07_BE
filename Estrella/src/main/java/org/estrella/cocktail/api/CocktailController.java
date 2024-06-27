package org.estrella.cocktail.api;

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
    public ResponseEntity<List<Cocktail>> getAllCocktails() {
        List<Cocktail> cocktails = cocktailService.getAllCocktails();
        return new ResponseEntity<>(cocktails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cocktail> getCocktailById(@PathVariable Integer id) {
        Cocktail cocktail = cocktailService.getCocktailById(id);
        if (cocktail != null) {
            return new ResponseEntity<>(cocktail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
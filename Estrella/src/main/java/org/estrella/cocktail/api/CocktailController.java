package org.estrella.cocktail.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "CocktailController", description = "CocktailController API 목록")
public class CocktailController {


    private final CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping
    @Operation(
            summary = "모든 칵테일 목록 조회",
            description = "모든 칵테일 목록을 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CocktailListResDto.class),
                                    examples = @ExampleObject(
                                            name = "예제 데이터",
                                            value = "{\"cocktails\": ["
                                                    + "{\"id\": 1, \"korName\": \"모히토\", \"engName\": \"Mojito\", \"location\": \"쿠바\", \"ingredients\": \"럼, 라임, 민트\", \"garnish\": \"민트 잎\", \"preparation\": \"민트잎에 라임즙을 섞고 럼을 넣고 소다를 넣는다\", \"image\": \"image_url_here\"},"
                                                    + "{\"id\": 2, \"korName\": \"마가리타\", \"engName\": \"Margarita\", \"location\": \"멕시코\", \"ingredients\": \"테킬라, 라임, 트리플세크 시럽\", \"garnish\": \"라임 슬라이스\", \"preparation\": \"얼음으로 재료를 흔든 후 유리컵에 담는다\", \"image\": \"image_url_here\"}"
                                                    + "]}"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<CocktailListResDto> getAllCocktails(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        CocktailListResDto cocktailListResDto = cocktailService.cocktailFindAll(page, size);
        return new ResponseEntity<>(cocktailListResDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "특정 ID로 칵테일 조회",
            description = "특정 ID로 칵테일을 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CocktailInfoResDto.class),
                                    examples = @ExampleObject(
                                            name = "예제 데이터",
                                            value = "{\"id\": 1, \"korName\": \"모히토\", \"engName\": \"Mojito\", \"location\": \"쿠바\", \"ingredients\": \"럼, 라임, 민트\", \"garnish\": \"민트 잎\", \"preparation\": \"민트잎에 라임즙을 섞고 럼을 넣고 소다를 넣는다.\", \"image\": \"image_url_here\"}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "칵테일이 존재하지 않습니다.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "예제 데이터",
                                            value = "{\"message\": \"칵테일이 존재하지 않습니다!\"}"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<?> getCocktailById(@PathVariable Integer id) {
        CocktailInfoResDto cocktailInfoResDto = cocktailService.getCocktailById(id);
        if (cocktailInfoResDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("칵테일이 존재하지 않습니다!");
        }
        return ResponseEntity.ok(cocktailInfoResDto);
    }

    @GetMapping("/search")
    @Operation(
            summary = "칵테일 이름으로 검색",
            description = "한글 이름이나 영어 이름으로 칵테일을 검색하여 ID를 반환합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = List.class),
                                    examples = @ExampleObject(
                                            name = "예제 데이터",
                                            value = "[1, 2]"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "칵테일을 찾을 수 없습니다.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "예제 데이터",
                                            value = "{\"message\": \"칵테일을 찾을 수 없습니다.\"}"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<?> findCocktailIdsByName(
            @Parameter(description = "검색할 칵테일의 영어 이름 또는 한글 이름의 일부", example = "모히 or Mo") @RequestParam(name= "name") String name,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        List<Integer> ids = cocktailService.findCocktailIdsByName(name, page, size);
        if (ids.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"칵테일을 찾을 수 없습니다.\"}");
        }
        return ResponseEntity.ok(ids);
    }

}
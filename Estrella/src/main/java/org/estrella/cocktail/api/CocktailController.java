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
                                                    + "{\"id\": 1, \"korName\": \"아페롤 스프릿\", \"engName\": \"Aperol Spritz\", \"location\": \"이탈리아\", \"ingredients\": \"3온스 프로세코, 2온스 아페롤, 스플래시 소다수(약 1온스)\", \"garnish\": \"오렌지 슬라이스\", \"content\": \"1. 와인 잔에 얼음을 채우고 잔이 차가워질 때까지 약 30초 동안 냉장 보관합니다. \n" +
                                                    "2. 프로세코, 아페롤, 소다수를 넣고 가볍게 저어 섞습니다. \n" +
                                                    "3. 오렌지 슬라이스로 장식합니다.\", \"image\": \"https://hips.hearstapps.com/hmg-prod/images/aperol-spritz-lead-64763b54778bc.jpg?crop=1xw:1xh;center,top&resize=980:*\", \"color\": \"red\", \"category\": \"Aperitif Cocktail\", \"abv\": 8},"
                                                    + "{\"id\": 2, \"korName\": \"에이비에이션\", \"engName\": \"Aviation\", \"location\": \"뉴욕\", \"ingredients\": \"진 2온스, 마라스키노 리큐어 1/4온스, 크렘 드 바이올렛 리큐어 1/4온스, 레몬즙 1/2온스\", \"garnish\": \"구운 레몬 껍질\", \"content\": \"1. 얼음을 채운 칵테일 셰이커에 진, 리큐어, 레몬즙을 붓습니다. \n" +
                                                    "2.차가운 칵테일 글라스에 따라줍니다. \n" +
                                                    "3. 구운 레몬 껍질로 장식합니다.\", \"image\": \"https://www.thespruceeats.com/thmb/fA8u5yMunR2O9je6zBbSZpSXgUM=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/aviation-cocktail-recipe-760055-hero-01-af5233300c184476b02891de2c685b2a.jpg\", \"color\": \"purplpe\", \"category\": \"Before Dinner Cocktail\", \"abv\": 27}"
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
                                            value = "{\"id\": 1, \"korName\": \"모히토\", \"engName\": \"Mojito\", \"location\": \"쿠바\", \"ingredients\": \"럼, 라임, 민트\", \"garnish\": \"민트 잎\", \"content\": \"민트잎에 라임즙을 섞고 럼을 넣고 소다를 넣는다.\", \"image\": \"image_url_here\", \"color\": \"green\", \"category\": \"Tropical\", \"abv\": 14}"
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

    @GetMapping("/random")
    @Operation(
            summary = "랜덤 칵테일 ID 조회",
            description = "지정된 개수의 랜덤 칵테일 ID를 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "예제 데이터",
                                            value = "[1, 2, 3]"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<List<Integer>> getRandomCocktailIds(
            @Parameter(description = "조회할 랜덤 칵테일 ID의 개수", example = "3")
            @RequestParam(name = "count", defaultValue = "5") int count) {
        List<Integer> randomIds = cocktailService.getRandomCocktailIds(count);
        return new ResponseEntity<>(randomIds, HttpStatus.OK);
    }


}
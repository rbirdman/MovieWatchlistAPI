package io.swagger.api;

import io.swagger.model.SearchData;
import io.swagger.model.TitleData;
import io.swagger.model.UserRating;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-02T22:43:09.213512-04:00[America/New_York]")
@RestController
public class MediaApiController implements MediaApi {

    private static final Logger log = LoggerFactory.getLogger(MediaApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public MediaApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<SearchData> mediaGet(@NotNull @Parameter(in = ParameterIn.QUERY, description = "" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "query", required = true) String query) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SearchData>(objectMapper.readValue("{\n  \"expression\" : \"Frozen\",\n  \"searchType\" : \"Title\",\n  \"errorMessage\" : \"\",\n  \"results\" : [ {\n    \"image\" : \"https://imdb-api.com/images/original/MV5BMjI0NTQ4MzgxMl5BMl5BanBnXkFtZTcwMzI1MzU2Nw@@._V1_Ratio1.0714_AL_.jpg\",\n    \"description\" : \"When the newly crowned Queen Elsa accidentally uses her power to turn things into ice to curse her home in infinite winter, her sister Anna teams up with a mountain man, his playful reindeer, and a snowman to change the weather condition.\",\n    \"id\" : \"tt123456\",\n    \"title\" : \"Frozen\",\n    \"resultType\" : \"Title\"\n  }, {\n    \"image\" : \"https://imdb-api.com/images/original/MV5BMjI0NTQ4MzgxMl5BMl5BanBnXkFtZTcwMzI1MzU2Nw@@._V1_Ratio1.0714_AL_.jpg\",\n    \"description\" : \"When the newly crowned Queen Elsa accidentally uses her power to turn things into ice to curse her home in infinite winter, her sister Anna teams up with a mountain man, his playful reindeer, and a snowman to change the weather condition.\",\n    \"id\" : \"tt123456\",\n    \"title\" : \"Frozen\",\n    \"resultType\" : \"Title\"\n  } ]\n}", SearchData.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SearchData>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SearchData>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TitleData> mediaMediaIdGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("mediaId") String mediaId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TitleData>(objectMapper.readValue("{\n  \"image\" : \"https://imdb-api.com/images/original/MV5BNzhlY2E5NDUtYjJjYy00ODg3LWFkZWQtYTVmMzU4ZWZmOWJkXkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_Ratio0.6751_AL_.jpg\",\n  \"fullTitle\" : \"Lost (TV Series 2004–2010)\",\n  \"runtimeMins\" : \"runtimeMins\",\n  \"year\" : \"2004\",\n  \"releaseDate\" : \"2004-09-22\",\n  \"genreList\" : [ {\n    \"value\" : \"value\",\n    \"key\" : \"key\"\n  }, {\n    \"value\" : \"value\",\n    \"key\" : \"key\"\n  } ],\n  \"title\" : \"Lost\",\n  \"type\" : \"TVSeries\",\n  \"runtimeStr\" : \"runtimeStr\",\n  \"plot\" : \"The past, present, and future lives of surviving Oceanic Flight 815 passengers are dramatically intertwined as a fight for survival ensues in a quest for answers after crashlanding on a mysterious island. Each discovery prompts yet more secrets, as the hastily-formed colony search for a way off the island, or is this their home?\",\n  \"ratings\" : [ {\n    \"rating\" : 4\n  }, {\n    \"rating\" : 4\n  } ],\n  \"originalTitle\" : \"\",\n  \"genres\" : \"Adventure, Drama, Fantasy\",\n  \"actorList\" : [ {\n    \"image\" : \"https://imdb-api.com/images/original/MV5BMTUyNTkxODIxN15BMl5BanBnXkFtZTgwOTU2MDAwMTE@._V1_Ratio1.0000_AL_.jpg\",\n    \"asCharacter\" : \"Hugo 'Hurley\",\n    \"name\" : \"Jorge Garcia\",\n    \"id\" : \"nm0306201\"\n  }, {\n    \"image\" : \"https://imdb-api.com/images/original/MV5BMTUyNTkxODIxN15BMl5BanBnXkFtZTgwOTU2MDAwMTE@._V1_Ratio1.0000_AL_.jpg\",\n    \"asCharacter\" : \"Hugo 'Hurley\",\n    \"name\" : \"Jorge Garcia\",\n    \"id\" : \"nm0306201\"\n  } ],\n  \"id\" : \"tt0411008\"\n}", TitleData.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TitleData>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TitleData>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> mediaMediaIdRatingsPost(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("mediaId") String mediaId,@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody UserRating body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}

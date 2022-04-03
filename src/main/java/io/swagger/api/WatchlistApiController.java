package io.swagger.api;

import io.swagger.model.MediaItem;
import io.swagger.model.Watchlist;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-02T22:29:35.803425-04:00[America/New_York]")
@RestController
public class WatchlistApiController implements WatchlistApi {

    private static final Logger log = LoggerFactory.getLogger(WatchlistApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public WatchlistApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Watchlist> watchlistPost() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Watchlist>(objectMapper.readValue("{\n  \"mediaItems\" : [ {\n    \"name\" : \"The Good Doctor\",\n    \"id\" : 5\n  }, {\n    \"name\" : \"The Good Doctor\",\n    \"id\" : 5\n  } ],\n  \"isPubliclyViewable\" : true,\n  \"ownerUserId\" : 3,\n  \"id\" : 1\n}", Watchlist.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Watchlist>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Watchlist>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Watchlist> watchlistWatchlistIdGet(@Parameter(in = ParameterIn.PATH, description = "The id of the watchlist to retrieve", required=true, schema=@Schema()) @PathVariable("watchlist_id") Integer watchlistId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Watchlist>(objectMapper.readValue("{\n  \"mediaItems\" : [ {\n    \"name\" : \"The Good Doctor\",\n    \"id\" : 5\n  }, {\n    \"name\" : \"The Good Doctor\",\n    \"id\" : 5\n  } ],\n  \"isPubliclyViewable\" : true,\n  \"ownerUserId\" : 3,\n  \"id\" : 1\n}", Watchlist.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Watchlist>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Watchlist>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> watchlistWatchlistIdMediaMediaIdDelete(@Parameter(in = ParameterIn.PATH, description = "The id of the watchlist to retrieve", required=true, schema=@Schema()) @PathVariable("watchlist_id") Integer watchlistId,@Parameter(in = ParameterIn.PATH, description = "The id of the media to delete", required=true, schema=@Schema()) @PathVariable("media_id") Integer mediaId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Watchlist> watchlistWatchlistIdMediaPost(@Parameter(in = ParameterIn.PATH, description = "The id of the watchlist to retrieve", required=true, schema=@Schema()) @PathVariable("watchlist_id") Integer watchlistId,@Parameter(in = ParameterIn.DEFAULT, description = "Media data to add to watchlist", schema=@Schema()) @Valid @RequestBody MediaItem body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Watchlist>(objectMapper.readValue("{\n  \"mediaItems\" : [ {\n    \"name\" : \"The Good Doctor\",\n    \"id\" : 5\n  }, {\n    \"name\" : \"The Good Doctor\",\n    \"id\" : 5\n  } ],\n  \"isPubliclyViewable\" : true,\n  \"ownerUserId\" : 3,\n  \"id\" : 1\n}", Watchlist.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Watchlist>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Watchlist>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Watchlist> watchlistWatchlistIdVisibilityPut(@Parameter(in = ParameterIn.PATH, description = "The id of the watchlist to retrieve", required=true, schema=@Schema()) @PathVariable("watchlist_id") Integer watchlistId,@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody Object body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Watchlist>(objectMapper.readValue("{\n  \"mediaItems\" : [ {\n    \"name\" : \"The Good Doctor\",\n    \"id\" : 5\n  }, {\n    \"name\" : \"The Good Doctor\",\n    \"id\" : 5\n  } ],\n  \"isPubliclyViewable\" : true,\n  \"ownerUserId\" : 3,\n  \"id\" : 1\n}", Watchlist.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Watchlist>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Watchlist>(HttpStatus.NOT_IMPLEMENTED);
    }

}

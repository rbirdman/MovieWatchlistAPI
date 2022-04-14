package io.swagger.api.media;

import io.swagger.entity.media.MediaInfo;
import io.swagger.model.media.SearchData;
import io.swagger.model.media.TitleData;
import io.swagger.model.media.UserRating;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.service.MediaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-02T22:43:09.213512-04:00[America/New_York]")
@RestController
public class MediaApiController implements MediaApi {

    private static final Logger log = LoggerFactory.getLogger(MediaApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final MediaService mediaService;

    @org.springframework.beans.factory.annotation.Autowired
    public MediaApiController(ObjectMapper objectMapper, HttpServletRequest request, MediaService mediaService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.mediaService = mediaService;
    }

    public ResponseEntity<SearchData> mediaGet(@NotNull @Parameter(in = ParameterIn.QUERY, description = "" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "query", required = true) String query) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            SearchData searchData = mediaService.GetMediaByTitle(query);
            return ResponseEntity.ok(searchData);
        }

        // TODO: Handle application/xml. Return proper codes for no "Accept" header
        //  and improper accept value
        return new ResponseEntity<SearchData>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TitleData> mediaMediaIdGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("mediaId") String mediaId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            TitleData titleData = mediaService.GetMediaById(mediaId);
            return ResponseEntity.ok(titleData);
        }

        // TODO: Handle application/xml. Return proper codes for no "Accept" header
        //  and improper accept value
        return new ResponseEntity<TitleData>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> mediaMediaIdRatingsPost(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("mediaId") String mediaId,@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody UserRating body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}

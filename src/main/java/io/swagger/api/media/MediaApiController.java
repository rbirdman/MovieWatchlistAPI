package io.swagger.api.media;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.media.SearchData;
import io.swagger.model.media.TitleData;
import io.swagger.model.media.UserRating;
import io.swagger.service.MediaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
        SearchData searchData = mediaService.getMediaByTitle(query);
        return ResponseEntity.ok(searchData);
    }

    public ResponseEntity<TitleData> mediaMediaIdGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("mediaId") String mediaId) {
        TitleData titleData = mediaService.getMediaById(mediaId);
        return titleData != null
            ? ResponseEntity.ok(titleData)
            : ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> mediaMediaIdRatingsPost(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("mediaId") String mediaId,@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody UserRating body) {
        if (mediaService.getMediaById(mediaId) != null) {
            mediaService.rateMediaById(mediaId, body.getRating());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

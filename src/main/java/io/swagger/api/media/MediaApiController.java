package io.swagger.api.media;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.api.watchlist.WatchlistApiController;
import io.swagger.model.media.SearchData;
import io.swagger.model.media.SearchResult;
import io.swagger.model.media.TitleData;
import io.swagger.model.media.UserRating;
import io.swagger.model.watchlist.WatchlistCreateRequest;
import io.swagger.service.MediaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        searchData.add(linkTo(methodOn(MediaApiController.class).mediaGet(query)).withSelfRel());
        searchData.add(linkTo(methodOn(WatchlistApiController.class).watchlistPost(new WatchlistCreateRequest())).withRel("watchlist"));
        for (SearchResult result : searchData.getResults()) {
            searchData.add(linkTo(methodOn(MediaApiController.class).mediaMediaIdGet(result.getId())).withRel("get-media-metadata"));
        }
        return ResponseEntity.ok(searchData);
    }

    public ResponseEntity<TitleData> mediaMediaIdGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("mediaId") String mediaId) {
        TitleData titleData = mediaService.getMediaById(mediaId);
        titleData.add(linkTo(methodOn(MediaApiController.class).mediaMediaIdGet(mediaId)).withSelfRel());
        titleData.add(linkTo(methodOn(MediaApiController.class).mediaMediaIdRatingsPost(mediaId, new UserRating(5, null))).withRel("rate-media"));
        titleData.add(linkTo(methodOn(MediaApiController.class).mediaGet("Movies")).withRel("search-media"));
        titleData.add(linkTo(methodOn(WatchlistApiController.class).watchlistPost(new WatchlistCreateRequest())).withRel("watchlist"));
        return titleData != null
                ? ResponseEntity.ok(titleData)
                : ResponseEntity.notFound().build();
    }

    public ResponseEntity mediaMediaIdRatingsPost(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("mediaId") String mediaId, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody UserRating body) {
        if (mediaService.getMediaById(mediaId) != null) {
            mediaService.rateMediaById(mediaId, body.getRating());
            RepresentationModel representationModel = new RepresentationModel();
            representationModel.add(linkTo(methodOn(MediaApiController.class).mediaMediaIdGet(mediaId)).withRel("get-media-metadata"));
            representationModel.add(linkTo(methodOn(MediaApiController.class).mediaGet("Movies")).withRel("search-media"));
            return ResponseEntity.ok().body(representationModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

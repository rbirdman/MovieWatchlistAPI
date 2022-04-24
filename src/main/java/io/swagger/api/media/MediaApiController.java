package io.swagger.api.media;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.api.exceptions.ApiException;
import io.swagger.api.watchlist.WatchlistApiController;
import io.swagger.model.media.*;
import io.swagger.model.watchlist.WatchlistCreateRequest;
import io.swagger.service.MediaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

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
        try {
            SearchData searchData = mediaService.getMediaByTitle(query);
            searchData.add(linkTo(methodOn(MediaApiController.class).mediaGet(query)).withSelfRel());
            searchData.add(linkTo((WatchlistApiController.class)).withRel("create-watchlist"));
            searchData.add(linkTo(methodOn(WatchlistApiController.class).watchlistWatchlistIdMediaPost(UUID.randomUUID(), new MediaItem())).withRel("add-to-watchlist"));
            for (SearchResult result : searchData.getResults()) {
                searchData.add(linkTo(methodOn(MediaApiController.class).mediaMediaIdGet(result.getId())).withRel("get-media-metadata"));
            }
            return ResponseEntity.ok(searchData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), String.format("Error getting media with query %s",query),e);
        }
    }

    public ResponseEntity<TitleData> mediaMediaIdGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("mediaId") String mediaId) {
        try {
            TitleData titleData = mediaService.getMediaById(mediaId);
            MediaItem sampleMediaItem = new MediaItem().id(mediaId).name(titleData.getTitle());
            WatchlistCreateRequest sampleWatchlistCreate = new WatchlistCreateRequest().addMediaItemsItem(sampleMediaItem).ownerUserId(UUID.randomUUID());
            titleData.add(linkTo(methodOn(MediaApiController.class).mediaMediaIdGet(mediaId)).withSelfRel());
            titleData.add(linkTo(methodOn(MediaApiController.class).mediaMediaIdRatingsPost(mediaId, new UserRating(5, null))).withRel("rate-media"));
            titleData.add(linkTo(methodOn(MediaApiController.class).mediaGet("Movies")).withRel("search-media"));
            titleData.add(linkTo(methodOn(WatchlistApiController.class).watchlistPost(sampleWatchlistCreate)).withRel("create-watchlist"));
            titleData.add(linkTo(methodOn(WatchlistApiController.class).watchlistWatchlistIdMediaPost(UUID.randomUUID(), sampleMediaItem)).withRel("add-to-watchlist"));
            return titleData != null
                    ? ResponseEntity.ok(titleData)
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), String.format("Error getting media with ID %s",mediaId),e);
        }
    }

    public ResponseEntity mediaMediaIdRatingsPost(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("mediaId") String mediaId, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody UserRating body) {
        try {
            if (mediaService.getMediaById(mediaId) != null) {
                mediaService.rateMediaById(mediaId, body.getRating());
                RepresentationModel representationModel = new RepresentationModel();
                representationModel.add(linkTo(methodOn(MediaApiController.class).mediaMediaIdGet(mediaId)).withRel("get-media-metadata"));
                representationModel.add(linkTo(methodOn(MediaApiController.class).mediaGet("Movies")).withRel("search-media"));
                return ResponseEntity.ok().body(representationModel);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), String.format("Error rating media with ID %s",mediaId),e);
        }
    }

}

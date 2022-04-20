package io.swagger.api.watchlist;

import io.swagger.model.media.MediaItem;
import io.swagger.model.watchlist.Watchlist;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.watchlist.WatchlistCreateRequest;
import io.swagger.model.watchlist.WatchlistVisiblity;
import io.swagger.service.WatchlistService;
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

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-02T22:43:09.213512-04:00[America/New_York]")
@RestController
public class WatchlistApiController implements WatchlistApi {

    private static final Logger log = LoggerFactory.getLogger(WatchlistApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final WatchlistService watchlistService;

    @org.springframework.beans.factory.annotation.Autowired
    public WatchlistApiController(ObjectMapper objectMapper, HttpServletRequest request, WatchlistService watchlistService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.watchlistService = watchlistService;
    }

    public ResponseEntity<Watchlist> watchlistPost(@Parameter(in = ParameterIn.DEFAULT, description = "Watchlist to create", schema=@Schema()) @Valid @RequestBody WatchlistCreateRequest body) {
        Watchlist watchlist = watchlistService.CreateWatchlist(body);

        // TODO: Add links for individual media items

        return ResponseEntity.created(URI.create("/watchlist/" + watchlist.getId())).body(watchlist);
    }

    public ResponseEntity<Watchlist> watchlistWatchlistIdGet(@Parameter(in = ParameterIn.PATH, description = "The id of the watchlist to retrieve", required=true, schema=@Schema()) @PathVariable("watchlist_id") UUID watchlistId) {
        Watchlist watchlist = watchlistService.GetWatchlistById(watchlistId);

        if (watchlist == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(watchlist);
    }

    public ResponseEntity<Void> watchlistWatchlistIdMediaMediaIdDelete(@Parameter(in = ParameterIn.PATH, description = "The id of the watchlist to retrieve", required=true, schema=@Schema()) @PathVariable("watchlist_id") UUID watchlistId,@Parameter(in = ParameterIn.PATH, description = "The id of the media to delete", required=true, schema=@Schema()) @PathVariable("media_id") String mediaId) {
        watchlistService.RemoveMediaFromWatchlist(watchlistId, mediaId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Watchlist> watchlistWatchlistIdMediaPost(@Parameter(in = ParameterIn.PATH, description = "The id of the watchlist to retrieve", required=true, schema=@Schema()) @PathVariable("watchlist_id") UUID watchlistId,@Parameter(in = ParameterIn.DEFAULT, description = "Media data to add to watchlist", schema=@Schema()) @Valid @RequestBody MediaItem body) {
        Watchlist watchlist = watchlistService.AddMediaItemToWatchlist(watchlistId, body);

        if (watchlist == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(watchlist);
    }

    public ResponseEntity<Watchlist> watchlistWatchlistIdVisibilityPut(@Parameter(in = ParameterIn.PATH, description = "The id of the watchlist to retrieve", required=true, schema=@Schema()) @PathVariable("watchlist_id") UUID watchlistId,@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody WatchlistVisiblity body) {
        Watchlist watchlist = watchlistService.SetWatchlistVisibility(watchlistId, body);

        if (watchlist == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(watchlist);
    }

}

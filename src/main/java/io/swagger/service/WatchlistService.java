package io.swagger.service;

import io.swagger.api.exceptions.ApiException;
import io.swagger.api.exceptions.NotFoundException;
import io.swagger.entity.watchlist.WatchlistEntity;
import io.swagger.entity.watchlist.WatchlistMedia;
import io.swagger.model.media.MediaItem;
import io.swagger.model.media.SearchResult;
import io.swagger.model.media.TitleData;
import io.swagger.model.watchlist.Watchlist;
import io.swagger.model.watchlist.WatchlistCreateRequest;
import io.swagger.model.watchlist.WatchlistVisiblity;
import io.swagger.repository.WatchlistMediaRepository;
import io.swagger.repository.WatchlistRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final WatchlistMediaRepository watchlistMediaRepository;

    private final MediaService mediaService;
    private final MovieUserDetailsService movieUserDetailsService;

    public WatchlistService(WatchlistRepository watchlistRepository,
                            WatchlistMediaRepository watchlistMediaRepository,
                            MediaService mediaService,
                            MovieUserDetailsService movieUserDetailsService) {
        this.watchlistRepository = watchlistRepository;
        this.watchlistMediaRepository = watchlistMediaRepository;
        this.mediaService = mediaService;
        this.movieUserDetailsService = movieUserDetailsService;
    }

    public Watchlist CreateWatchlist(WatchlistCreateRequest watchlistRequest) throws NotFoundException {
        ValidateWatchlistCreateRequest(watchlistRequest);

        Watchlist watchlist = new Watchlist();
        watchlist.setOwnerUserId(watchlistRequest.getOwnerUserId());
        watchlist.setMediaItems(watchlistRequest.getMediaItems());
        watchlist.setIsPubliclyViewable(watchlistRequest.isIsPubliclyViewable());

        UserDetails owningUser = movieUserDetailsService.loadUserById(watchlist.getOwnerUserId());
        if (owningUser == null) {
            throw new NotFoundException(404, "Unknown ownerUserId: " + watchlist.getOwnerUserId());
        }

        WatchlistEntity watchlistEntity = ConvertWatchlistToEntity(watchlist);

        WatchlistEntity savedEntity = watchlistRepository.save(watchlistEntity);

        // Legacy, does not save correctly
//        List<WatchlistMedia> mediaList = GetWatchlistMediaFromWatchlist(watchlist);
//        watchlistMediaRepository.saveAll(mediaList);

        // inefficient, but reuses validation and code to find media id code:
        for (MediaItem item : watchlist.getMediaItems()) {
            AddMediaItemToWatchlist(savedEntity.getId(), item);
        }

        // return final state of the watchlist
        return GetWatchlistById(savedEntity.getId(), true /*readOnlyAccess*/);
    }

    private void ValidateWatchlistCreateRequest(WatchlistCreateRequest watchlist)
    {
        if (watchlist == null) {
            throw new ApiException(400, "Request not provided");
        }
        else if (watchlist.getOwnerUserId() == null) {
            throw new ApiException(400, "ownerUserId not provided");
        }
        else if (watchlist.isIsPubliclyViewable() == null) {
            throw new ApiException(400, "isPubliclyViewable not provided");
        }

        // media items is optional
        if (watchlist.getMediaItems() == null) {
            watchlist.setMediaItems(new ArrayList<>());
        }
    }

    public Watchlist GetWatchlistById(UUID id, boolean readOnlyAccess) {
        WatchlistEntity watchlistEntity = ValidateAccessPermission(id, readOnlyAccess);

        List<MediaItem> mediaItems = new ArrayList<>();

        // Get Media information for watchlist
        List<WatchlistMedia> watchlistItems = watchlistMediaRepository.findAllByWatchlistId(id);
        for (WatchlistMedia item : watchlistItems) {
            TitleData titleData = mediaService.getMediaById(item.getMediaId());
            if (titleData != null) {
                mediaItems.add(new MediaItem().id(titleData.getId()).name(titleData.getTitle()));
            }
        }

        return ConvertWatchlistFromEntity(watchlistEntity, mediaItems);
    }

    public void RemoveMediaFromWatchlist(UUID watchlistId, String mediaId)
    {
        final boolean readOnlyAccess = false; // delete operation is not read-only
        ValidateAccessPermission(watchlistId, readOnlyAccess);

        // Custom delete query did not work. I'm not sure why. This is a work around
        // that should have similar performance
        List<WatchlistMedia> mediaItems = watchlistMediaRepository.findAllByWatchlistId(watchlistId);
        for (WatchlistMedia media : mediaItems) {
            if (media.getMediaId().equals(mediaId)) {
                watchlistMediaRepository.deleteById(media.getId());
            }
        }
    }

    public Watchlist AddMediaItemToWatchlist(UUID watchlistId, MediaItem mediaItem)
    {
        final boolean readOnlyAccess = false; // add operation is not read-only

        // Verify watchlist and media item exist
        ValidateAccessPermission(watchlistId, readOnlyAccess);
        ValidateMediaItemForInsertion(mediaItem);

        WatchlistMedia watchlistMedia = new WatchlistMedia();
        watchlistMedia.setWatchlistId(watchlistId);

        if (mediaItem.getId() != null) {
            if (mediaService.getMediaById(mediaItem.getId()) == null) {
                throw new ApiException(400, "Provided id does not match a movie title: " + mediaItem.getId());
            }

            watchlistMedia.setMediaId(mediaItem.getId());
        }
        else {
            // Get the MediaId based on the movie name/title
            SearchResult result = mediaService.getSearchResultByTitle(mediaItem.getName());
            if (result == null || result.getId() == null) {
                throw new ApiException(400, "Provided movie title not found: " + mediaItem.getName());
            }

            watchlistMedia.setMediaId(result.getId());
        }

        watchlistMediaRepository.save(watchlistMedia);

        // Return new version of the watchlist
        return GetWatchlistById(watchlistId, true /*readOnlyAccess*/);
    }

    public Watchlist SetWatchlistVisibility(UUID watchlistId, WatchlistVisiblity visibility)
    {
        if (visibility.isIsPubliclyViewable() == null) {
            throw new ApiException(400, "isPubliclyViewable parameter not provided");
        }

        final boolean readOnlyAccess = false; // add operation is not read-only
        WatchlistEntity entity = ValidateAccessPermission(watchlistId, readOnlyAccess);
        entity.setIsPubliclyViewable(visibility.isIsPubliclyViewable());

        watchlistRepository.save(entity);

        // return latest version of watchlist
        return GetWatchlistById(entity.getId(), true /*readOnlyAccess*/);
    }

    private void ValidateMediaItemForInsertion(MediaItem mediaItem)
    {
        String id = mediaItem.getId();
        String name = mediaItem.getName();

        if (id != null && name != null) {
            String msg = String.format("Id and Name given. Choose one parameter to add to playlist - id: %s name: %s", id, name);
            throw new ApiException(400, msg);
        }
        else if (id == null && name == null) {
            throw new ApiException(400, "Id nor Name were given in Request");
        }
        else if (id != null && id.isEmpty()) {
            throw new ApiException(400, "Id is empty");
        }
        else if (name != null && name.isEmpty()) {
            throw new ApiException(400, "Name is empty");
        }
    }

    private WatchlistEntity ValidateAccessPermission(UUID id, boolean readOnlyAccess)
    {
        Optional<WatchlistEntity> entity = watchlistRepository.findById(id);
        if (!entity.isPresent()) {
            throw new NotFoundException(404, "WatchlistId not found: " + id.toString());
        }

        WatchlistEntity watchlistEntity = entity.get();
        UserDetails owningUser = movieUserDetailsService.loadUserById(watchlistEntity.getOwnerUserId());
        String callingUser =  SecurityContextHolder.getContext().getAuthentication().getName();

        boolean callerIsOwningUser = owningUser.getUsername().equals(callingUser);
        if (!callerIsOwningUser) {
            if (!watchlistEntity.getIsPubliclyViewable()) {
                // non-owning user may not view a private watchlist
                throw new NotFoundException(404, "WatchlistId not found: " + id.toString());
            }
            else if (!readOnlyAccess) {
                // non-owning user wants to edit this watchlist
                throw new ApiException(403, "Calling user may not edit a Watchlist they do not own");
            }
        }

        return watchlistEntity;
    }

    private WatchlistEntity ConvertWatchlistToEntity(Watchlist watchlist) {
        WatchlistEntity entity = new WatchlistEntity(watchlist.getId(),
                                                     watchlist.isIsPubliclyViewable(),
                                                     watchlist.getOwnerUserId());

        return entity;
    }

    private List<WatchlistMedia> GetWatchlistMediaFromWatchlist(Watchlist entity) {
        if (entity.getMediaItems() == null) {
            return new ArrayList<>();
        }

        return entity.getMediaItems().stream()
                .map(mediaItem -> new WatchlistMedia(null, entity.getId(), mediaItem.getId()))
                .collect(Collectors.toList());
    }


    private Watchlist ConvertWatchlistFromEntity(WatchlistEntity entity, List<MediaItem> mediaItems) {
//        // Deserialize data to Title data
//        List<MediaItem> deserializedTitleData = entity.getMediaItems().stream()
//                // Deserialize into Title data (how it is stored)
//                .map(mediaCache -> mediaCache.readData(TitleData.class))
//                // Convert TitleData to MediaItem
//                .map(titleData -> new MediaItem().id(titleData.getId()).name(titleData.getTitle()))
//                .collect(Collectors.toList());

        Watchlist watchlist = new Watchlist();

        watchlist.setId(entity.getId());
        watchlist.setIsPubliclyViewable(entity.getIsPubliclyViewable());
        watchlist.setMediaItems(mediaItems);
        watchlist.setOwnerUserId(entity.getOwnerUserId());

        return watchlist;
    }
}

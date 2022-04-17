package io.swagger.service;

import io.swagger.entity.watchlist.WatchlistEntity;
import io.swagger.entity.watchlist.WatchlistMedia;
import io.swagger.model.media.MediaItem;
import io.swagger.model.media.SearchData;
import io.swagger.model.media.SearchResult;
import io.swagger.model.media.TitleData;
import io.swagger.model.watchlist.Watchlist;
import io.swagger.model.watchlist.WatchlistCreateRequest;
import io.swagger.model.watchlist.WatchlistVisiblity;
import io.swagger.repository.WatchlistMediaRepository;
import io.swagger.repository.WatchlistRepository;
import org.springframework.data.domain.Example;
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

    public WatchlistService(WatchlistRepository watchlistRepository,
                            WatchlistMediaRepository watchlistMediaRepository,
                            MediaService mediaService) {
        this.watchlistRepository = watchlistRepository;
        this.watchlistMediaRepository = watchlistMediaRepository;
        this.mediaService = mediaService;
    }

    public Watchlist CreateWatchlist(WatchlistCreateRequest watchlistRequest) {
        // TODO: Validate input data

        Watchlist watchlist = new Watchlist();
        watchlist.setOwnerUserId(watchlistRequest.getOwnerUserId());
        watchlist.setMediaItems(watchlistRequest.getMediaItems());
        watchlist.setIsPubliclyViewable(watchlistRequest.isIsPubliclyViewable());

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
        return GetWatchlistById(savedEntity.getId());
    }

    public Watchlist GetWatchlistById(UUID id) {
        Optional<WatchlistEntity> entity = watchlistRepository.findById(id);
        if (!entity.isPresent()) {
            return null;
        }

        List<MediaItem> mediaItems = new ArrayList<>();

        // Get Media information for watchlist
        List<WatchlistMedia> watchlistItems = watchlistMediaRepository.findAllByWatchlistId(id);
        for (WatchlistMedia item : watchlistItems) {
            TitleData titleData = mediaService.getMediaById(item.getMediaId());
            if (titleData != null) {
                mediaItems.add(new MediaItem().id(titleData.getId()).name(titleData.getTitle()));
            }
        }

        return ConvertWatchlistFromEntity(entity.get(), mediaItems);
    }

    public void RemoveMediaFromWatchlist(UUID watchlistId, String mediaId)
    {
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
        // Verify watchlist and media item exist
        if (!watchlistRepository.findById(watchlistId).isPresent()) {
            // TODO: Handle properly to result in 404
            throw new RuntimeException("Watchlist not found!");
        }

        ValidateMediaItemForInsertion(mediaItem);


        WatchlistMedia watchlistMedia = new WatchlistMedia();
        watchlistMedia.setWatchlistId(watchlistId);

        if (mediaItem.getId() != null) {
            if (mediaService.getMediaById(mediaItem.getId()) == null) {
                // TODO: Handle exception for proper http code
                throw new RuntimeException("Provided id does not match a movie title");
            }

            watchlistMedia.setMediaId(mediaItem.getId());
        }
        else {
            // Get the MediaId based on the movie name/title
            SearchResult result = mediaService.getSearchResultByTitle(mediaItem.getName());
            if (result == null || result.getId() == null) {
                // TODO: Handle exception for proper http code
                throw new RuntimeException("Provided movie title not found");
            }

            watchlistMedia.setMediaId(result.getId());
        }

        watchlistMediaRepository.save(watchlistMedia);

        // Return new version of the watchlist
        return GetWatchlistById(watchlistId);
    }

    public Watchlist SetWatchlistVisibility(UUID watchlistId, WatchlistVisiblity visibility)
    {
        if (visibility.isIsPubliclyViewable() == null) {
            throw new RuntimeException("isPubliclyViewable parameter not provided");
        }

        Optional<WatchlistEntity> result = watchlistRepository.findById(watchlistId);

        if (!result.isPresent()) {
            return null;
        }

        WatchlistEntity entity = result.get();
        entity.setIsPubliclyViewable(visibility.isIsPubliclyViewable());

        watchlistRepository.save(entity);

        // return latest version of watchlist
        return GetWatchlistById(entity.getId());
    }

    private void ValidateMediaItemForInsertion(MediaItem mediaItem)
    {
        String id = mediaItem.getId();
        String name = mediaItem.getName();

        // TODO: Handle exceptions properly to result in 400
        if (id != null && name != null) {
            throw new RuntimeException("Id and Name given. Choose one parameter to add to playlist");
        }
        else if (id == null && name == null) {
            throw new RuntimeException("Id nor Name were given in Request");
        }
        else if (id != null && id.isEmpty()) {
            throw new RuntimeException("Id is empty");
        }
        else if (name != null && name.isEmpty()) {
            throw new RuntimeException("Name is empty");
        }

    }

    private WatchlistEntity ConvertWatchlistToEntity(Watchlist watchlist) {
        WatchlistEntity entity = new WatchlistEntity(watchlist.getId(),
                                                     watchlist.isIsPubliclyViewable(),
                                                     watchlist.getOwnerUserId());

        return entity;
    }

    private List<WatchlistMedia> GetWatchlistMediaFromWatchlist(Watchlist entity) {
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

package io.swagger.service;

import io.swagger.entity.media.MediaInfo;
import io.swagger.model.media.SearchData;
import io.swagger.model.media.SearchResult;
import io.swagger.model.media.TitleData;
import io.swagger.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// The service will fetch data from imdb API if data does not already
// exist in local database
@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private IMDBService imdbService;

    private Map<String, TitleData> mediaCache = new HashMap<>();
    private Map<String, SearchData> searchCache = new HashMap<>();

    public TitleData GetMediaById(String id) {
        //Optional<TitleData> mediaInfo = mediaRepository.findById(id);
        //if (mediaInfo.isPresent()) {
        //    return mediaInfo.get();
        //}

        TitleData imdbData = mediaCache.get(id);
        if (imdbData == null) {
            // Call real API in order to populate local cache
            imdbData = imdbService.getMediaById(id);

            if (imdbData != null) {
                mediaCache.put(id, imdbData);
            }
        }

//        if (imdbData != null) {
//            mediaRepository.save(imdbData);
//        }

        return imdbData;
    }

    public SearchData GetMediaByTitle(String title) {
        SearchData imdbData = searchCache.get(title);
        if (imdbData == null) {
            // Call real API in order to populate local cache
            imdbData = imdbService.searchMediaByTitle(title);

            if (imdbData != null) {
                searchCache.put(title, imdbData);
            }
        }

        return imdbData;
    }

}

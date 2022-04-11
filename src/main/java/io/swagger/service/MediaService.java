package io.swagger.service;

import io.swagger.entity.media.MediaInfo;
import io.swagger.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

// The service will fetch data from imdb API if data does not already
// exist in local database
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;


    public MediaInfo GetMediaById(String id) {
        Optional<MediaInfo> mediaInfo = mediaRepository.findById(id);
        if (mediaInfo.isPresent()) {
            return mediaInfo.get();
        }

        // TODO: Call real API if not found in local database

        // TODO: Store API result in database to cache data and return
        return null;
    }

    public MediaInfo GetMediaByTitle(String title) {
        Optional<MediaInfo> mediaInfo = mediaRepository.findByTitle(title);
        if (mediaInfo.isPresent()) {
            return mediaInfo.get();
        }
        // TODO: Call real API if not found in local database

        // TODO: Store API result in database to cache data and return
        return null;
    }

}

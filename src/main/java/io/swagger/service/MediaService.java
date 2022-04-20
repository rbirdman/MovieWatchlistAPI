package io.swagger.service;

import io.swagger.entity.media.MediaRating;
import io.swagger.model.media.SearchData;
import io.swagger.model.media.SearchResult;
import io.swagger.model.media.TitleData;
import io.swagger.model.media.UserRating;
import io.swagger.repository.ImdbCacheRepository;
import io.swagger.repository.MediaRatingRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// The service will fetch data from imdb API if data does not already
// exist in local database
@Service
public class MediaService {

    private final MediaRatingRepository mediaRatingRepository;

    private final ImdbCacheRepository imdbCacheRepository;

    private final IMDBService imdbService;

    public MediaService(MediaRatingRepository mediaRatingRepository, ImdbCacheRepository imdbCacheRepository, IMDBService imdbService) {
        this.mediaRatingRepository = mediaRatingRepository;
        this.imdbCacheRepository = imdbCacheRepository;
        this.imdbService = imdbService;
    }

    public TitleData getMediaById(String id) {
        TitleData imdbData = imdbCacheRepository.findByQueryAndReadValue(id, TitleData.class);
        if (imdbData == null) {
            // Call real API in order to populate local cache
            imdbData = imdbService.getMediaById(id);

            if (imdbData != null) {
                imdbCacheRepository.cache(id, imdbData);
            }
        } else {
            System.out.println("Returning cached media");
        }

        // Retrieve associated ratings
        if (imdbData != null) {
            List<MediaRating> ratings = mediaRatingRepository.findAllRatingsForTitleId(id);
            if (ratings != null && !ratings.isEmpty()) {
                List<UserRating> userRatings = ratings.stream().map(mediaRating -> new UserRating(mediaRating.getRating(), mediaRating.getUserEmail())).collect(Collectors.toList());

                Double averageRating = userRatings.stream()
                        .mapToInt(UserRating::getRating)
                        .summaryStatistics()
                        .getAverage();

                imdbData.setRatings(userRatings);
                imdbData.setAverageRating(averageRating);
            }
        }

        return imdbData;
    }

    public SearchData getMediaByTitle(String title) {
        SearchData imdbData = imdbCacheRepository.findByQueryAndReadValue(title, SearchData.class);
        if (imdbData == null) {
            // Call real API in order to populate local cache
            imdbData = imdbService.searchMediaByTitle(title);

            if (imdbData != null) {
                imdbCacheRepository.cache(title, imdbData);
            }
        } else {
            System.out.println("Returning cached media");
        }

        return imdbData;
    }

    // Centralized function for finding a specific title in a list
    // of results
    public SearchResult getSearchResultByTitle(String title) {
        SearchData data = getMediaByTitle(title);

        if (data == null) {
            return null;
        }

        // find title that matches exactly ignoring case
        return data.getResults().stream().
                filter(result -> result.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public void rateMediaById(String titleId, int rating) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Update or create a user rating
        List<MediaRating> userRatingsForMedia = mediaRatingRepository.findAllRatingsForTitleIdAndUser(titleId,email);
        MediaRating mediaRating = !userRatingsForMedia.isEmpty()
            ? userRatingsForMedia.get(0)
            : new MediaRating();

        mediaRating.setTitleId(titleId);
        mediaRating.setUserEmail(email);
        mediaRating.setRating(rating);

        mediaRatingRepository.save(mediaRating);
    }

}

package io.swagger.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.entity.media.MediaCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImdbCacheRepository extends CrudRepository<MediaCache, String> {

    public default void cache(String query, Object data) {
        try {
            this.save(MediaCache.of(query, data));
            System.out.println("Successfully cached IMDB data.");
        } catch (JsonProcessingException e) {
            System.err.println("Failed to cache IMDB data.");
            e.printStackTrace();
        }
    }

    public default <T> T findByQueryAndReadValue(String query, Class<T> clazz) {
        return findByQuery(query).orElse(new MediaCache()).readData(clazz);
    }

    public Optional<MediaCache> findByQuery(String query);

}

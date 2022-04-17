package io.swagger.repository;

import io.swagger.entity.watchlist.WatchlistMedia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface WatchlistMediaRepository extends CrudRepository<WatchlistMedia, Integer> {
    @Query("SELECT w FROM WatchlistMedia w WHERE w.WatchlistId = ?1")
    List<WatchlistMedia> findAllByWatchlistId(UUID WatchlistId);
}

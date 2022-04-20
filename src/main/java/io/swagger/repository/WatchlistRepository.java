package io.swagger.repository;

import io.swagger.entity.watchlist.WatchlistEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface WatchlistRepository extends CrudRepository<WatchlistEntity, UUID> {
}

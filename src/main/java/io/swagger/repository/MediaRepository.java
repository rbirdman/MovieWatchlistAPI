package io.swagger.repository;

import io.swagger.entity.media.MediaInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MediaRepository extends CrudRepository<MediaInfo, String> {
    Optional<MediaInfo> findByTitle(String title);

}

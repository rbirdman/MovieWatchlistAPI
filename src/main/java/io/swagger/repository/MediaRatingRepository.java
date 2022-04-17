package io.swagger.repository;

import io.swagger.entity.media.MediaRating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MediaRatingRepository extends CrudRepository<MediaRating, Integer> {

    @Query("select r from MediaRating r where r.titleId = ?1")
    public List<MediaRating> findAllRatingsForTitleId(String titleId);

    @Query("select r from MediaRating r where r.titleId = ?1 and r.userEmail = ?2")
    public List<MediaRating> findAllRatingsForTitleIdAndUser(String titleId, String email);

}

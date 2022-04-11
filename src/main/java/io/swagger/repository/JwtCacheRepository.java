package io.swagger.repository;

import io.swagger.entity.auth.JwtTokenCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtCacheRepository extends CrudRepository<JwtTokenCache, String> {

}

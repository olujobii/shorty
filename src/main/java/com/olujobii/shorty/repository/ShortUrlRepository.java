package com.olujobii.shorty.repository;

import com.olujobii.shorty.model.ShortUrls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrls, Long> {

    Boolean existsByShortCode(String shortCode);

    Optional<ShortUrls> findShortUrlsByShortCode(String shortCode);
}

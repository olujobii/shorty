package com.olujobii.shorty.repository;

import com.olujobii.shorty.model.OriginalUrls;
import com.olujobii.shorty.repository.projection.OriginalUrlView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OriginalUrlRepository extends JpaRepository<OriginalUrls, Long> {
    @Query("select o.originalUrl as originalUrl," +
            "s.shortCode as shortCode " +
            "from OriginalUrls o " +
            "inner join ShortUrls s on o.shortUrls.id = s.id " +
            "where s.shortCode = :shortCode")
    Optional<OriginalUrlView> findByShortCode(String shortCode);
}

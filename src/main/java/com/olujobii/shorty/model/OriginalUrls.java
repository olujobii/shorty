package com.olujobii.shorty.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OriginalUrls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(unique = true, nullable = false)
    private ShortUrls shortUrls;

    private String originalUrl;
}

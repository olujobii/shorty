package com.olujobii.shorty.dto.response;

import java.time.LocalDateTime;

public record UrlResponseDTO(
        Long id,
        String url,
        String shortCode,
        LocalDateTime createdAt,
        Boolean status
) {

}

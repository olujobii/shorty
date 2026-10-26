package com.olujobii.shorty.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateShortUrlRequestDTO(
        @NotBlank(message = "url is a required field")
        String url
) {
}

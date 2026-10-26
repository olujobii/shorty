package com.olujobii.shorty.service;

import com.olujobii.shorty.dto.request.CreateShortUrlRequestDTO;
import com.olujobii.shorty.dto.response.OriginalUrlResponseDTO;
import com.olujobii.shorty.dto.response.UrlResponseDTO;
import jakarta.validation.Valid;

public interface ShortenService {
    UrlResponseDTO createShortUrl(@Valid CreateShortUrlRequestDTO createShortUrlRequestDTO);

    OriginalUrlResponseDTO retrieveOriginalUrl(String shortUrl);

    UrlResponseDTO updateOriginalUrl(String shortUrl, @Valid CreateShortUrlRequestDTO createShortUrlRequestDTO);

    void deleteShortUrl(String shortUrl);
}

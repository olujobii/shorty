package com.olujobii.shorty.service.impl;

import com.olujobii.shorty.dto.request.CreateShortUrlRequestDTO;
import com.olujobii.shorty.dto.response.OriginalUrlResponseDTO;
import com.olujobii.shorty.dto.response.UrlResponseDTO;
import com.olujobii.shorty.exception.ResourceNotFoundException;
import com.olujobii.shorty.model.OriginalUrls;
import com.olujobii.shorty.model.ShortUrls;
import com.olujobii.shorty.repository.OriginalUrlRepository;
import com.olujobii.shorty.repository.ShortUrlRepository;
import com.olujobii.shorty.repository.projection.OriginalUrlView;
import com.olujobii.shorty.service.ShortenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class ShortenServiceImpl implements ShortenService {

    private final ShortUrlRepository shortUrlRepository;
    private final OriginalUrlRepository originalUrlRepository;

    @Override
    public UrlResponseDTO createShortUrl(@Valid CreateShortUrlRequestDTO createShortUrlRequestDTO) {
        var shortCode = shortUrlGeneration();

        var originalUrl = OriginalUrls.builder()
                .originalUrl(createShortUrlRequestDTO.url())
                .build();

        var shortUrl = ShortUrls.builder()
                .shortCode(shortCode)
                .url("https://www.shortly.ly/"+shortCode)
                .status(true)
                .build();

        shortUrl.setOriginalUrls(originalUrl);

        var savedShortUrl = shortUrlRepository.save(shortUrl);

        return new UrlResponseDTO(savedShortUrl.getId(),
                savedShortUrl.getOriginalUrls().getOriginalUrl(),
                savedShortUrl.getShortCode(),
                savedShortUrl.getCreatedAt(),
                savedShortUrl.getStatus());
    }

    @Override
    public OriginalUrlResponseDTO retrieveOriginalUrl(String shortUrl) {
        OriginalUrlView originalUrlView = originalUrlRepository.findByShortCode(shortUrl)
                .orElseThrow(() -> new ResourceNotFoundException(shortUrl+" does not exist"));

        return new OriginalUrlResponseDTO(originalUrlView.getOriginalUrl());
    }

    @Override
    public UrlResponseDTO updateOriginalUrl(String shortUrl, @Valid CreateShortUrlRequestDTO createShortUrlRequestDTO) {
        ShortUrls shortUrls = shortUrlRepository.findShortUrlsByShortCode(shortUrl)
                .orElseThrow(() -> new ResourceNotFoundException(shortUrl+" does not exist"));

        shortUrls.removeOriginalUrls();
        //fixme:error in this method. In README.md
        OriginalUrls originalUrl = OriginalUrls.builder()
                        .originalUrl(createShortUrlRequestDTO.url())
                        .build();

        shortUrls.setOriginalUrls(originalUrl);

        ShortUrls savedShortUrls = shortUrlRepository.save(shortUrls);

        return new UrlResponseDTO(savedShortUrls.getId(),
                savedShortUrls.getOriginalUrls().getOriginalUrl(),
                savedShortUrls.getShortCode(),
                savedShortUrls.getCreatedAt(),
                savedShortUrls.getStatus());
    }

    @Override
    public void deleteShortUrl(String shortUrl) {
        ShortUrls shortUrls = shortUrlRepository.findShortUrlsByShortCode(shortUrl)
                .orElseThrow(() -> new ResourceNotFoundException(shortUrl+" does not exist"));

        shortUrlRepository.delete(shortUrls);
    }

    private String shortUrlGeneration(){
        String shortCode;
        do {
            String uuid = UUID.randomUUID().toString();
            int indexOf = uuid.indexOf("-");

            shortCode = LocalDateTime.now().getYear() + uuid.substring(0, indexOf);
        }while(shortUrlRepository.existsByShortCode(shortCode));

        return shortCode;
    }
}

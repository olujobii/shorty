package com.olujobii.shorty.controller;

import com.olujobii.shorty.dto.request.CreateShortUrlRequestDTO;
import com.olujobii.shorty.dto.response.OriginalUrlResponseDTO;
import com.olujobii.shorty.dto.response.UrlResponseDTO;
import com.olujobii.shorty.service.ShortenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shorten")
public class ShortenController {

    private final ShortenService shortenService;

    @PostMapping
    public ResponseEntity<UrlResponseDTO> createShortUrl(@RequestBody @Valid CreateShortUrlRequestDTO createShortUrlRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(shortenService.createShortUrl(createShortUrlRequestDTO));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<OriginalUrlResponseDTO> retrieveOriginalUrl(@PathVariable String shortUrl){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(shortenService.retrieveOriginalUrl(shortUrl));
    }

    @PutMapping("/{shortUrl}")
    public ResponseEntity<UrlResponseDTO> updateOriginalUrl(@PathVariable String shortUrl,
                                                            @RequestBody @Valid CreateShortUrlRequestDTO createShortUrlRequestDTO){
        return ResponseEntity.ok(shortenService.updateOriginalUrl(shortUrl, createShortUrlRequestDTO));
    }

    @DeleteMapping("/{shortUrl}")
    public ResponseEntity<Void> deleteShortUrl(@PathVariable String shortUrl){

        shortenService.deleteShortUrl(shortUrl);
        return ResponseEntity.noContent().build();
    }
}

package com.kosenkovps.clicker.controller;

import com.kosenkovps.clicker.service.UrlService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class UrlController {
    private final UrlService urlService;

    @ApiOperation(value = "Short link generation",
            notes = "A short link is created from a long link",
            response = String.class)
    @PostMapping("short")
    public String convertToShortUrl(@RequestBody String request) {
        return urlService.toShortUrl(request);
    }

    @ApiOperation(value = "Redirect on a short link",
            notes = "Redirect on a short linkе")
    @GetMapping("{shortUrl}")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public ResponseEntity<?> getAndRedirect(@PathVariable String shortUrl, @RequestParam Boolean remote) {
        var url = urlService.getUrl(shortUrl);
        if(remote){
            return ResponseEntity.ok(url);
        }
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}

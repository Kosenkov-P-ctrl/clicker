package com.kosenkovps.clicker.service;

import com.kosenkovps.clicker.model.Url;
import com.kosenkovps.clicker.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository repository;
    private final ConverterService converterService;

    public String toShortUrl(String defaultUrl) {
        var urlTmp = new Url();
        urlTmp.setDefaultUrl(defaultUrl);
        urlTmp.setCreatedDate(new Date());
        var url = repository.save(urlTmp);
        return converterService.encode(url.getId());
    }

    public String getUrl(String shortUrl) {
        var id = converterService.decode(shortUrl);
        var url = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("URL not found: " + shortUrl));
        return url.getDefaultUrl();
    }
}

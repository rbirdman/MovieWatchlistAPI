package io.swagger.service;

import io.swagger.model.media.SearchData;
import io.swagger.model.media.TitleData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IMDBService {
    private static final String hosturl = "https://imdb-api.com/";

    @Value("${app.security.imdbApiKey}")
    private String apiKey;

    private RestTemplate restTemplate;

    public IMDBService() {
        restTemplate = new RestTemplate();

    }

    public TitleData getMediaById(String id) {
        final String url = getMediaByIdURL(id);

        ResponseEntity<TitleData> response = restTemplate.getForEntity(url, TitleData.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }

        return null;
    }

    public SearchData searchMediaByTitle(String title) {
        final String url = getSearchMediaByTitleURL(title);

        ResponseEntity<SearchData> response = restTemplate.getForEntity(url, SearchData.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }

        return null;
    }

    private String getMediaByIdURL(String mediaId) {
        // example: https://imdb-api.com/en/API/Title/{{api_key}}/tt0461770
        return hosturl + "en/API/Title/" + apiKey + "/" + mediaId;
    }

    private String getSearchMediaByTitleURL(String mediaTitle) {
        // example: https://imdb-api.com/API/SearchTitle/{{api_key}}/Enchanted
        return hosturl + "API/SearchTitle/" + apiKey + "/" + mediaTitle;
    }

}

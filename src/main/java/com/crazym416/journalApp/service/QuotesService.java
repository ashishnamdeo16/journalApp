package com.crazym416.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;


@Component
@Slf4j
public class QuotesService {

    @Value("${api.ninjas.key}")
    private String API_KEY;

    @Value("${api.ninjas.quotes-url}")
    private String API;

    @Autowired
    private RestTemplate restTemplate;

    public @Nullable String getQuote(){

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", API_KEY);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                API,
                HttpMethod.GET,
                entity,
                String.class
        );

        try {
            // Parse JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            // The API returns an array, take the first element
            if (root.isArray() && root.size() > 0) {
                return root.get(0).get("quote").asText();
            }
        } catch (Exception e) {
            log.error("Unexpected error occurred", e);
            throw new RuntimeException("Something went wrong", e);
        }

        return null;
    }
}

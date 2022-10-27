package com.multithread.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.multithread.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class RestServiceImpl implements RestService {
    @Autowired
    @Qualifier("customRestTemplate")
    private RestTemplate restTemplate;

    @Override
    public JsonNode getUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        String url = "http://localhost:9191/swagger2/api/getUser";
        return restTemplate.postForObject(url, request, JsonNode.class);
    }

    @Override
    public JsonNode getClient() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        String url = "http://localhost:9191/swagger2/api/getClient";
        return restTemplate.postForObject(url, request, JsonNode.class);
    }

    @Async
    @Override
    public CompletableFuture<JsonNode> getUserAsync() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        String url = "http://localhost:9191/swagger2/api/getUser";
        JsonNode response = restTemplate.postForObject(url, request, JsonNode.class);
        return CompletableFuture.completedFuture(response);
    }

    @Async
    @Override
    public CompletableFuture<JsonNode> getClientAsync() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        String url = "http://localhost:9191/swagger2/api/getClient";
        JsonNode response = restTemplate.postForObject(url, request, JsonNode.class);
        return CompletableFuture.completedFuture(response);
    }
}

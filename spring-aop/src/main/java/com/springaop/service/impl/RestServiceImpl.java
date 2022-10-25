package com.springaop.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.springaop.service.ApiService;
import com.springaop.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

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
        JsonNode response = restTemplate.postForObject(url, request, JsonNode.class);
        return response;
    }

    @Override
    public JsonNode getClient() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        String url = "http://localhost:9191/swagger2/api/getClient";
        JsonNode response = restTemplate.postForObject(url, request, JsonNode.class);
        return response;
    }
}

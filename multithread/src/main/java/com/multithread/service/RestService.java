package com.multithread.service;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.concurrent.CompletableFuture;

public interface RestService {
    public JsonNode getUser();
    public JsonNode getClient();

    public CompletableFuture<JsonNode> getUserAsync();
    public CompletableFuture<JsonNode> getClientAsync();
}

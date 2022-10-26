package com.multithread.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface RestService {
    public JsonNode getUser();
    public JsonNode getClient();
}

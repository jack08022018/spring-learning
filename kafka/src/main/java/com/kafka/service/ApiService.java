package com.kafka.service;

import com.kafka.dto.Greeting;

public interface ApiService {
    void jackTopicListener(String message);
    void greetingTopicListener(Greeting greeting);
}

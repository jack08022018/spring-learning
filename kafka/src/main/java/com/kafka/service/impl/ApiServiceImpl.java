package com.kafka.service.impl;

import com.kafka.dto.Greeting;
import com.kafka.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl implements ApiService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    @KafkaListener(topics = "${topic.jack}",
            groupId = "foo",
            containerFactory = "listenerFactoryFoo")
    public void jackTopicListener(String message) {
        System.out.println("Received Message: " + message);
    }

    @Override
    @KafkaListener(topics = "${topic.greeting}",
            containerFactory = "listenerFactoryGreeting")
    public void greetingTopicListener(Greeting greeting) {
        System.out.println("Received greeting message: " + greeting);
//        this.greetingLatch.countDown();
    }
}

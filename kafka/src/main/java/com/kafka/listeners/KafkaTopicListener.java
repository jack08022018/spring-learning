package com.kafka.listeners;

import com.kafka.dto.Greeting;

public interface KafkaTopicListener {
    void stringTopic(String message);
    void stringTopicBar(String message);
    void greetingTopic(Greeting greeting);
    void multiTypeTopic(Greeting object);
}

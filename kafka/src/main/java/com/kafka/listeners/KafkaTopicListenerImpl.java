package com.kafka.listeners;

import com.kafka.dto.Greeting;
import com.kafka.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Component
public class KafkaTopicListenerImpl implements KafkaTopicListener {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(
            topics = "${topic.string}",
//            groupId = "consumer_group_string_1",
            containerFactory = "consumer_group_string_1")
    public void stringTopic(String message) {
        System.out.println("Received Message 1: " + message);
    }

    @KafkaListener(topics = "${topic.string}", containerFactory = "consumer_group_string_1")
    public void stringTopicBar(String message) {
        System.out.println("Received Message 2: " + message);
    }

//    @RetryableTopic(
//            attempts = "2",
//            backoff = @Backoff(delay = 300000, multiplier = 10.0),
//            autoCreateTopics = "false",
//            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
//    )
    @KafkaListener(topics = "${topic.greeting}", containerFactory = "consumer_group_greeting")
    public void greetingTopic(Greeting greeting) {
        System.out.println("Received greeting message: " + greeting);
//        this.greetingLatch.countDown();
    }

    @Override
    @KafkaListener(topics = "${topic.multiType}", containerFactory = "consumer_group_multiType")
    public void multiTypeTopic(Greeting object) {
        System.out.println("Received multiType message: " + object);
    }

}

package com.kafka.controller;


import com.kafka.dto.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Greeting> greetingKafkaTemplate;

    @Value(value = "${topic.jack}")
    private String topicJack;

    @Value(value = "${topic.greeting}")
    private String topicGreeting;

    @GetMapping(value = "/sendMessage")
    public <T> T sendMessage(@RequestParam("title") String title) throws Exception {
//        kafkaTemplate.send(topicJack, title);
        greetingKafkaTemplate.send(topicGreeting, new Greeting("Greetings", "World!"));
        return null;
//        return apiService.getRentalMovies(title);
    }

}

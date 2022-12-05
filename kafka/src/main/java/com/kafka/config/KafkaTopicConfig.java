package com.kafka.config;

import java.util.HashMap;
import java.util.Map;

import com.kafka.common.FunctionCommonUtils;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${topic.jack}")
    private String topicJack;

    @Value(value = "${topic.greeting}")
    private String topicGreeting;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicJack() {
        return new NewTopic(topicJack, 1, (short) 1);
    }

    @Bean
    public NewTopic topicGreeting() {
        return new NewTopic(topicGreeting, 1, (short) 1);
    }

//    @Bean
//    public NewTopic topic2() {
//        return new NewTopic(partitionedTopicName, 6, (short) 1);
//    }
//
//    @Bean
//    public NewTopic topic3() {
//        return new NewTopic(filteredTopicName, 1, (short) 1);
//    }
//
//
//    @Bean
//    public NewTopic topic5() {
//        NewTopic newTopic = new NewTopic(longMsgTopicName, 1, (short) 1);
//        Map<String, String> configs = new HashMap<>();
//        configs.put("max.message.bytes", "20971520");
//        newTopic.configs(configs);
//        return newTopic;
//    }
//
//    @Bean
//    public NewTopic multiTypeTopic() {
//        return new NewTopic(multiTypeTopicName, 1, (short) 1);
//    }
}

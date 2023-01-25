package com.lehit.profiles.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.kafka.topic")
public record KafkaTopicProperties(String name, int replicas, int partitions){
}

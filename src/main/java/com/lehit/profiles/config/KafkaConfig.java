package com.lehit.profiles.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "spring.kafka.enabled", havingValue = "true")
public class KafkaConfig {
    private final KafkaTopicProperties topicProperties;

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Autowired
    public KafkaConfig(KafkaTopicProperties topicProperties) {
        this.topicProperties = topicProperties;
    }


    @Bean
    public NewTopic profilesTopic() {
        return TopicBuilder.name(topicProperties.name())
                .partitions(topicProperties.partitions())
                .replicas(topicProperties.replicas())
                .build();
    }
}

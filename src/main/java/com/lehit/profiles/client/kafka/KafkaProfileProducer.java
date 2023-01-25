package com.lehit.profiles.client.kafka;

import com.lehit.common.enums.KafkaAction;
import com.lehit.profiles.model.UserProfile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
@Getter
public class KafkaProfileProducer {
    @Value("${spring.kafka.topic.name}")
    private String profilesTopicName;

    private final KafkaTemplate<String, UserProfile> kafkaTemplate;

    public UserProfile send(UserProfile message, String topicName){

        kafkaTemplate.send(topicName, message.getId().toString(), message);

        return message;
    }

    public UserProfile sendCreateEvent(UserProfile profile){
        return sendWithHeader(profile, profilesTopicName, KafkaAction.CREATE);
    }

    public UserProfile sendDeleteEvent(UserProfile profile){
        return sendWithHeader(profile, profilesTopicName, KafkaAction.DELETE);
    }


    private UserProfile sendWithHeader(UserProfile message, String topicName, KafkaAction action){
        var producerRecord = new ProducerRecord<>(topicName, message.getId().toString(), message);
        producerRecord.headers().add("__Action__", action.toString().getBytes(StandardCharsets.UTF_8));

//        todo check for erros
        kafkaTemplate.send(producerRecord);

        return message;
    }

}

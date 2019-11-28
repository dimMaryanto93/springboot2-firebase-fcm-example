package com.maryanto.dimas.example.controller;

import com.google.pubsub.v1.Subscription;
import com.google.pubsub.v1.Topic;
import com.maryanto.dimas.example.dto.NotificationDTO;
import com.maryanto.dimas.example.dto.TopicDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.pubsub.PubSubAdmin;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/fcm")
public class FirebaseController {

    @Autowired
    private PubSubTemplate pubSubTemplate;

    @Autowired
    private PubSubAdmin pubSubAdmin;

    @PostMapping("/create-topic")
    public ResponseEntity<?> createTopic(@RequestBody TopicDTO value) {
        if (pubSubAdmin.getTopic(value.getName()) != null) {
            log.error("topic name {} already exist!", value.getName());
            return ResponseEntity.noContent().build();
        }

        try {
            Topic topic = this.pubSubAdmin.createTopic(value.getName());
            log.info("topic: {}, kmsKeyName: {}", topic.getName(), topic.getKmsKeyName());
            return ResponseEntity.ok().build();
        } catch (io.grpc.StatusRuntimeException gcsre) {
            log.error("error creating topic: {}", gcsre.getLocalizedMessage(), gcsre);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/push-notification-by-topic")
    public ResponseEntity<?> pushByTopic(@RequestBody NotificationDTO value) {
        Topic topic = pubSubAdmin.getTopic(value.getTopic());
        if (topic == null) {
            log.error("topic name {} not exist!", value.getTopic());
            return ResponseEntity.noContent().build();
        }

        pubSubTemplate.publish(topic.getName(), value.getMessage());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sub-by-topic")
    public ResponseEntity<?> subcribeByTopic(@RequestParam String topicName) {
        Topic topic = pubSubAdmin.getTopic(topicName);
        if (topic == null) {
            log.error("topic name {} not exist!", topicName);
            return ResponseEntity.noContent().build();
        }

        String subcribeId = UUID.randomUUID().toString();
        Subscription subscription = pubSubAdmin.createSubscription(subcribeId, topicName);
        log.info("subscribe: {}", subscription.getName());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/send")
    public ResponseEntity<?> send() {

        return ResponseEntity.ok().build();
    }
}

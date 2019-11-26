package com.maryanto.dimas.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.pubsub.PubSubAdmin;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fcm")
public class FirebaseController {

    @Autowired
    private PubSubTemplate pubSubTemplate;

    @Autowired
    private PubSubAdmin pubSubAdmin;


    @GetMapping("/send")
    public ResponseEntity<?> send() {

        return ResponseEntity.ok().build();
    }
}

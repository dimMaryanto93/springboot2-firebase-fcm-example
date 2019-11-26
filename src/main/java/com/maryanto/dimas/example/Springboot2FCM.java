package com.maryanto.dimas.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
public class Springboot2FCM {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2FCM.class, args);
	}

}

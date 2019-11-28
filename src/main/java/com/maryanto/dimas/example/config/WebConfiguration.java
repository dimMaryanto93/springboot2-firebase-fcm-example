package com.maryanto.dimas.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gcp.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.springframework.cloud.gcp.pubsub.support.converter.PubSubMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public PubSubMessageConverter pubSubMessageConverter() {
        return new JacksonPubSubMessageConverter(new ObjectMapper());
    }
}

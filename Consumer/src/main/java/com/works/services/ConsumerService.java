package com.works.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    // @Value("${spring.kafka.producer.topic}")
    // private String topicName;

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}" )
    public void listen( String message ) {
        System.out.println( message );
    }

}

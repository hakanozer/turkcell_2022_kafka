package com.works.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.props.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    final ObjectMapper objectMapper;

    // @Value("${spring.kafka.producer.topic}")
    // private String topicName;

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}" )
    public void listen( String message ) throws JsonProcessingException {
        Message messageObj = objectMapper.readValue(message, Message.class);
        System.out.println( messageObj.getId() );
        System.out.println( messageObj.getName() );
        System.out.println( messageObj.getText() );
    }

}

package com.kafka.echo.producer.controllers;

import com.kafka.echo.producer.dto.Address;
import com.kafka.echo.producer.dto.UserDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("producer")
public class ProducerController {

    final KafkaTemplate<Long, UserDto> kafkaTemplate;

    public ProducerController(KafkaTemplate<Long, UserDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void sendMessage (Long msgID, String message) {
        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("Producer",msgID, UserDto.builder().
                name(message).address(Address.builder().country("ХУЙ").build()).build());
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}

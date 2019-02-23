package com.romanidze.reactivchinaconsumer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanidze.reactivchinaconsumer.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.kafka.receiver.internals.DefaultKafkaReceiver;

import java.io.IOException;

/**
 * 17.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@RequestMapping(path = "/result")
@RestController
public class KafkaConsumerController {

    private final DefaultKafkaReceiver<String, String> kafkaReceiver;
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaConsumerController(DefaultKafkaReceiver<String, String> kafkaReceiver, ObjectMapper objectMapper) {
        this.kafkaReceiver = kafkaReceiver;
        this.objectMapper = objectMapper;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserDTO> getResult(){

        Flux<ReceiverRecord<String, String>> kafkaFlux = this.kafkaReceiver.receive();

        return kafkaFlux.log()
                        .doOnNext(r -> r.receiverOffset().acknowledge())
                        .map(ReceiverRecord::value)
                        .map(item -> {

                            UserDTO userDTO = null;

                            try {
                                userDTO =  this.objectMapper.readValue(item, UserDTO.class);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            return userDTO;
                        });

    }

}

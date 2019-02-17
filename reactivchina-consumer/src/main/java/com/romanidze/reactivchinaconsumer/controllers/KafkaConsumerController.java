package com.romanidze.reactivchinaconsumer.controllers;

import com.romanidze.reactivchinaconsumer.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.kafka.receiver.internals.DefaultKafkaReceiver;

/**
 * 17.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@RequestMapping(path = "/result")
@RestController
public class KafkaConsumerController {

    private final DefaultKafkaReceiver<String, UserDTO> kafkaReceiver;

    @Autowired
    public KafkaConsumerController(DefaultKafkaReceiver<String, UserDTO> kafkaReceiver) {
        this.kafkaReceiver = kafkaReceiver;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserDTO> getResult(){

        Flux<ReceiverRecord<String, UserDTO>> kafkaFlux = this.kafkaReceiver.receive();

        return kafkaFlux.log()
                        .doOnNext(r -> r.receiverOffset().acknowledge())
                        .map(ReceiverRecord::value);

    }

}

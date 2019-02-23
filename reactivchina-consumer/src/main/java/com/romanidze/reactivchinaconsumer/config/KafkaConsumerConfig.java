package com.romanidze.reactivchinaconsumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.internals.ConsumerFactory;
import reactor.kafka.receiver.internals.DefaultKafkaReceiver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Configuration
public class KafkaConsumerConfig {

    private final KafkaConsumerProperties kafkaConsumerProperties;

    @Autowired
    public KafkaConsumerConfig(KafkaConsumerProperties kafkaConsumerProperties) {
        this.kafkaConsumerProperties = kafkaConsumerProperties;
    }

    @Bean
    public DefaultKafkaReceiver kafkaReceiver(){

        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaConsumerProperties.getBootstrapServers());
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, this.kafkaConsumerProperties.getOffsetType());
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, this.kafkaConsumerProperties.getGroupID());
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, this.kafkaConsumerProperties.getKeyDeserializer());
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, this.kafkaConsumerProperties.getValueDeserializer());

        return new DefaultKafkaReceiver<>(ConsumerFactory.INSTANCE,
                                        ReceiverOptions.create(configProps)
                                                       .subscription(List.of(this.kafkaConsumerProperties.getTopicName())));

    }

}

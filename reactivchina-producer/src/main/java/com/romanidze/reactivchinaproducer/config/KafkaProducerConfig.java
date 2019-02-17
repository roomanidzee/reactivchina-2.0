package com.romanidze.reactivchinaproducer.config;

import com.romanidze.reactivchinaproducer.dto.UserDTO;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * 17.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Configuration
public class KafkaProducerConfig {

    private final KafkaProducerProperties kafkaProducerProperties;

    @Autowired
    public KafkaProducerConfig(KafkaProducerProperties kafkaProducerProperties) {
        this.kafkaProducerProperties = kafkaProducerProperties;
    }

    @Bean
    public ProducerFactory<String, UserDTO> producerFactory() {

        Map<String, Object> configProps = new HashMap<>();

        
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaProducerProperties.getBootstrapServers());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, this.kafkaProducerProperties.getKeySerializer());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, this.kafkaProducerProperties.getValueSerializer());

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, UserDTO> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

}

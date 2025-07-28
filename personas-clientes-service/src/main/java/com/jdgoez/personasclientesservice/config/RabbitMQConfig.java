package com.jdgoez.personasclientesservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "cliente.direct";
    public static final String QUEUE_NAME = "cliente.request.queue";
    public static final String ROUTING_KEY = "cliente.request";

    @Bean
    public DirectExchange clienteExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue clienteRequestQueue() {
        return new Queue(QUEUE_NAME, true); // durable
    }

    @Bean
    public Binding clienteBinding(Queue clienteRequestQueue, DirectExchange clienteExchange) {
        return BindingBuilder.bind(clienteRequestQueue).to(clienteExchange).with(ROUTING_KEY);
    }
}
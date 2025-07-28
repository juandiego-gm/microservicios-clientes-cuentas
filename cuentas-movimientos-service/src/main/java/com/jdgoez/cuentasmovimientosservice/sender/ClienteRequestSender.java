package com.jdgoez.cuentasmovimientosservice.sender;

import com.jdgoez.cuentasmovimientosservice.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClienteRequestSender {

    private final RabbitTemplate rabbitTemplate;

    public ClienteRequestSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarClienteId(Long clienteId) {
        System.out.println("📤 Enviando solicitud de cliente con ID: " + clienteId);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                clienteId
        );
    }
}
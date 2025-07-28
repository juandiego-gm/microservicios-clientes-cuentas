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

    public boolean validarCliente(Long clienteId) {
        System.out.println("📤 Validando cliente con ID: " + clienteId);

        String respuesta = (String) rabbitTemplate.convertSendAndReceive(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                clienteId
        );

        return "true".equalsIgnoreCase(respuesta);
    }

}
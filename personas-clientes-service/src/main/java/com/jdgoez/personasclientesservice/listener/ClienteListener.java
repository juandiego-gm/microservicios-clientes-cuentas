package com.jdgoez.personasclientesservice.listener;

import com.jdgoez.personasclientesservice.config.RabbitMQConfig;
import com.jdgoez.personasclientesservice.repository.ClienteRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ClienteListener {

    private final ClienteRepository clienteRepository;

    public ClienteListener(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public String validarCliente(Long clienteId) {
        System.out.println("📥 Validando cliente con ID: " + clienteId);
        boolean existe = clienteRepository.existsById(clienteId);
        return existe ? "true" : "false";
    }



}
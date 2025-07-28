package com.jdgoez.personasclientesservice.listener;

import com.jdgoez.personasclientesservice.config.RabbitMQConfig;
import com.jdgoez.personasclientesservice.dto.ClienteDTO;
import com.jdgoez.personasclientesservice.model.Cliente;
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
    public boolean verificarCliente(Long clienteId) {
        boolean existe = clienteRepository.existsById(clienteId);
        System.out.println("🔍 Verificación de cliente [" + clienteId + "]: " + (existe ? "Existe" : "No existe"));
        return existe;
    }

}
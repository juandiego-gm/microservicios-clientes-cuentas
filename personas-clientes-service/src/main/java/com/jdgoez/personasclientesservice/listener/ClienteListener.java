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
    public void recibirSolicitudCliente(Long clienteId) {
        System.out.println("📥 Mensaje recibido en personas-clientes-service: ID cliente = " + clienteId);

        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);

        if (cliente != null) {
            ClienteDTO clienteDTO = new ClienteDTO(
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getIdentificacion()
            );
            System.out.println("✅ Cliente encontrado: " + clienteDTO.getNombre() + " [" + clienteDTO.getIdentificacion() + "]");
        } else {
            System.out.println("❌ Cliente no encontrado con ID: " + clienteId);
        }
    }
}
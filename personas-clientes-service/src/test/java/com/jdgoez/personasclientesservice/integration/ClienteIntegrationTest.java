package com.jdgoez.personasclientesservice.integration;

import com.jdgoez.personasclientesservice.model.Cliente;
import com.jdgoez.personasclientesservice.repository.ClienteRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClienteIntegrationTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @Transactional // Esto asegura rollback al terminar el test
    void crearYLeerCliente() {
        // Crear cliente con datos heredados de Persona
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Test");
        cliente.setIdentificacion("9999999999");
        cliente.setGenero("Masculino");
        cliente.setEdad(28);
        cliente.setDireccion("Calle Falsa 123");
        cliente.setTelefono("0987654321");
        cliente.setPassword("claveSegura123");
        cliente.setEstado(true);

        Cliente guardado = clienteRepository.save(cliente);

        // Leerlo nuevamente
        Cliente encontrado = clienteRepository.findById(guardado.getId()).orElse(null);

        // Verificar
        assertThat(encontrado).isNotNull();
        assertThat(encontrado.getNombre()).isEqualTo("Juan Test");
        assertThat(encontrado.getPassword()).isEqualTo("claveSegura123");
    }
}

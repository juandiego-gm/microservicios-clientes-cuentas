package com.jdgoez.personasclientesservice.service;

import com.jdgoez.personasclientesservice.exception.ClienteNoEncontradoException;
import com.jdgoez.personasclientesservice.exception.IdentificacionDuplicadaException;
import com.jdgoez.personasclientesservice.model.Cliente;
import com.jdgoez.personasclientesservice.repository.ClienteRepository;
import com.jdgoez.personasclientesservice.service.impl.ClienteServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearCliente_exitoso() {
        Cliente cliente = new Cliente();
        cliente.setIdentificacion("123456789");

        when(clienteRepository.findByIdentificacion("123456789")).thenReturn(Optional.empty());
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente creado = clienteService.crearCliente(cliente);

        assertEquals("123456789", creado.getIdentificacion());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void crearCliente_identificacionDuplicada() {
        Cliente cliente = new Cliente();
        cliente.setIdentificacion("123");

        when(clienteRepository.findByIdentificacion("123"))
                .thenReturn(Optional.of(new Cliente()));

        assertThrows(IdentificacionDuplicadaException.class, () -> clienteService.crearCliente(cliente));
    }

    @Test
    void obtenerCliente_existente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente encontrado = clienteService.obtenerCliente(1L);

        assertEquals(1L, encontrado.getId());
    }

    @Test
    void obtenerCliente_noExiste() {
        when(clienteRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ClienteNoEncontradoException.class, () -> clienteService.obtenerCliente(999L));
    }

    @Test
    void eliminarCliente() {
        clienteService.eliminarCliente(1L);
        verify(clienteRepository).deleteById(1L);
    }

    @Test
    void listarClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(new Cliente(), new Cliente()));
        List<Cliente> clientes = clienteService.listarClientes();
        assertEquals(2, clientes.size());
    }
}

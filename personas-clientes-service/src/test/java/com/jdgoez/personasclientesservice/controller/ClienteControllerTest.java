package com.jdgoez.personasclientesservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdgoez.personasclientesservice.exception.ClienteNoEncontradoException;
import com.jdgoez.personasclientesservice.model.Cliente;
import com.jdgoez.personasclientesservice.service.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setIdentificacion("123456");
        cliente.setPassword("clave123");
        cliente.setEstado(true);
    }

    @Test
    void crearCliente_debeRetornar201() throws Exception {
        when(clienteService.crearCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void obtenerCliente_existente() throws Exception {
        when(clienteService.obtenerCliente(1L)).thenReturn(cliente);

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identificacion").value("123456"));
    }

    @Test
    void obtenerCliente_noExiste() throws Exception {
        when(clienteService.obtenerCliente(999L)).thenThrow(new ClienteNoEncontradoException("No encontrado"));

        mockMvc.perform(get("/clientes/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void listarClientes() throws Exception {
        when(clienteService.listarClientes()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void eliminarCliente() throws Exception {
        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isNoContent());

        verify(clienteService, times(1)).eliminarCliente(1L);
    }

    @Test
    void actualizarCliente() throws Exception {
        Cliente clienteActualizado = new Cliente();
        clienteActualizado.setNombre("Actualizado");
        clienteActualizado.setGenero("Masculino");
        clienteActualizado.setEdad(30);
        clienteActualizado.setIdentificacion("9999999999");
        clienteActualizado.setDireccion("Nueva dirección");
        clienteActualizado.setTelefono("0987654321");
        clienteActualizado.setPassword("nuevo123");
        clienteActualizado.setEstado(false);

        String clienteJson = objectMapper.writeValueAsString(clienteActualizado);

        mockMvc.perform(put("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isOk());
    }

}

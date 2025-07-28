package com.jdgoez.cuentasmovimientosservice.service.impl;


import com.jdgoez.cuentasmovimientosservice.exception.ClienteNoEncontradoException;
import com.jdgoez.cuentasmovimientosservice.exception.CuentaNoEncontradaException;
import com.jdgoez.cuentasmovimientosservice.model.Cuenta;
import com.jdgoez.cuentasmovimientosservice.repository.CuentaRepository;
import com.jdgoez.cuentasmovimientosservice.sender.ClienteRequestSender;
import com.jdgoez.cuentasmovimientosservice.service.CuentaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRequestSender clienteRequestSender;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, ClienteRequestSender clienteRequestSender) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRequestSender = clienteRequestSender;
    }

    @Override
    public Cuenta crear(Cuenta cuenta) {
        if (!clienteRequestSender.validarCliente(cuenta.getClienteId())) {
            throw new ClienteNoEncontradoException("El cliente con ID " + cuenta.getClienteId() + " no existe.");
        }

        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta obtener(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta con ID " + id + " no encontrada"));
    }

    @Override
    public List<Cuenta> listar() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta actualizar(Long id, Cuenta cuentaActualizada) {
        Cuenta cuenta = obtener(id);
        cuenta.setNumeroCuenta(cuentaActualizada.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaActualizada.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaActualizada.getSaldoInicial());
        cuenta.setEstado(cuentaActualizada.getEstado());
        cuenta.setClienteId(cuentaActualizada.getClienteId());
        return cuentaRepository.save(cuenta);
    }

    @Override
    public void eliminar(Long id) {
        cuentaRepository.deleteById(id);
    }
}

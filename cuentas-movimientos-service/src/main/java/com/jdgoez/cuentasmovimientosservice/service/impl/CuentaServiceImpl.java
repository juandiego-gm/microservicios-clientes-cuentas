package com.jdgoez.cuentasmovimientosservice.service.impl;


import com.jdgoez.cuentasmovimientosservice.exception.CuentaNoEncontradaException;
import com.jdgoez.cuentasmovimientosservice.model.Cuenta;
import com.jdgoez.cuentasmovimientosservice.repository.CuentaRepository;
import com.jdgoez.cuentasmovimientosservice.service.CuentaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public Cuenta crear(Cuenta cuenta) {
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

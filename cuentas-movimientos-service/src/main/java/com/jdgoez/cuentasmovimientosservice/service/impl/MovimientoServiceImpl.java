package com.jdgoez.cuentasmovimientosservice.service.impl;


import com.jdgoez.cuentasmovimientosservice.exception.CuentaNoEncontradaException;
import com.jdgoez.cuentasmovimientosservice.exception.SaldoInsuficienteException;
import com.jdgoez.cuentasmovimientosservice.model.Cuenta;
import com.jdgoez.cuentasmovimientosservice.model.Movimiento;
import com.jdgoez.cuentasmovimientosservice.repository.CuentaRepository;
import com.jdgoez.cuentasmovimientosservice.repository.MovimientoRepository;
import com.jdgoez.cuentasmovimientosservice.service.MovimientoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public Movimiento registrar(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuentaId())
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta no encontrada"));

        Double nuevoSaldo = cuenta.getSaldoInicial();
        if (movimiento.getTipoMovimiento().equalsIgnoreCase("RETIRO")) {
            if (movimiento.getValor() > nuevoSaldo) {
                throw new SaldoInsuficienteException("Saldo no disponible");
            }
            nuevoSaldo -= movimiento.getValor();
        } else if (movimiento.getTipoMovimiento().equalsIgnoreCase("DEPOSITO")) {
            nuevoSaldo += movimiento.getValor();
        } else {
            throw new RuntimeException("Tipo de movimiento no soportado");
        }

        movimiento.setSaldo(nuevoSaldo);
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        return movimientoRepository.save(movimiento);
    }

    @Override
    public List<Movimiento> listarPorCuenta(Long cuentaId) {
        return movimientoRepository.findByCuentaId(cuentaId);
    }
}

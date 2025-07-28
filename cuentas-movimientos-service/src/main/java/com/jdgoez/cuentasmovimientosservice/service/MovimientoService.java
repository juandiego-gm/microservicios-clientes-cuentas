package com.jdgoez.cuentasmovimientosservice.service;


import com.jdgoez.cuentasmovimientosservice.model.Movimiento;

import java.util.List;

public interface MovimientoService {
    Movimiento registrar(Movimiento movimiento);
    List<Movimiento> listarPorCuenta(Long cuentaId);
}

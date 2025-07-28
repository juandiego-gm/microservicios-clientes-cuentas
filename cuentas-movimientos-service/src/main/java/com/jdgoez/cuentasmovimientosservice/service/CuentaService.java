package com.jdgoez.cuentasmovimientosservice.service;

import com.jdgoez.cuentasmovimientosservice.model.Cuenta;

import java.util.List;

public interface CuentaService {
    Cuenta crear(Cuenta cuenta);
    Cuenta obtener(Long id);
    List<Cuenta> listar();
    Cuenta actualizar(Long id, Cuenta cuenta);
    void eliminar(Long id);
}
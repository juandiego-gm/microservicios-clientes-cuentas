package com.jdgoez.cuentasmovimientosservice.repository;

import com.jdgoez.cuentasmovimientosservice.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaId(Long cuentaId);
}
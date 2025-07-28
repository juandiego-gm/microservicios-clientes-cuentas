package com.jdgoez.cuentasmovimientosservice.repository;

import com.jdgoez.cuentasmovimientosservice.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
}
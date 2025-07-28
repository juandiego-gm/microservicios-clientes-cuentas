package com.jdgoez.cuentasmovimientosservice.controller;

import com.jdgoez.cuentasmovimientosservice.model.Movimiento;
import com.jdgoez.cuentasmovimientosservice.service.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public Movimiento registrar(@Valid @RequestBody Movimiento movimiento) {
        return movimientoService.registrar(movimiento);
    }

    @GetMapping("/cuenta/{cuentaId}")
    public List<Movimiento> listarPorCuenta(@PathVariable Long cuentaId) {
        return movimientoService.listarPorCuenta(cuentaId);
    }
}

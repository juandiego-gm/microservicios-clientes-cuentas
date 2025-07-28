package com.jdgoez.cuentasmovimientosservice.controller;

import com.jdgoez.cuentasmovimientosservice.model.Cuenta;
import com.jdgoez.cuentasmovimientosservice.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public Cuenta crear(@Valid @RequestBody Cuenta cuenta) {
        return cuentaService.crear(cuenta);
    }

    @GetMapping
    public List<Cuenta> listar() {
        return cuentaService.listar();
    }

    @GetMapping("/{id}")
    public Cuenta obtener(@PathVariable Long id) {
        return cuentaService.obtener(id);
    }

    @PutMapping("/{id}")
    public Cuenta actualizar(@PathVariable Long id, @Valid @RequestBody Cuenta cuenta) {
        return cuentaService.actualizar(id, cuenta);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cuentaService.eliminar(id);
    }
}

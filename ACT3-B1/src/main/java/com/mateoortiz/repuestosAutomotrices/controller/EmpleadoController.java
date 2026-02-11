package com.mateoortiz.repuestosAutomotrices.controller;

import com.mateoortiz.repuestosAutomotrices.entity.Empleado;
import com.mateoortiz.repuestosAutomotrices.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")

public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService){ this.empleadoService=empleadoService;}

    @GetMapping
    public List<Empleado> getAllEmpleados(){return empleadoService.getAllEmpleados();}

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmpleadoById(@PathVariable Integer id) {
        Empleado empleado = empleadoService.getEmpleadoById(id);
        return ResponseEntity.ok(empleado);
    }

    @PostMapping
    public ResponseEntity<Object> createEmpleado(@Valid @RequestBody Empleado empleado){
        try{
            Empleado createdEmpleado = empleadoService.saveEmpleado(empleado);
            return new ResponseEntity<>(createdEmpleado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado){
        Empleado updatedEmpleado = empleadoService.updateEmpleado(id, empleado);
        return ResponseEntity.ok(updatedEmpleado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Integer id){
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.ok().build();
    }
}

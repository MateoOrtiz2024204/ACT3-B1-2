package com.mateoortiz.repuestosAutomotrices.controller;

import com.mateoortiz.repuestosAutomotrices.entity.Ventas;
import com.mateoortiz.repuestosAutomotrices.service.VentasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {

    private final VentasService ventasService;

    public VentasController(VentasService ventasService){
        this.ventasService = ventasService;
    }

    @GetMapping
    public List<Ventas> getAllVentas(){
        return ventasService.getAllVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ventas> getVentasById(@PathVariable Integer id){
        Ventas ventas = ventasService.getVentasById(id);
        return ResponseEntity.ok(ventas);
    }

    @PostMapping
    public ResponseEntity<Object> createVentas(@Valid @RequestBody Ventas ventas){
        try{
            Ventas createdVentas = ventasService.saveVentas(ventas);
            return new ResponseEntity<>(createdVentas, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ventas> updateVentas(@PathVariable Integer id, @RequestBody Ventas ventas){
        Ventas updatedVentas = ventasService.updateVentas(id, ventas);
        return ResponseEntity.ok(updatedVentas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVentas(@PathVariable Integer id){
        ventasService.deleteVentas(id);
        return ResponseEntity.ok().build();
    }
}

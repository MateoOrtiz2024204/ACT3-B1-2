package com.mateoortiz.repuestosAutomotrices.controller;

import com.mateoortiz.repuestosAutomotrices.entity.Ventas;
import com.mateoortiz.repuestosAutomotrices.service.VentasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {

    private final VentasService ventasService;

    public VentasController(VentasService ventasService){
        this.ventasService = ventasService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllVentas(){
        try {
            List<Ventas> ventas = ventasService.getAllVentas();
            return ResponseEntity.ok(ventas);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al obtener las ventas");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVentasById(@PathVariable Integer id){
        try {
            Ventas ventas = ventasService.getVentasById(id);
            if (ventas == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Venta no encontrada");
                error.put("mensaje", "No existe una venta con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(ventas);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al buscar la venta");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createVentas(@Valid @RequestBody Ventas ventas){
        try {
            // Validar que el empleado exista
            if (ventas.getEmpleado() == null || ventas.getEmpleado().getIdEmpleado() == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Empleado inválido");
                error.put("mensaje", "Debe proporcionar un ID de empleado válido");
                return ResponseEntity.badRequest().body(error);
            }

            // Validar que el repuesto exista
            if (ventas.getRepuestos() == null || ventas.getRepuestos().getIdRepuesto() == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Repuesto inválido");
                error.put("mensaje", "Debe proporcionar un ID de repuesto válido");
                return ResponseEntity.badRequest().body(error);
            }

            Ventas createdVentas = ventasService.saveVentas(ventas);
            return new ResponseEntity<>(createdVentas, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear la venta");
            error.put("mensaje", e.getMessage());

            // Si el error es por foreign key (empleado o repuesto no existe)
            if (e.getMessage().contains("foreign key") || e.getMessage().contains("constraint")) {
                error.put("mensaje", "El empleado o repuesto especificado no existe en la base de datos");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVentas(@PathVariable Integer id, @Valid @RequestBody Ventas ventas){
        try {
            // Validar que la venta exista
            Ventas ventaExistente = ventasService.getVentasById(id);
            if (ventaExistente == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Venta no encontrada");
                error.put("mensaje", "No existe una venta con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            // Validar empleado
            if (ventas.getEmpleado() == null || ventas.getEmpleado().getIdEmpleado() == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Empleado inválido");
                error.put("mensaje", "Debe proporcionar un ID de empleado válido");
                return ResponseEntity.badRequest().body(error);
            }

            // Validar repuesto
            if (ventas.getRepuestos() == null || ventas.getRepuestos().getIdRepuesto() == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Repuesto inválido");
                error.put("mensaje", "Debe proporcionar un ID de repuesto válido");
                return ResponseEntity.badRequest().body(error);
            }

            Ventas updatedVentas = ventasService.updateVentas(id, ventas);
            return ResponseEntity.ok(updatedVentas);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar la venta");
            error.put("mensaje", e.getMessage());

            if (e.getMessage().contains("foreign key") || e.getMessage().contains("constraint")) {
                error.put("mensaje", "El empleado o repuesto especificado no existe en la base de datos");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVentas(@PathVariable Integer id){
        try {
            Ventas ventaExistente = ventasService.getVentasById(id);
            if (ventaExistente == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Venta no encontrada");
                error.put("mensaje", "No existe una venta con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            ventasService.deleteVentas(id);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Venta eliminada exitosamente");
            respuesta.put("id", id.toString());
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al eliminar la venta");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Manejo de errores de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();
            errores.put(nombreCampo, mensajeError);
        });

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("error", "Datos de entrada inválidos");
        respuesta.put("campos", errores);

        return ResponseEntity.badRequest().body(respuesta);
    }
}
package com.mateoortiz.repuestosAutomotrices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "Ventas")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @Column(name = "fecha_venta")
    @NotNull(message = "La fecha no puede estar vacía")
    private LocalDate fechaVenta;

    @Column(name = "cantidad")
    @NotNull(message = "La cantidad no puede estar vacía")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidadRepuesto;

    @Column(name = "total")
    @NotNull(message = "El total no puede estar vacío")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
    private Double totalRepuesto;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    @NotNull(message = "El empleado no puede estar vacío")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_repuesto", nullable = false)
    @NotNull(message = "El repuesto no puede estar vacío")
    private Repuestos repuestos;

    // Getters y Setters
    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getCantidadRepuesto() {
        return cantidadRepuesto;
    }

    public void setCantidadRepuesto(Integer cantidadRepuesto) {
        this.cantidadRepuesto = cantidadRepuesto;
    }

    public Double getTotalRepuesto() {
        return totalRepuesto;
    }

    public void setTotalRepuesto(Double totalRepuesto) {
        this.totalRepuesto = totalRepuesto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Repuestos getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(Repuestos repuestos) {
        this.repuestos = repuestos;
    }
}
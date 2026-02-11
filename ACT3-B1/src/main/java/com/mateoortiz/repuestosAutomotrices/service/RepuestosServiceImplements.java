package com.mateoortiz.repuestosAutomotrices.service;

import com.mateoortiz.repuestosAutomotrices.entity.Repuestos;
import com.mateoortiz.repuestosAutomotrices.repository.RepuestosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepuestosServiceImplements implements RepuestosService {

    private final RepuestosRepository repuestosRepository;

    public RepuestosServiceImplements(RepuestosRepository repuestosRepository) {
        this.repuestosRepository = repuestosRepository;
    }

    @Override
    public List<Repuestos> getAllRepuestos() {
        return repuestosRepository.findAll();
    }

    @Override
    public Repuestos getRepuestoById(Integer id) {
        return repuestosRepository.findById(id).orElse(null);
    }

    @Override
    public Repuestos saveRepuesto(Repuestos repuesto) throws RuntimeException {
        return repuestosRepository.save(repuesto);
    }

    @Override
    public Repuestos updateRepuesto(Integer id, Repuestos repuesto) {
        Repuestos repuestoExistente = repuestosRepository.findById(id).orElse(null);
        if (repuestoExistente == null) {
            return null;
        }
        repuestoExistente.setNombreRepuesto(repuesto.getNombreRepuesto());
        repuestoExistente.setCategoriaRepuesto(repuesto.getCategoriaRepuesto());
        repuestoExistente.setPrecioCompra(repuesto.getPrecioCompra());
        repuestoExistente.setPrecioVenta(repuesto.getPrecioVenta());
        repuestoExistente.setProveedor(repuesto.getProveedor());
        return repuestosRepository.save(repuestoExistente);
    }

    @Override
    public void deleteRepuesto(Integer id) {
        repuestosRepository.deleteById(id);
    }
}

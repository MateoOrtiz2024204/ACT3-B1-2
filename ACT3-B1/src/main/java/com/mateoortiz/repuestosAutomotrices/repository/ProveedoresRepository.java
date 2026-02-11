package com.mateoortiz.repuestosAutomotrices.repository;

import com.mateoortiz.repuestosAutomotrices.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedor,Integer> {
}

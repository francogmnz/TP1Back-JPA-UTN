package com.example.TrabajoPracticiBack.repositorios;

import com.example.TrabajoPracticiBack.entidades.Factura;
import com.example.TrabajoPracticiBack.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}

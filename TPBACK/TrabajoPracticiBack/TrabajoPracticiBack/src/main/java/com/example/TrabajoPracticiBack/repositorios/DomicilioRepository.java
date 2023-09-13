package com.example.TrabajoPracticiBack.repositorios;

import com.example.TrabajoPracticiBack.entidades.Domicilio;
import com.example.TrabajoPracticiBack.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}

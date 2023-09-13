package com.example.TrabajoPracticiBack.repositorios;

import com.example.TrabajoPracticiBack.entidades.Cliente;
import com.example.TrabajoPracticiBack.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Object> findAllById(Long id);
}

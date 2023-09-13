package com.example.TrabajoPracticiBack.repositorios;

import com.example.TrabajoPracticiBack.entidades.Producto;
import com.example.TrabajoPracticiBack.entidades.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RubroRepository extends JpaRepository<Rubro, Long> {
}

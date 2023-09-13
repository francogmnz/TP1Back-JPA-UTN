package com.example.TrabajoPracticiBack.repositorios;

import com.example.TrabajoPracticiBack.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository; // le tengo qe proveer 2 parametros (Identidad, ID el tipo d dato)
import org.springframework.stereotype.Repository;

@Repository // le dice q se comporte como un repositorio de spring
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}

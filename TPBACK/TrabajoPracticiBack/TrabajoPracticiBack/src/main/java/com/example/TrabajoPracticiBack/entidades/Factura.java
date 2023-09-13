package com.example.TrabajoPracticiBack.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name= "Factura")

public class Factura extends BaseEntidad  {
    private int numero;
    private Date fecha;
    private double descuento;
    private String formaDePago;
    private int total;

    public void add(Factura faci) {
    }
}

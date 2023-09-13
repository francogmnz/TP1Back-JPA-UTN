package com.example.TrabajoPracticiBack.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Rubro")
public class Rubro extends BaseEntidad {
    private String denominacion;

    @OneToMany(cascade = CascadeType.REFRESH /*SI ESTA INSTANCIA YA EXISTE CON ESTO NO SE VUELVE A REPETIR*/, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id") // Nombre de la columna de la clave foránea en la tabla de productos
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void mostrarProductos() {
        System.out.println("Productos en el Rubro " + denominacion + ":");
        for (Producto producto : productos) {
            System.out.println("Nombre del Producto: " + producto.getDenominacion());
        }
    }
}

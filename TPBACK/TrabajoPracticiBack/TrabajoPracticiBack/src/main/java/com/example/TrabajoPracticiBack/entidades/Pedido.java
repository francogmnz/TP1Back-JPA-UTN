package com.example.TrabajoPracticiBack.entidades;

import com.example.TrabajoPracticiBack.Enums.EstadoPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Pedido")
public class Pedido extends BaseEntidad {
    private EstadoPedido estadoPedido;
    public enum EstadoPedido {
        Iniciado, Preparacion, Enviado
    }
    //private String estado;
    private Date fecha;
    private String tipoEnvio;
    private double total;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();
    public void agregarDetallePedido(DetallePedido detallePedidoi) {
        detallePedidos.add(detallePedidoi);
    }

    public void mostrarDetallePedido() {
        for (DetallePedido detallePedido : detallePedidos) {
            System.out.println("Cantidad: " + detallePedido.getCantidad() + ",Subtotal:" + detallePedido.getSubtotal());
        }
    }

}

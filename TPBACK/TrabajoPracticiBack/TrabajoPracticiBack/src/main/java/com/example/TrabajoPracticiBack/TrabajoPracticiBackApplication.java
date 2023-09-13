package com.example.TrabajoPracticiBack;

import ch.qos.logback.core.net.server.Client;
import com.example.TrabajoPracticiBack.entidades.*;
import com.example.TrabajoPracticiBack.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TrabajoPracticiBackApplication {

	@Autowired //Para que no haya error en el commandLineRunner
	ClienteRepository clienteRepository;
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private DetallePedidoRepository detallePedidoRepository;
	@Autowired
	private DomicilioRepository domicilioRepository;
	@Autowired
	private FacturaRepository facturaRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private RubroRepository rubroRepository;

	public static void main(String[] args) {
		SpringApplication.run(TrabajoPracticiBackApplication.class, args);
		System.out.println("Estoy funcionando");
	}

	@Bean
	public CommandLineRunner init() {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Calle Clorinda")
					.numero("825")
					.localidad("Godoy cruz")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("Calle 2")
					.numero("456")
					.localidad("Lujan")
					.build();
			Domicilio domicilio3 = Domicilio.builder()
					.calle("Calle 3")
					.numero("4109")
					.localidad("Las heras")
					.build();
// Crear instancia de cliente y agregar domicilios
			Cliente cliente = Cliente.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.email("juan@gmail.com")
					.telefono("261459498")
					.build();
			Cliente cliente1 = Cliente.builder()
					.nombre("Franco")
					.apellido("Gimenez")
					.email("fgimenez185@gmail.com")
					.telefono("2614685445")
					.build();
			cliente.agregarDomicilio(domicilio1);
			cliente.agregarDomicilio(domicilio2);
			cliente1.agregarDomicilio(domicilio3);

			//Creo instancia de pedido y la agrego al client
			Pedido pedido1 = Pedido.builder()
					.estadoPedido(Pedido.EstadoPedido.Enviado)
					.fecha(new Date()) //Fecha de hoy
					.tipoEnvio("A domicilio")
					.total(9999.10)
					.build();
			Pedido pedido2 = Pedido.builder()
					.estadoPedido(Pedido.EstadoPedido.Iniciado)
					.fecha(new Date()) //Fecha de hoy
					.tipoEnvio("A domicilio")
					.total(11190920.2)
					.build();
			cliente.agregarPedido(pedido1);
			cliente1.agregarPedido(pedido2);

			//creo la instancia de factura
			Factura factura1 = Factura.builder()
					.numero(100)
					.fecha(new Date())
					.descuento(50.00) //50%
					.formaDePago("Tarjeta de debito")
					.total(2990)
					.build();
			Factura factura2 = Factura.builder()
					.numero(99)
					.fecha(new Date())
					.descuento(100.00) //50%
					.formaDePago("Tarjeta de credito")
					.total(2123120)
					.build();
			//seteo factura al pedido
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);
			//Seteo DetallePedido
			DetallePedido detallePedido1 = DetallePedido.builder()
							.cantidad(200)
							.subtotal(999.90)
							.build();
			DetallePedido detallePedido2= DetallePedido.builder()
					.cantidad(9000)
					.subtotal(1000)
					.build();
			Rubro rubro1 = Rubro.builder()
					.denominacion("Electrico")
					.build();
			Rubro rubro2 = Rubro.builder()
					.denominacion("Comida")
					.build();

			//seteo producto
			Producto producto1 = Producto.builder()
					.tipo("Celular")
					.tiempoEstimadoCocina(50)
					.denominacion("Huevo frito")
					.precioVenta(50003.9)
					.precioCompra(15.99)
					.stockActual(10000)
					.stockMinimo(200)
					.unidadMedida("Unidades")
					.receta("Y lo freis")
					.build();
			Producto producto2 = Producto.builder()
					.tipo("Comida")
					.tiempoEstimadoCocina(30)
					.denominacion("Pizza Margarita")
					.precioVenta(12.99)
					.precioCompra(5.99)
					.stockActual(100)
					.stockMinimo(20)
					.unidadMedida("Unidades")
					.receta("Ingredientes y pasos para hacer una pizza Margarita")
					.build();
			//seteo el rubro al producto
			rubro1.agregarProducto(producto1);
			rubro2.agregarProducto(producto2);
			//seteo el detalle al producto
			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);

//seteo el detalle al pedido
			pedido1.getDetallePedidos().add(detallePedido1);
			pedido2.getDetallePedidos().add(detallePedido2);
			// Guardar el objeto Persona en la base de datos
			clienteRepository.save(cliente);
			pedidoRepository.save(pedido1);
			rubroRepository.save(rubro1);
			productoRepository.save(producto1);
			clienteRepository.save(cliente1);
			pedidoRepository.save(pedido2);
			rubroRepository.save(rubro2);
			productoRepository.save(producto2);

			// Recuperar el objeto Persona desde la base de datos
			Cliente clienteRecuperado = clienteRepository.findById(cliente.getId()).orElse(null);
			if (clienteRecuperado != null) {
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Email: " + clienteRecuperado.getEmail());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				clienteRecuperado.mostrarDomicilios();
			}

				Pedido pedidoRecuperado1 = pedidoRepository.findById(pedido1.getId()).orElse(null);
				if (pedidoRecuperado1 != null) {
					System.out.println("Pedido 1:");
					System.out.println("Estado: " + pedidoRecuperado1.getEstadoPedido());
					System.out.println("Fecha: " + pedidoRecuperado1.getFecha());
					System.out.println("Tipo de Envío: " + pedidoRecuperado1.getTipoEnvio());
					System.out.println("Total: $" + pedidoRecuperado1.getTotal());
					clienteRecuperado.mostrarPedidos();

				}
				Factura facturaRecuperada1 = facturaRepository.findById(factura1.getId()).orElse(null);
				if (facturaRecuperada1 != null) {
					System.out.println("Factura 1:");
					System.out.println("Número: " + facturaRecuperada1.getNumero());
					System.out.println("Fecha: " + facturaRecuperada1.getFecha());
					System.out.println("Descuento: " + facturaRecuperada1.getDescuento() + "%");
				}

				DetallePedido detallePedidoRecuperado1 = detallePedidoRepository.findById(detallePedido1.getId()).orElse(null);
				if (detallePedidoRecuperado1 != null) {
					System.out.println("Detalle pedido:");
					System.out.println("Cantidad: " + detallePedidoRecuperado1.getCantidad());
					System.out.println("Subtotal: " + detallePedidoRecuperado1.getSubtotal());
					pedidoRecuperado1.mostrarDetallePedido();

				}
			Rubro rubroRecuperado1 = rubroRepository.findById(rubro1.getId()).orElse(null);
			if (rubroRecuperado1 != null) {
				System.out.println("Rubro del producto:");
				System.out.println("Rubro: " + rubroRecuperado1.getDenominacion());
				rubroRecuperado1.mostrarProductos();
			}

			Producto productoRecuperado = productoRepository.findById(producto1.getId()).orElse(null);
			if (productoRecuperado != null) {
				System.out.println("Producto Recuperado:");
				System.out.println("Tipo: " + productoRecuperado.getTipo());
				System.out.println("Tiempo Estimado de Cocina: " + productoRecuperado.getTiempoEstimadoCocina());
				System.out.println("Denominación: " + productoRecuperado.getDenominacion());
				System.out.println("Precio de Venta: $" + productoRecuperado.getPrecioVenta());
				System.out.println("Precio de Compra: $" + productoRecuperado.getPrecioCompra());
				System.out.println("Stock Actual: " + productoRecuperado.getStockActual());
				System.out.println("Stock Mínimo: " + productoRecuperado.getStockMinimo());
				System.out.println("Unidad de Medida: " + productoRecuperado.getUnidadMedida());
				System.out.println("Receta: " + productoRecuperado.getReceta());
			}
		};
	}
}
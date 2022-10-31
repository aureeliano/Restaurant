package modelo;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MesaAtendida {

	private GregorianCalendar fecha;
	private Mesa mesa;
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	private double total;
	private Enumerados.formaDePago formaDePago;							// hay que elegir la forma de pago por ventana cuando se cierra la mesa
	private ArrayList<Promocion> promociones = new ArrayList<Promocion>();
	
	
	public MesaAtendida(GregorianCalendar fecha, Mesa mesa, ArrayList<Pedido> pedidos, double total,
			Enumerados.formaDePago formaDePago, ArrayList<Promocion> promociones) {
		super();
		this.fecha = fecha;
		this.mesa = mesa;
		this.pedidos = pedidos;
		this.total = total;
		this.formaDePago = formaDePago;
		this.promociones = promociones;
	}
	
	
}

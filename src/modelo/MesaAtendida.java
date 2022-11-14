package modelo;

import java.util.ArrayList;
import java.util.Date;

public class MesaAtendida {

	private Date fecha = new Date();
	private Mesa mesa;
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	private double total;
	private Enumerados.formaDePago formaDePago;							// hay que elegir la forma de pago por ventana cuando se cierra la mesa
	private ArrayList<Promocion> promociones = new ArrayList<Promocion>();
	
	
	public MesaAtendida(Mesa mesa, ArrayList<Pedido> pedidos, double total,
			Enumerados.formaDePago formaDePago, ArrayList<Promocion> promociones) {
		super();
		this.mesa = mesa;
		this.pedidos = pedidos;
		this.total = total;
		this.formaDePago = formaDePago;
		this.promociones = promociones;
	}


	public double getTotal()
	{
		return total;
	}


	public Mesa getMesa()
	{
		return mesa;
	}
	
	
	
}

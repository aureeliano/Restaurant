package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MesaAtendida implements Serializable
{

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


	public MesaAtendida() {}

	
	public double getTotal()
	{
		return total;
	}


	public Mesa getMesa()
	{
		return mesa;
	}


	public Date getFecha() {
		return fecha;
	}


	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}


	public Enumerados.formaDePago getFormaDePago() {
		return formaDePago;
	}


	public ArrayList<Promocion> getPromociones() {
		return promociones;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}


	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public void setFormaDePago(Enumerados.formaDePago formaDePago) {
		this.formaDePago = formaDePago;
	}


	public void setPromociones(ArrayList<Promocion> promociones) {
		this.promociones = promociones;
	}
	
	
	
}

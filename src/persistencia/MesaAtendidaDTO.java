package persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import modelo.Pedido;

public class MesaAtendidaDTO implements Serializable
{
	private Date fecha = new Date();
	private MesaDTO mesa;
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	private double total;
	
	
	public MesaAtendidaDTO() {}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public MesaDTO getMesa() {
		return mesa;
	}


	public void setMesa(MesaDTO mesa) {
		this.mesa = mesa;
	}


	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}
	
	
}

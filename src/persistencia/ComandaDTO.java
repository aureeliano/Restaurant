package persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import modelo.Enumerados;
import modelo.Mesa;
import modelo.Pedido;

public class ComandaDTO implements Serializable
{
	private GregorianCalendar fecha;
    private Mesa mesa;
    private ArrayList<Pedido> pedidos;
    private Enumerados.estadoComanda estado;
	
    
    public ComandaDTO() {}


	public GregorianCalendar getFecha() {
		return fecha;
	}


	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}


	public Mesa getMesa() {
		return mesa;
	}


	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}


	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}


	public Enumerados.estadoComanda getEstado() {
		return estado;
	}


	public void setEstado(Enumerados.estadoComanda estado) {
		this.estado = estado;
	}

}

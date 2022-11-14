package modelo;


import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Comanda 
{
    private GregorianCalendar fecha;
    private Mesa mesa;
    private ArrayList<Pedido> pedidos;
    private Enumerados.estadoComanda estado;
    
	public Comanda(Mesa mesa)
	{
		super();
		this.fecha = new GregorianCalendar();
		this.fecha.setTime(new Date());
		this.mesa = mesa;
		this.pedidos = new ArrayList<Pedido>();
		this.estado = Enumerados.estadoComanda.ABIERTO;
	}

	public Mesa getMesa()
	{
		return mesa;
	}

	public void setMesa(Mesa mesa)
	{
		this.mesa = mesa;
	}

	public Enumerados.estadoComanda getEstado()
	{
		return estado;
	}

	public void setEstado(Enumerados.estadoComanda estado)
	{
		this.estado = estado;
	}

	public ArrayList<Pedido> getPedidos()
	{
		return pedidos;
	}
    
	
    
}
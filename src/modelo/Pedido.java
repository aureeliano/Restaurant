package modelo;

import java.io.Serializable;

public class Pedido implements Serializable
{
    private Producto producto;
    private int cant;
    
    
    public Pedido() {}

	/**
     * Pre: cant debe ser mayor a 0. <br>
     * @param producto
     * @param cant
     */
	public Pedido(Producto producto, int cant)
	{
		super();
		this.producto = producto;
		this.cant = cant;
	}

	public Producto getProducto()
	{
		return producto;
	}

	public int getCant()
	{
		return cant;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}
   
    
}

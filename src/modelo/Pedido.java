package modelo;

public class Pedido
{
    private Producto producto;
    private int cant;
    
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
   
    
}

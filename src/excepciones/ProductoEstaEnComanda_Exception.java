package excepciones;

public class ProductoEstaEnComanda_Exception extends Exception
{
	private String nombre;

	public ProductoEstaEnComanda_Exception(String nombre)
	{
		super();
		this.nombre = nombre;
	}

	public String getNombre()
	{
		return nombre;
	}

}

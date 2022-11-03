package excepciones;

public class PromocionTemporalNombreRepetido_Exception extends Exception
{
	private String nombre;

	public PromocionTemporalNombreRepetido_Exception(String nombre)
	{
		super();
		this.nombre = nombre;
	}

	public String getNombre()
	{
		return nombre;
	}

}

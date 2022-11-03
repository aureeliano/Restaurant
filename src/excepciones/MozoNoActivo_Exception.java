package excepciones;

public class MozoNoActivo_Exception extends Exception
{
	public String nombre;

	public MozoNoActivo_Exception(String nombre)
	{
		super();
		this.nombre = nombre;
	}

	public String getNombre()
	{
		return nombre;
	}

}

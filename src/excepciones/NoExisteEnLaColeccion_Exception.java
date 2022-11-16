package excepciones;

public class NoExisteEnLaColeccion_Exception extends Exception
{
	private String identificador;

	public NoExisteEnLaColeccion_Exception(String identificador)
	{
		super();
		this.identificador = identificador;
	}

	public String getIdentificador()
	{
		return identificador;
	}

}

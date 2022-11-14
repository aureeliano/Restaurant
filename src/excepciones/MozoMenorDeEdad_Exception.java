package excepciones;

public class MozoMenorDeEdad_Exception extends Exception
{
	private long edad;

	public MozoMenorDeEdad_Exception(long edad)
	{
		super();
		this.edad = edad;
	}

	public long getEdad()
	{
		return edad;
	}

}

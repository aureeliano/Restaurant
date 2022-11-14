package excepciones;

public class ComandaYaCerrada_Exception extends Exception
{
	private int nroMesa;

	public ComandaYaCerrada_Exception(int nroMesa)
	{
		super();
		this.nroMesa = nroMesa;
	}

	public int getNroMesa()
	{
		return nroMesa;
	}

}

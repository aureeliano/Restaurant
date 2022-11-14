package excepciones;

public class MesaOcupada_Exception extends Exception
{
	private int nroMesa;

	public MesaOcupada_Exception(int nroMesa)
	{
		super();
		this.nroMesa = nroMesa;
	}

	public int getNroMesa()
	{
		return nroMesa;
	}

}

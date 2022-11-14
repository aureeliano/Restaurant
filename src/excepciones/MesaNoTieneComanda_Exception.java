package excepciones;

public class MesaNoTieneComanda_Exception extends Exception
{
	private int nroMesa;

	public MesaNoTieneComanda_Exception(int nroMesa)
	{
		super();
		this.nroMesa = nroMesa;
	}

	public int getNroMesa()
	{
		return nroMesa;
	}

}

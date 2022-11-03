package excepciones;

public class NroMesaRepetido_Exception extends Exception {
	private int nroMesa;

	public NroMesaRepetido_Exception(int nroMesa) {
		super();
		this.nroMesa = nroMesa;
	}

	public int getNroMesa()
	{
		return nroMesa;
	}

}

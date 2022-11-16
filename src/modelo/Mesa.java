package modelo;

import java.io.Serializable;

public class Mesa implements Serializable
{
    private static int siguienteNroMesa = 0; //el 0 es la barra
    private int nroMesa = siguienteNroMesa++;
    private int cantSillas;
    private Enumerados.estadoMesa estado = Enumerados.estadoMesa.LIBRE;
    private Mozo mozo;
    
	/**
	 * Pre: cantSillas debe ser > =2 cuando el nro de mesa es > 1
	 * @param cantSillas
	 */
    public Mesa(int cantSillas)
	{
		super();
		this.cantSillas = cantSillas;
	}

    
	public Mesa() {}
	

	public int getNroMesa()
	{
		return nroMesa;
	}

	public int getCantSillas()
	{
		return cantSillas;
	}

	public void setCantSillas(int cantSillas)
	{
		this.cantSillas = cantSillas;
	}

	public Enumerados.estadoMesa getEstado()
	{
		return estado;
	}

	public void setEstado(Enumerados.estadoMesa estado)
	{
		this.estado = estado;
	}

	public Mozo getMozo()
	{
		return mozo;
	}

	public void setMozo(Mozo mozo)
	{
		this.mozo = mozo;
	}

	public static int getSiguienteNroMesa()
	{
		return siguienteNroMesa;
	}

	public void setNroMesa(int nroMesa) {
		this.nroMesa = nroMesa;
	}

}


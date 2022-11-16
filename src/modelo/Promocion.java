package modelo;

import java.io.Serializable;

public class Promocion implements Serializable
{

	private boolean activa = true;
	private Enumerados.diasDePromo diasDePromo;
	
	
	public Promocion() {}
	
	public Promocion(Enumerados.diasDePromo diasDePromo)
	{
		super();
		this.diasDePromo = diasDePromo;
	}
	public boolean isActiva()
	{
		return activa;
	}
	public void setActiva(boolean activa)
	{
		this.activa = activa;
	}
	public Enumerados.diasDePromo getDiasDePromo()
	{
		return diasDePromo;
	}
	public void setDiasDePromo(Enumerados.diasDePromo diasDePromo) {
		this.diasDePromo = diasDePromo;
	}
	
	
}

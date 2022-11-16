package modelo;

public class Promocion
{

	private boolean activa = true;
	private Enumerados.diasDePromo diasDePromo;
	
	
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
	
	
}

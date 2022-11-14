package modelo;

public class PromocionTemporal extends Promocion
{
		private String nombre;
    private Enumerados.formaDePago formaDePago;
    private int porcentajeDesc;
    private boolean esAcumulable;
    
    
    
	public PromocionTemporal(modelo.Enumerados.diasDePromo diasDePromo, String nombre,
<<<<<<< HEAD
			modelo.Enumerados.formaDePago formaDePago, int porcentajeDesc, boolean esAcumulable)
	{
=======
			modelo.Enumerados.formaDePago formaDePago, int porcentajeDesc, boolean esAcumulable) {
>>>>>>> 10ae6d0d26cfc1b7671875c768618c13423db803
		super(diasDePromo);
		this.nombre = nombre;
		this.formaDePago = formaDePago;
		this.porcentajeDesc = porcentajeDesc;
		this.esAcumulable = esAcumulable;
	}



<<<<<<< HEAD
=======

>>>>>>> 10ae6d0d26cfc1b7671875c768618c13423db803
	public int getPorcentajeDesc() {
		return porcentajeDesc;
	}



	public String getNombre()
	{
		return nombre;
	}



	public boolean isEsAcumulable()
	{
		return esAcumulable;
	}



	public Enumerados.formaDePago getFormaDePago()
	{
		return formaDePago;
	}
    
	
}

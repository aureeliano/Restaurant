package modelo;

public class PromocionTemporal extends Promocion
{
	
    private String nombre;
    private Enumerados.formaDePago formaDePago;
    private int porcentajeDesc;
    private boolean esAcumulable;
    
    
    
	public PromocionTemporal(modelo.Enumerados.diasDePromo diasDePromo, String nombre,
			modelo.Enumerados.formaDePago formaDePago, int porcentajeDesc, boolean esAcumulable)
	{
		super(diasDePromo);
		this.nombre = nombre;
		this.formaDePago = formaDePago;
		this.porcentajeDesc = porcentajeDesc;
		this.esAcumulable = esAcumulable;
	}



	public int getPorcentajeDesc() {
		return porcentajeDesc;
	}
    
}

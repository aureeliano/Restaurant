package modelo;

public class PromocionTemporal extends Promocion
{
	
    private String nombre;
    private Enumerados.formaDePago formaDePago;
    private int porcentajeDesc;
    private boolean esAcumulable;
    
    
	public int getPorcentajeDesc() {
		return porcentajeDesc;
	}
    
}

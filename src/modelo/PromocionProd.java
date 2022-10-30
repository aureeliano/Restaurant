package modelo;

public class PromocionProd extends Promocion
{
	private static int siguienteIdProm = 0;
	private int idProm;
    private Producto producto;
    private boolean aplica2x1;
    private boolean aplicaDtoPorCant;
    private int DtoPorCant_CantMinima;
    private double DtoPorCant_PrecioUnitario;
    
    //private Enumerados.diasDePromo diasPromo;
    //private boolean activa;
    
   

	public PromocionProd(int idProm, Producto producto, boolean aplica2x1, boolean aplicaDtoPorCant,
			int dtoPorCant_CantMinima, double dtoPorCant_PrecioUnitario) {
		super();
		this.idProm = siguienteIdProm++;
		this.producto = producto;
		this.aplica2x1 = aplica2x1;
		this.aplicaDtoPorCant = aplicaDtoPorCant;
		DtoPorCant_CantMinima = dtoPorCant_CantMinima;
		DtoPorCant_PrecioUnitario = dtoPorCant_PrecioUnitario;
		
	}
	
	
	
}

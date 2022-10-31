package modelo;

public class PromocionProd extends Promocion
{
	private static int siguienteIdProm = 0;
	private int idProm = siguienteIdProm++;;
    private Producto producto;
    private boolean aplica2x1;
    private boolean aplicaDtoPorCant;
    private int DtoPorCant_CantMinima;
    private double DtoPorCant_PrecioUnitario;
    
    //private Enumerados.diasDePromo diasPromo;
    //private boolean activa;
    
   

	public PromocionProd(Enumerados.diasDePromo diasDePromo, Producto producto, boolean aplica2x1, boolean aplicaDtoPorCant,
			int dtoPorCant_CantMinima, double dtoPorCant_PrecioUnitario) {
		super(diasDePromo);
		this.producto = producto;
		this.aplica2x1 = aplica2x1;
		this.aplicaDtoPorCant = aplicaDtoPorCant;
		DtoPorCant_CantMinima = dtoPorCant_CantMinima;
		DtoPorCant_PrecioUnitario = dtoPorCant_PrecioUnitario;
		
	}

	public static int getSiguienteIdProm()
	{
		return siguienteIdProm;
	}

	public int getIdProm()
	{
		return idProm;
	}

	public Producto getProducto()
	{
		return producto;
	}

	public boolean isAplica2x1()
	{
		return aplica2x1;
	}

	public boolean isAplicaDtoPorCant()
	{
		return aplicaDtoPorCant;
	}

	public int getDtoPorCant_CantMinima()
	{
		return DtoPorCant_CantMinima;
	}

	public double getDtoPorCant_PrecioUnitario()
	{
		return DtoPorCant_PrecioUnitario;
	}
	
	
	
}

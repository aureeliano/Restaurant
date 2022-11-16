package negocio;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.ComandaYaCerrada_Exception;
import excepciones.MenosDe2ProdsEnPromocion_Exception;
import excepciones.MesaNoTieneComanda_Exception;
import excepciones.MesaOcupada_Exception;
import excepciones.MozoNoActivo_Exception;
import excepciones.NoHayProductos_Exception;
import excepciones.NroMesaRepetido_Exception;
import excepciones.NyARepetido_Exception;
import excepciones.PromocionTemporalNombreRepetido_Exception;
import excepciones.StockInsuficiente_Exception;
import excepciones.TodasMesasInhabilitadas_Exception;
import excepciones.TodosMozosInactivos_Exception;
import excepciones.UserNameRepetido_Exception;
import modelo.Comanda;
import modelo.Enumerados;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.Promocion;

public class FuncionalidadOperariosTest {

	private FuncionalidadOperarios func = new FuncionalidadOperarios(new Operario("Juan Rodriguez","juan","juan1234"));
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	//public void modificaOperario(String NyA, String username, String password) throws UserNameRepetido_Exception
	@Test
	public void modificaOperarioTest() 
	{
		
	}
	
	//public void eliminaOperario()
	
	//public void modificaMozo(String NyA, String nuevoNyA, int cantHijos) throws NyARepetido_Exception
	
	//public void cambiaEstadoMozo(String NyA, Enumerados.estadoMozo nuevoEstado)
	
	//public void modificaProducto(int id, String nombre, double precioCosto, double precioVenta)
	
	//public void modificaMesa(int nroMesa, int cantSillas, Enumerados.estadoMesa estado) throws NroMesaRepetido_Exception
	
	//public void agregaPromocionProd(boolean activa, Enumerados.diasDePromo dia, Producto producto, boolean aplica2x1,
	//boolean aplicaDtoPorCant, int dtoPorCant_CantMinima, double dtoPorCant_PrecioUnitario)
	
	//public void modificaPromocionProd(int idProm, boolean activa)
	
	//public void eliminaPromocionProd(int idProm)
	
	//public void agregaPromocionTemporal(Enumerados.diasDePromo diasDePromo, String nombre, Enumerados.formaDePago formaDePago, int porcentajeDescuento, boolean esAcumulable) 
	//		throws PromocionTemporalNombreRepetido_Exception
	
	//public void eliminaPromocionTemporal(String nombre)
	
	//public void modificaPromocionTemporal(String nombre, boolean activo)
	
	//public void asignaMozoAMesa(int nroMesa, String NyA) throws MozoNoActivo_Exception
	
	//public void abreComanda(int nroMesa) throws TodasMesasInhabilitadas_Exception, TodosMozosInactivos_Exception,
	//MenosDe2ProdsEnPromocion_Exception, MozoNoActivo_Exception, NoHayProductos_Exception, MesaOcupada_Exception
	
	//private void TodasMesasInhabilitadas() throws TodasMesasInhabilitadas_Exception
	
	//private void TodosMozosInactivos() throws TodosMozosInactivos_Exception
	
	//private void MenosDe2ProdsEnPromocion() throws MenosDe2ProdsEnPromocion_Exception
	
	//public void AgregaPedidoAComanda(int nroMesa, int idProd, int cant)
	//throws MesaNoTieneComanda_Exception, StockInsuficiente_Exception
	
//	public void cierraComanda(int nroMesa, Enumerados.formaDePago formaDePago)
//			throws MesaNoTieneComanda_Exception, ComandaYaCerrada_Exception
	
//	private double procesaPromProds(Comanda comanda, boolean aplicoPromProd, ArrayList<Promocion> promos)
	
//	private double procesaPromTemps(boolean aplicoPromProd, Enumerados.formaDePago formaDePago,
//			ArrayList<Promocion> promos)
	
//	public double estadisticasEmpleado(String nombre)
	
//	public Mozo empleadoConMayorVolumenDeVenta()
	
//	public Mozo empleadoConMenorVolumenDeVenta()
	
//	public double consumoPromedioPorMesa(int nroMesa)
}

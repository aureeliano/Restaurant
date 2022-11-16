package test;

import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.ComandaYaCerrada_Exception;
import excepciones.MenosDe2ProdsEnPromocion_Exception;
import excepciones.MesaNoTieneComanda_Exception;
import excepciones.MesaOcupada_Exception;
import excepciones.MozoNoActivo_Exception;
import excepciones.NoExisteEnLaColeccion_Exception;
import excepciones.NoHayProductos_Exception;
import excepciones.NroMesaRepetido_Exception;
import excepciones.NyARepetido_Exception;
import excepciones.PromocionTemporalNombreRepetido_Exception;
import excepciones.StockInsuficiente_Exception;
import excepciones.TodasMesasInhabilitadas_Exception;
import excepciones.TodosMozosInactivos_Exception;
import excepciones.UserNameRepetido_Exception;
import modelo.Enumerados;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import negocio.FuncionalidadOperarios;
import negocio.Sistema;

public class FuncionalidadOperariosTest {

	private FuncionalidadOperarios func = new FuncionalidadOperarios(new Operario("Juan Perez", "juanPerez", "Juan123"));


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Sistema.getInstance().getOperarios().put("juancito", new Operario("Juan Rodriguez", "juancito", "Juan1234"));
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Sistema.getInstance().getOperarios().remove("juancito");

	}

	@Before
	public void setUp() throws Exception {
		Sistema.getInstance().getMozos().clear();
		Sistema.getInstance().getMesas().clear();
		Sistema.getInstance().getProductos().clear();
		Sistema.getInstance().getPromocionProds().clear();
		Sistema.getInstance().getPromocionTemps().clear();
	}

	@After
	public void tearDown() throws Exception {
	}

	// public void modificaOperario(String NyA, String username, String password)
	// throws UserNameRepetido_Exception
	@Test
	public void testModificaOperarioCorrecto() {
		try {
			this.func.modificaOperario("Juan Perez", "juanPerezzz", "Juan123");
		} catch (UserNameRepetido_Exception e) {
			Assert.fail("No deberia tirar excepcion");
		}
		Assert.assertEquals("El nombre se deberia haber modificado", "Juan Perez",
				this.func.getOperarioActual().getNyA());
		Assert.assertEquals("El userName se deberia haber modificado", "juanPerezzz",
				this.func.getOperarioActual().getUsername());
		Assert.assertEquals("El password se deberia haber modificado", "Juan123",
				this.func.getOperarioActual().getPassword());
	}

	@Test
	public void testModificaOperarioIncorrecto() {
		try {
			this.func.modificaOperario("Juan Perez", "juancito", "Juan123");
			Assert.fail("Deberia tirar excepcion");
		} catch (UserNameRepetido_Exception e) {
		}
	}


	@Test
	public void testModificaMozoIncorrecto() {
		try {
			this.func.modificaMozo("Juan", "Carlos", 1);
			Assert.fail("Deberia tirar excepcion");
		} catch (NyARepetido_Exception e) {
			Assert.fail("No deberia tirar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {

		}
	}

	@Test
	public void testCambiaEstadoMozoIncorrecto() {
		try {
			this.func.cambiaEstadoMozo("Juan", Enumerados.estadoMozo.ACTIVO);
			Assert.fail("Deberia tirar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {

		}
	}

	@Test
	public void modificaProducto() {
		try {
			this.func.modificaProducto(0, "Pizza", 0, 0);
			Assert.fail("Deberia tirar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {

		}
	}

	@Test
	public void testModificaMesa() {
		try {
			this.func.modificaMesa(5, 4, Enumerados.estadoMesa.LIBRE);
			Assert.fail("Deberia tirar excepcion");
		} catch (NroMesaRepetido_Exception e) {
			Assert.fail("No deberia tirar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {

		}
	}

	@Test
	public void testAgregaPromocionProdCorrecta() {
		Producto prod = new Producto("Agua", 10, 20, 10);
		this.func.agregaPromocionProd(true, Enumerados.diasDePromo.MONDAY, prod, true, true, 3, 0.5);
		Assert.assertNotNull("Se deberia haber agregado la promocion",
				Sistema.getInstance().getPromocionProds().get(prod.getIdProd()));
	}

	@Test
	public void testAgregaPromocionProdIncorrecta() {
		Producto prod = new Producto("Agua", 10, 20, 10);
		this.func.agregaPromocionProd(true, Enumerados.diasDePromo.MONDAY, prod, false, false, 3, 0.5);
		Assert.fail("Se deberia lanzar excepcion ya que son false ambas promos");
	}

	@Test
	public void testModificaPromocionProdIncorrecto() {
		try {
			this.func.modificaPromocionProd(0, true);
			Assert.fail("Deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {

		}
	}

	@Test
	public void testEliminaPromocionProd() {
		try {
			this.func.eliminaPromocionProd(0);
			Assert.fail("Deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
		}
	}

	@Test
	public void testAgregaPromocionTemporal() {
		try {
			this.func.agregaPromocionTemporal(Enumerados.diasDePromo.FRIDAY, "Promo1", Enumerados.formaDePago.CTADNI,
					20, false);
		} catch (PromocionTemporalNombreRepetido_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
		Assert.assertNotNull("Se deberia haber agreagdo la promo", Sistema.getInstance().getPromocionTemps().get(0));
	}

	@Test
	public void testEliminaPromocionTemporal() {
		try {
			this.func.eliminaPromocionTemporal("Promo1");
			Assert.fail("Deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {

		}
	}

	@Test
	public void testModificaPromocionTemporal() {
		try {
			this.func.modificaPromocionTemporal("Promo2", true);
			;
			Assert.fail("Deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {

		}
	}

	@Test
	public void testAsignaMozoAMesaIncorrecto() {
		try {
			this.func.asignaMozoAMesa(0, "Mozo");
			Assert.fail("Deberia lanzar excepcion de que no existen en la coelccion");
		} catch (MozoNoActivo_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {

		}
	}

	@Test
	public void testAsignaMozoAMesaIncorrecto1() {
		Sistema.getInstance().getMesas().put(0, new Mesa(4));
		try {
			this.func.asignaMozoAMesa(0, "Mozo");
			Assert.fail("Deberia lanzar excepcion de que mozo no existe");
		} catch (MozoNoActivo_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {

		}
	}

	@Test
	public void testAsignaMozoAMesaIncorrecto2() {
		Sistema.getInstance().getMozos().put("Juan", new Mozo("Juan", new Date(1, 3, 2001), 5));
		Sistema.getInstance().getMozos().get("Juan").setEstado(Enumerados.estadoMozo.AUSENTE);
		try {
			this.func.asignaMozoAMesa(0, "Juan");
			Assert.fail("Deberia lanzar excepcion de que no existe mesa");
		} catch (MozoNoActivo_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
		}
	}

	@Test
	public void testAbreComandaIncorrecto1() {
		try {
			this.func.abreComanda(0);
			Assert.fail("Deberia lanzar excepcion");
		} catch (TodasMesasInhabilitadas_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (TodosMozosInactivos_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (MenosDe2ProdsEnPromocion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (MozoNoActivo_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoHayProductos_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (MesaOcupada_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {

		}
	}

	@Test
	public void testAbreComandaIncorrecto2() {
		Mesa mesa = new Mesa(4);
		mesa.setEstado(Enumerados.estadoMesa.LIBRE);
		Sistema.getInstance().getMozos().put("Juan", new Mozo("Juan", new Date(1,2,1999),3));
		Sistema.getInstance().getMesas().put(0, mesa);
		try {
			this.func.abreComanda(0);
			Assert.fail("No deberia lanzar excepcion");
		} catch (TodasMesasInhabilitadas_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (TodosMozosInactivos_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (MenosDe2ProdsEnPromocion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (MozoNoActivo_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoHayProductos_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (MesaOcupada_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}

	
	//Hay una mesa libre y me lanza excepcion de mesas inhabilitadas. Siempre lanza esa exc.OBSERVACION
	@Test
	public void testAbreComandaIncorrecto3() {
		Mesa mesa = new Mesa(4);
		Mozo mozo = new Mozo("Juan", new Date(1,2,2000), 3);
		mozo.setEstado(Enumerados.estadoMozo.AUSENTE);
		mesa.setEstado(Enumerados.estadoMesa.LIBRE);
		Sistema.getInstance().getMesas().put(0, mesa);
		Sistema.getInstance().getMozos().put("Juan", mozo);
		try {
			this.func.abreComanda(0);
			Assert.fail("Deberia lanzar excepcion");
		} catch (TodasMesasInhabilitadas_Exception e) {
			Assert.fail("No deberia lanzar excepcion de TodasMesasInhabilitadas_Exception");
		} catch (TodosMozosInactivos_Exception e) {
			Assert.fail("No deberia lanzar excepcion de TodosMozosInactivos_Exception");
		} catch (MenosDe2ProdsEnPromocion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (MozoNoActivo_Exception e) {
			
		} catch (NoHayProductos_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (MesaOcupada_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}

	@Test
	public void testAgregaPedidoAComandaIncorrecto() {
		try {
			this.func.AgregaPedidoAComanda(0, 0, 5);
			Assert.fail("Deberia lanzar excepcion");
		} catch (MesaNoTieneComanda_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (StockInsuficiente_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			
		}
	}

	@Test
	public void testCierraComandaIncorrecto() {
		try {
			this.func.cierraComanda(0, Enumerados.formaDePago.CTADNI);
			Assert.fail("Deberia lanzar excepcion");
		} catch (MesaNoTieneComanda_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (ComandaYaCerrada_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			
		}
	}

	@Test
	public void testEstadisticasEmpleado() {
		try {
			this.func.estadisticasEmpleado("Juan");
			Assert.fail("Deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			
		}
	}

	@Test
	public void testEmpleadoConMayorVolumenDeVenta() {
		Mozo res =this.func.empleadoConMayorVolumenDeVenta();
		Assert.assertNull("Deberia ser nulo ya que no existen empleados", res);
	}
	
	@Test
	public void testEmpleadoConMenorVolumenDeVenta() {
		Mozo res =this.func.empleadoConMenorVolumenDeVenta();
		Assert.assertNull("Deberia ser nulo ya que no existen empleados", res);
	}

	@Test
	public void testConsumoPromedioPorMesa() {
		try {
			this.func.consumoPromedioPorMesa(0);
			Assert.fail("Deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
		
		}
		
	}
}

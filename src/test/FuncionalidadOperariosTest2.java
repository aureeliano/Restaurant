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
import modelo.Comanda;
import modelo.Enumerados;
import modelo.Mesa;
import modelo.MesaAtendida;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromocionProd;
import modelo.PromocionTemporal;
import negocio.FuncionalidadOperarios;
import negocio.Sistema;

public class FuncionalidadOperariosTest2 {

	private FuncionalidadOperarios func = new FuncionalidadOperarios(new Operario("Juan Perez", "juanPerez", "Juan123"));
	private Producto prod = new Producto ("Agua", 10, 20, 5);
	private Producto prod1 = new Producto ("Cerveza", 10, 20, 5);
	private Mesa mesa0 = new Mesa(4);
	private Mesa mesa1 = new Mesa(4);
	private Mozo mozo1 = new Mozo ("Juan", new Date(1,2,1999), 5);
	private Mozo mozo2 = new Mozo ("Esteban", new Date(1,2,1999), 5);
	private PromocionProd promo1 = new PromocionProd(Enumerados.diasDePromo.MONDAY, prod, true, true, 3, 0.5);
	private PromocionTemporal promoTemp = new PromocionTemporal(Enumerados.diasDePromo.FRIDAY, "Promo1", Enumerados.formaDePago.CTADNI,
			20, false);
	private Comanda com = new Comanda(mesa0);
	
	{Sistema.getInstance().getMesas().put(this.mesa0.getNroMesa(), mesa0);
	Sistema.getInstance().getMesas().put(this.mesa1.getNroMesa(), mesa1);
	Sistema.getInstance().getMozos().put(this.mozo1.getNyA(), mozo1);
	Sistema.getInstance().getMozos().put(this.mozo2.getNyA(), mozo2);
	Sistema.getInstance().getProductos().put(this.prod.getIdProd(), prod);
	Sistema.getInstance().getProductos().put(this.prod1.getIdProd(), prod1);
	Sistema.getInstance().getPromocionProds().put(0, promo1);}
	//Sistema.getInstance().getPromocionTemps().add(promoTemp);}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Sistema.getInstance().getOperarios().put("juancito", new Operario("Juan Rodriguez", "juancito", "Juan1234"));
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Sistema.getInstance().getPromocionTemps().add(promoTemp);
		Sistema.getInstance().getMozos().put(this.mozo1.getNyA(), mozo1);
		Sistema.getInstance().getMozos().put(this.mozo2.getNyA(), mozo2);
		Sistema.getInstance().getMesas().put(this.mesa0.getNroMesa(), mesa0);
		Sistema.getInstance().getMesas().put(this.mesa1.getNroMesa(), mesa1);
	}

	@After
	public void tearDown() throws Exception {
		Sistema.getInstance().getMozos().clear();
		Sistema.getInstance().getMesas().clear();
		Sistema.getInstance().getProductos().clear();
		Sistema.getInstance().getPromocionProds().clear();
		Sistema.getInstance().getPromocionTemps().clear();
	}

	@Test
	public void testModificaOperarioCorrecto() {
		try {
			this.func.modificaOperario("Juan Perez", "juanPerezz", "Juan123");
		} catch (UserNameRepetido_Exception e) {
			Assert.fail("No deberia tirar excepcion");
		}
		Assert.assertEquals("El nombre se deberia haber modificado", "Juan Perez",
				this.func.getOperarioActual().getNyA());
		Assert.assertEquals("El userName se deberia haber modificado", "juanPerezz",
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
	public void testModificaMozoCorrecto() {
		try {
			this.func.modificaMozo("Juan", "Carlos", 1);
			Assert.assertNotNull("El nombre se deberia haber modificado",Sistema.getInstance().getMozos().get("Carlos"));
			Assert.assertEquals("La cantidad de hijos se deberia haber modificado", Sistema.getInstance().getMozos().get("Carlos").getCantHijos(),1);
		} catch (NyARepetido_Exception e) {
			Assert.fail("No deberia tirar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia tirar excepcion");
		}
	}
	
	@Test
	public void testModificaMozoIncorrecto() {
		try {
			this.func.modificaMozo("Juan", "Esteban", 1);
			Assert.fail("Deberia tirar excepcion de nombre y apellido repetido");
		} catch (NyARepetido_Exception e) {
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia tirar excepcion");
		}
	}

	@Test
	public void testCambiaEstadoMozoCorrecto() {
		try {
			this.func.cambiaEstadoMozo("Juan", Enumerados.estadoMozo.DEFRANCO);
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia tirar excepcion");
		}
		Mozo mozo = Sistema.getInstance().getMozos().get("Juan");
		Assert.assertEquals("El estado se deberia haber modificado", mozo.getEstado(), Enumerados.estadoMozo.DEFRANCO);
	}

	@Test
	public void testModificaProducto() {
		try {
			this.func.modificaProducto(this.prod1.getIdProd(), "Agua con gas", 5, 10);
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia tirar excepcion");
		}
		Producto producto = Sistema.getInstance().getProductos().get(this.prod1.getIdProd());
		Assert.assertEquals("El producto se deberia haber modificado", producto.getNombre(), "Agua con gas");
	}


	@Test
	public void testModificaMesaCorrecto() {
		try {
			this.func.modificaMesa(this.mesa0.getNroMesa(), 8, Enumerados.estadoMesa.LIBRE);	
		} catch (NroMesaRepetido_Exception e) {
			Assert.fail("No deberia tirar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia tirar excepcion de NoExisteEnLaColeccion_Exception");
		}
		Assert.assertEquals("La cantidad de sillas se deberia haber modificado", 8,
				Sistema.getInstance().getMesas().get(this.mesa0.getNroMesa()).getCantSillas());
		Assert.assertEquals("El estado se deberia haber modificado", Enumerados.estadoMesa.LIBRE,
				Sistema.getInstance().getMesas().get(this.mesa0.getNroMesa()).getEstado());
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
		this.func.agregaPromocionProd(true, Enumerados.diasDePromo.MONDAY, this.prod1, false, false, 3, 0.5);
		Assert.fail("Se deberia lanzar excepcion ya que son false ambas promos");
	}

	@Test
	public void testModificaPromocionProdCorrecto() {
		try {
			this.func.modificaPromocionProd(0, false);
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
		Assert.assertFalse("Se deberia haber modificado el estado de la promo", Sistema.getInstance().getPromocionProds().get(0).isActiva());
	}

	@Test
	public void testEliminaPromocionProdCorrecto() {
		try {
			this.func.eliminaPromocionProd(0);
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
		Assert.assertNull("Se deberia haber borrado la promo del sistema", Sistema.getInstance().getPromocionProds().get(0));
	}
	
	@Test
	public void testEliminaPromocionProdIncorrecto() {
		try {
			this.func.eliminaPromocionProd(97);
			Assert.fail("Deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
		}
	}

	@Test
	public void testAgregaPromocionTemporalCorrecto() {
		try {
			this.func.agregaPromocionTemporal(Enumerados.diasDePromo.FRIDAY, "Promo2", Enumerados.formaDePago.CTADNI,
					20, false);
			Assert.assertNotNull("Se deberia haber agreagdo la promo", Sistema.getInstance().getPromocionTemps().get(1));
		} catch (PromocionTemporalNombreRepetido_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testAgregaPromocionTemporalIncorrecto() {
		try {
			this.func.agregaPromocionTemporal(Enumerados.diasDePromo.FRIDAY, "Promo1", Enumerados.formaDePago.CTADNI,
					20, false);
			Assert.fail("Deberia lanzar excepcion");
		} catch (PromocionTemporalNombreRepetido_Exception e) {
			
		}
	}

	@Test
	public void testEliminaPromocionTemporal() {
		
		try {
			//System.out.println(Sistema.getInstance().getPromocionTemps().get(0).getNombre());
			this.func.eliminaPromocionTemporal("Promo1");
			Assert.assertTrue("Se deberia haber eliminado la promo",Sistema.getInstance().getPromocionTemps().isEmpty());
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("Deberia lanzar excepcion");
		}
	}

	@Test
	public void testModificaPromocionTemporalIncorrecto() {
		try {
			this.func.modificaPromocionTemporal("Promo5", true);
			
			Assert.fail("Deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {

		}
	}
	@Test
	public void testModificaPromocionTemporalCorrecto() {
		try {
			this.func.modificaPromocionTemporal("Promo1", false);
			
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
		Assert.assertFalse("Se deberia haber cambiado el estado de la promo", Sistema.getInstance().getPromocionTemps().get(0).isActiva());

	}

	@Test
	public void testAsignaMozoAMesaCorrecto() {
		try {
			this.func.asignaMozoAMesa(this.mesa0.getNroMesa(), this.mozo1.getNyA());
		} catch (MozoNoActivo_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
		 Assert.assertEquals("Se deberia haber asignado bien el mozo", this.mesa0.getMozo().getNyA(),this.mozo1.getNyA());
	}

	@Test
	public void testAsignaMozoAMesaInexistente() {
		try {
			this.func.asignaMozoAMesa(78, "Juan");
			Assert.fail("Deberia lanzar excepcion de que no existe mesa");
			
		} catch (MozoNoActivo_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			
		}
	}
	
	@Test
	public void testAsignaMozoInexistenteAMesa() {
		try {
			this.func.asignaMozoAMesa(78, "Peter");
			Assert.fail("Deberia lanzar excepcion de que no existe mozo");
			
		} catch (MozoNoActivo_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			
		}
	}

	@Test
	public void testAsignaMozoInactivoAMesa() {
		try {
			/*Mozo mozoNuevo = new Mozo("Pelado", new Date(1,2,1938), 3);
			mozoNuevo.setEstado(Enumerados.estadoMozo.AUSENTE);
			Sistema.getInstance().getMozos().put("Pelado", mozoNuevo);*/
			this.mozo2.setEstado(Enumerados.estadoMozo.AUSENTE);
			this.func.asignaMozoAMesa(this.mesa0.getNroMesa(), mozo2.getNyA());
			Assert.fail("Deberia lanzar excepcion de mozo inactivo");
			
		} catch (MozoNoActivo_Exception e) {
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testAbreComandaCorrecto() {
		try {
			this.mesa0.setEstado(Enumerados.estadoMesa.LIBRE);
			this.func.abreComanda(this.mesa0.getNroMesa());
		} catch (TodasMesasInhabilitadas_Exception e) {
			Assert.fail("No deberia lanzar excepcion de TodasMesasInhabilitadas_Exception");
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
	@Test
	public void testAbreComandaIncorrecto1() {
		Sistema.getInstance().getMesas().remove(this.mesa0.getNroMesa());
		try {
			this.func.abreComanda(this.mesa0.getNroMesa());
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
		this.mesa0.setEstado(Enumerados.estadoMesa.OCUPADA);
		this.mesa1.setEstado(Enumerados.estadoMesa.LIBRE);
		try {
			this.func.abreComanda(this.mesa0.getNroMesa());
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
			Assert.fail("No deberia lanzar excepcion");
		}
	}

	
	//Hay una mesa libre y me lanza excepcion de mesas inhabilitadas. Siempre lanza esa exc.OBSERVACION
	@Test
	public void testAbreComandaIncorrecto3() {
		this.mozo1.setEstado(Enumerados.estadoMozo.AUSENTE);
		this.mesa0.setEstado(Enumerados.estadoMesa.LIBRE);
		try {
			this.func.abreComanda(this.mesa0.getNroMesa());
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
	public void testAgregaPedidoAComandaCorrecto() {
		try {
			Sistema.getInstance().getComandas().put(this.mesa0.getNroMesa(), com);
			this.func.AgregaPedidoAComanda(this.mesa0.getNroMesa(), this.prod1.getIdProd(), 1);
		} catch (MesaNoTieneComanda_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (StockInsuficiente_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
		Assert.assertNotNull("Se deberia haber agregado el pedido a la comanda", this.com.getPedidos().get(0));
	}
	
	@Test
	public void testAgregaPedidoAComandaSinMesaAsoc() {
		try {
			//Sistema.getInstance().getComandas().put(this.mesa0.getNroMesa(), com);
			this.func.AgregaPedidoAComanda(this.mesa0.getNroMesa(), this.prod1.getIdProd(), 1);
			Assert.fail("Se deberia lanzar excepcion de que la mesa no tiene una comanda aasociada");
		} catch (MesaNoTieneComanda_Exception e) {
			
		} catch (StockInsuficiente_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testAgregaPedidoAComandaSinStock() {
		try {
			Sistema.getInstance().getComandas().put(this.mesa0.getNroMesa(), com);
			this.func.AgregaPedidoAComanda(this.mesa0.getNroMesa(), this.prod1.getIdProd(), 16);
			Assert.fail("Se deberia lanzar excepcion de que no hay stock");
		} catch (MesaNoTieneComanda_Exception e) {
			Assert.fail("No deberia lanzar excepcion");			
		} catch (StockInsuficiente_Exception e) {
			
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testAgregaPedidoNoExistenteAComanda() {
		try {
			Sistema.getInstance().getComandas().put(this.mesa0.getNroMesa(), com);
			Producto p  = new Producto("Lima", 1, 68, 20);
			this.func.AgregaPedidoAComanda(this.mesa0.getNroMesa(), p.getIdProd(), 16);
			Assert.fail("Se deberia lanzar excepcion de que no existe el producto");
		} catch (MesaNoTieneComanda_Exception e) {
			Assert.fail("No deberia lanzar excepcion");			
		} catch (StockInsuficiente_Exception e) {			
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
		}
	}

	@Test
	public void testCierraComandaInexistente() {
		try {
			this.func.cierraComanda(46, Enumerados.formaDePago.CTADNI);
			Assert.fail("Deberia lanzar excepcion");
		} catch (MesaNoTieneComanda_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (ComandaYaCerrada_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			
		}
	}
	@Test
	public void testCierraComandaCorrecto() {
		try {
			com.setEstado(Enumerados.estadoComanda.ABIERTO);
			this.mesa0.setMozo(mozo1);
			Sistema.getInstance().getComandas().put(this.mesa0.getNroMesa(), com);
			this.func.cierraComanda(this.mesa0.getNroMesa(), Enumerados.formaDePago.CTADNI);
			Assert.assertTrue("El estado deberia ser cerrado", this.com.getEstado()==Enumerados.estadoComanda.CERRADO);
		} catch (MesaNoTieneComanda_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (ComandaYaCerrada_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testCierraComandaNoAsociada() {
		try {
			com.setEstado(Enumerados.estadoComanda.ABIERTO);
			this.mesa0.setMozo(mozo1);
			//Sistema.getInstance().getComandas().put(this.mesa0.getNroMesa(), com);
			this.func.cierraComanda(this.mesa0.getNroMesa(), Enumerados.formaDePago.CTADNI);
			Assert.fail("Deberia lanzar excepcion");
		} catch (MesaNoTieneComanda_Exception e) {
			
		} catch (ComandaYaCerrada_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testCierraComandaIncorrecto1() {
		try {
			com.setEstado(Enumerados.estadoComanda.CERRADO);
			this.mesa0.setMozo(mozo1);
			Sistema.getInstance().getComandas().put(this.mesa0.getNroMesa(), com);
			this.func.cierraComanda(this.mesa0.getNroMesa(), Enumerados.formaDePago.CTADNI);
			Assert.fail("Deberia lanzar excepcion");
		} catch (MesaNoTieneComanda_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (ComandaYaCerrada_Exception e) {
			
		} catch (NoExisteEnLaColeccion_Exception e) {			
			Assert.fail("No deberia lanzar excepcion");
		}
	}


	@Test
	public void testEstadisticasEmpleado() {
		double res = -1;
		this.mozo1.getMesasAtendidas().add(new MesaAtendida(this.mesa0, null, 500, Enumerados.formaDePago.CTADNI, null));
		try {
			res = this.func.estadisticasEmpleado(this.mozo1.getNyA());
			//System.out.println(res);
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
		Assert.assertTrue("No se calculan las estadisticas", res>=0);
	}
	
	@Test
	public void testEstadisticasEmpleadoSinMesasAtendidas() {
		double res = -1;
		this.mozo1.getMesasAtendidas().clear();
		try {
			res = this.func.estadisticasEmpleado(this.mozo1.getNyA());
			Assert.assertTrue("No se calculan las estadisticas", res>=0);
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}

	@Test
	public void testEmpleadoConMayorVolumenDeVenta() {
		MesaAtendida mesaAt = new MesaAtendida(this.mesa0, null, 500, Enumerados.formaDePago.CTADNI, null);
		MesaAtendida mesaAt2 = new MesaAtendida(this.mesa0, null, 400, Enumerados.formaDePago.CTADNI, null);
		this.mozo1.getMesasAtendidas().add(mesaAt);
		this.mozo2.getMesasAtendidas().add(mesaAt2);
		Mozo res = this.func.empleadoConMayorVolumenDeVenta();
		Assert.assertEquals("Devuelve el mozo incorrecto", res, mozo1);
	}

	@Test
	public void testEmpleadoConMenorVolumenDeVenta() {
		MesaAtendida mesaAt = new MesaAtendida(this.mesa0, null, 500, Enumerados.formaDePago.CTADNI, null);
		MesaAtendida mesaAt2 = new MesaAtendida(this.mesa0, null, 400, Enumerados.formaDePago.CTADNI, null);
		this.mozo1.getMesasAtendidas().add(mesaAt);
		this.mozo2.getMesasAtendidas().add(mesaAt2);
		Mozo res = this.func.empleadoConMenorVolumenDeVenta();
		Assert.assertEquals("Devuelve el mozo incorrecto", res, mozo2);
	}

	@Test
	public void testConsumoPromedioPorMesaConMesasAtendidas() {
		double res = -1;
		MesaAtendida mesaAt = new MesaAtendida(this.mesa0, null, 500, Enumerados.formaDePago.CTADNI, null);
		MesaAtendida mesaAt2 = new MesaAtendida(this.mesa0, null, 500, Enumerados.formaDePago.CTADNI, null);
		MesaAtendida mesaAt3 = new MesaAtendida(this.mesa0, null, 500, Enumerados.formaDePago.CTADNI, null);
		try {
			res = this.func.consumoPromedioPorMesa(this.mesa0.getNroMesa());
			//System.out.println(res);
			Assert.assertTrue("Calcula mal el promedio", res>=0);
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
		
	}
	
	@Test 
	public void testConsumoPromedioPorMesaSinMesasAtendidas() {
		double res = -1;
		try {
			res = this.func.consumoPromedioPorMesa(this.mesa0.getNroMesa());
			//System.out.println(res);
			Assert.assertTrue("Calcula mal el promedio", res>=0);
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
		
	}
}

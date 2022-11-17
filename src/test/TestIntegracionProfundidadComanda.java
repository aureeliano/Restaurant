package test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import org.junit.Assert;


import excepciones.ContrasenaIncorrecta_Exception;
import excepciones.MenosDe2ProdsEnPromocion_Exception;
import excepciones.MesaNoTieneComanda_Exception;
import excepciones.MesaOcupada_Exception;
import excepciones.MozoNoActivo_Exception;
import excepciones.NoExisteEnLaColeccion_Exception;
import excepciones.NoHayProductos_Exception;
import excepciones.OperarioNoActivo_Exception;
import excepciones.StockInsuficiente_Exception;
import excepciones.TodasMesasInhabilitadas_Exception;
import excepciones.TodosMozosInactivos_Exception;
import excepciones.UserNameIncorrecto_Exception;
import modelo.Comanda;
import modelo.Enumerados;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import negocio.FuncionalidadOperarios;
import negocio.Sistema;

public class TestIntegracionProfundidadComanda {
	private Sistema sistema = Sistema.getInstance();
	
	private String Nya;
	private String username;
	private String password;
	private Mesa mesa;
	private FuncionalidadOperarios funcOp;
	private Mozo mozo ;
	private Producto prod1;
	private Producto prod2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		Nya = "Juan";
		username = "Juan10";
	    password = "Juan123";
	    mesa = new Mesa(3);
		prod1 = new Producto("Papitas",7,10,50);
		prod2 = new Producto("Carne",10,15,40);
		mozo = new Mozo("Mozo",new Date(1,2,2000),2);
		
	    mozo.setEstado(Enumerados.estadoMozo.ACTIVO);
		mesa.setMozo(mozo);
		mesa.setEstado(Enumerados.estadoMesa.LIBRE);
		sistema.getOperarios().clear();
		sistema.getMesas().clear();
		sistema.getProductos().clear();
		sistema.getComandas().clear();
		sistema.getOperarios().put(username, new Operario(Nya,username,password));
		sistema.getMesas().put(mesa.getNroMesa(), mesa);
		sistema.getProductos().put(prod1.getIdProd(), prod1);
		sistema.getProductos().put(prod2.getIdProd(), prod2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProfundidadAbriComandaCorrecto() {

		try {
			this.funcOp = sistema.login(username, password,Nya, "Contra12");
			Assert.assertNotNull("Se deberia haber retornado una instancia de funcionalidad operario", funcOp);
		} catch (UserNameIncorrecto_Exception e1) {
			
			Assert.fail("No deberia lanzarse la UserNameIncorrecto_Exception");
			
		} catch (ContrasenaIncorrecta_Exception e1) {
			Assert.fail("No deberia lanzarse la ContrasenaIncorrecta_Exception");
		} catch (OperarioNoActivo_Exception e1) {
			Assert.fail("No deberia lanzarse la OperarioNoActivo_Exception");
		}	
		
		
		try {
			this.funcOp.abreComanda(mesa.getNroMesa());
			Assert.fail("Deberia lanzarse la MenosDe2ProdsEnPromocion_Exception");
		}  catch (TodosMozosInactivos_Exception e) {
			Assert.fail("No deberia lanzarse la TodosMozosInactivos_Exception");
		}  catch (MozoNoActivo_Exception e) {
			Assert.fail("No deberia lanzarse la MozoNoActivo_Exception");
		} catch (NoHayProductos_Exception e) {
			Assert.fail("No deberia lanzarse la NoHayProductos_Exception");
		} catch (MesaOcupada_Exception e) {
			Assert.fail("No deberia lanzarse la MesaOcupada_Exception");
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzarse la NoExisteEnLaColeccion_Exception");
		} catch (TodasMesasInhabilitadas_Exception e) {
			Assert.fail("No deberia lanzarse la TodasMesasInhabilitadas_Exception");
		} catch (MenosDe2ProdsEnPromocion_Exception e) {
			
		}
	}
	
	@Test
	public void testProfundidadAgregarPedidoComandaCorrecto() {
		
		sistema.getComandas().put(mesa.getNroMesa(), new Comanda(mesa));
		mesa.setEstado(Enumerados.estadoMesa.OCUPADA);
		
		try {
			this.funcOp = sistema.login(username, password,Nya, "Contra12");
			Assert.assertNotNull("Se deberia haber retornado una instancia de funcionalidad operario", funcOp);
		} catch (UserNameIncorrecto_Exception e1) {
			
			Assert.fail("No deberia lanzarse la UserNameIncorrecto_Exception");
			
		} catch (ContrasenaIncorrecta_Exception e1) {
			Assert.fail("No deberia lanzarse la ContrasenaIncorrecta_Exception");
		} catch (OperarioNoActivo_Exception e1) {
			Assert.fail("No deberia lanzarse la OperarioNoActivo_Exception");
		}	
		
		try {
			this.funcOp.AgregaPedidoAComanda(mesa.getNroMesa(), prod1.getIdProd(), 2);
		} catch (MesaNoTieneComanda_Exception e1) {
			Assert.fail("No deberia lanzarse la MesaNoTieneComanda_Exception");
		} catch (StockInsuficiente_Exception e1) {
			Assert.fail("No deberia lanzarse la StockInsuficiente_Exception");
		} catch (NoExisteEnLaColeccion_Exception e1) {
			Assert.fail("No deberia lanzarse la NoExisteEnLaColeccion_Exception");
		}
	}
	
	@Test
	public void testProfundidadAgregarPedidoComandaIncorrecto() {
		
		sistema.getComandas().put(mesa.getNroMesa(), new Comanda(mesa));
		mesa.setEstado(Enumerados.estadoMesa.OCUPADA);
		
		try {
			this.funcOp = sistema.login(username, password,Nya, "Contra12");
			Assert.assertNotNull("Se deberia haber retornado una instancia de funcionalidad operario", funcOp);
		} catch (UserNameIncorrecto_Exception e1) {
			
			Assert.fail("No deberia lanzarse la UserNameIncorrecto_Exception");
			
		} catch (ContrasenaIncorrecta_Exception e1) {
			Assert.fail("No deberia lanzarse la ContrasenaIncorrecta_Exception");
		} catch (OperarioNoActivo_Exception e1) {
			Assert.fail("No deberia lanzarse la OperarioNoActivo_Exception");
		}	
		
		try {
			this.funcOp.AgregaPedidoAComanda(mesa.getNroMesa(), prod1.getIdProd(), 100);
			Assert.fail("Deberia lanzarse la StockInsuficiente_Exception");
		} catch (MesaNoTieneComanda_Exception e1) {
			Assert.fail("No deberia lanzarse la MesaNoTieneComanda_Exception");
		} catch (NoExisteEnLaColeccion_Exception e1) {
			Assert.fail("No deberia lanzarse la NoExisteEnLaColeccion_Exception");
		} catch (StockInsuficiente_Exception e1) {
			
		} 
	}
	
}

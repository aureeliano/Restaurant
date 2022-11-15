package negocio;

import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Administrador;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;

public class FuncionalidadAdminTest2 {
	
	private FuncionalidadAdmin func = new FuncionalidadAdmin(new Administrador("Admin", "pass"));

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Mozo mozo = new Mozo ("Pau", new Date(10,10,1999), 2);
		Mesa mesa = new Mesa (4);
		Operario op = new Operario("Juan", "juanCarlos", "Carlos1234");
		Producto p = new Producto ("Pizza", 10, 20, 10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAgregaMozo() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAgregaMozoCorrecto() {
		this.func.agregaMozo(null, 0, 0, 0, 0);
	}
	
	@Test
	public void testAgregaMozoIncorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEliminaMozoCorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAgregaOperarioCorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAgregaOperarioIncorrecto() {
		fail("Not yet implemented");
	}
	@Test
	public void testEliminaOperarioCorrecto() {
		fail("Not yet implemented");
	}
	@Test
	public void testEliminaOperarioIncorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPasswordValida() {
		fail("Not yet implemented");
	}

	@Test
	public void testActivaDesactivaOperario() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAgregaProductoCorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAgregaProductoIncorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEliminaProductoCorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEliminaProductoIncorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testModificaStockProductoCorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testModificaStockProductoIncorrecto() {
		fail("Not yet implemented");
	}
	

	@Test
	public void testAgregaMesaCorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAgregaMesaIncorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEliminaMesaCorrecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEliminaMesaIncorrecto() {
		fail("Not yet implemented");
	}
}

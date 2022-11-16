package test;

import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.ContrasenaInvalida_Exception;
import excepciones.MozoMenorDeEdad_Exception;
import excepciones.NoExisteEnLaColeccion_Exception;
import excepciones.NoPuedeHaberMasDe6Mozos_Exception;
import excepciones.NyARepetido_Exception;
import excepciones.ProductoEstaEnComanda_Exception;
import excepciones.SilasDebenSerMayoresA1CuandoNroMesaMayorA1_Exception;
import excepciones.UserNameRepetido_Exception;
import modelo.Administrador;
import modelo.Comanda;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Pedido;
import modelo.Producto;
import negocio.FuncionalidadAdmin;
import negocio.Sistema;

public class FuncionalidadAdminTest2 {
	
	private FuncionalidadAdmin func = new FuncionalidadAdmin(new Administrador("Admin", "pass"));

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Mozo mozo = new Mozo ("JuanPerez", new Date(2,10,1983), 5);
		Mesa mesa = new Mesa (4);
		Operario op = new Operario("Juan", "juanCarlos", "Carlos1234");
		Producto p = new Producto ("Pizza", 10, 20, 10);
		Sistema.getInstance().getMozos().put("JuanPerez", mozo);
		Sistema.getInstance().getMesas().put(0, mesa);
		Sistema.getInstance().getOperarios().put("juanCarlos", op);
		Sistema.getInstance().getProductos().put(p.getIdProd(), p);
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
	public void testAgregaMozoCorrecto() {
		try {
			this.func.agregaMozo("Paula Boni", 10, 10, 1999, 5);		
		} catch (NyARepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		} catch (NoPuedeHaberMasDe6Mozos_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		} catch (MozoMenorDeEdad_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
		Mozo mozo = Sistema.getInstance().getMozos().get("Paula Boni");
		Assert.assertNotNull("El mozo deberia estar en la coleccion de mozos", mozo);
	}
	
	@Test
	public void testAgregaMozoIncorrecto() {
		try {
			this.func.agregaMozo("JuanPerez", 5, 6, 1999, 0);	
			Assert.fail("Se deberia haber lanzado excepcion");
		} catch (NyARepetido_Exception e) {
			
		} catch (NoPuedeHaberMasDe6Mozos_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo NoPuedeHaberMasDe6Mozos");
		} catch (MozoMenorDeEdad_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo MozoMenorDeEdad_Exception");
		}
	}
	
	@Test
	public void testAgregaMozoIncorrecto1() {
		try {
			this.func.agregaMozo("Juan Rodriguez", -1, -1, 1999, 0);	
			Assert.fail("Se deberia haber lanzado excepcion de fechas incorrectas");
		} catch (NyARepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo NyARepetido_Exception");
		} catch (NoPuedeHaberMasDe6Mozos_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo NoPuedeHaberMasDe6Mozos");
		} catch (MozoMenorDeEdad_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo MozoMenorDeEdad_Exception");
		}
	}
	
	@Test
	public void testAgregaMozoIncorrecto2() {
		try {
			this.func.agregaMozo("Juan Perez", 2, 10, 2004, 5);	
			Assert.fail("Se deberia haber lanzado excepcion de Edad invalida");
		} catch (NyARepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo NyARepetido_Exception");
		} catch (NoPuedeHaberMasDe6Mozos_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo NoPuedeHaberMasDe6Mozos");
		} catch (MozoMenorDeEdad_Exception e) {
		}
	}
	
	
	@Test
	public void testAgregaMozoIncorrecto3() {
		try {
			this.func.agregaMozo("Rodrigo Hernandez", 5, 6, 1999, 0);	
			this.func.agregaMozo("Juan Domingo", 5, 6, 1999, 0);	
			this.func.agregaMozo("Sarmiento", 5, 6, 1999, 0);	
			this.func.agregaMozo("Juan Pz", 5, 6, 1999, 0);	
			this.func.agregaMozo("Susana Perez", 5, 6, 1999, 0);	
			this.func.agregaMozo("Cristian Perez", 5, 6, 1999, 0);
			this.func.agregaMozo("Cristian U", 5, 6, 1999, 0);
			Assert.fail("Se deberia haber lanzado excepcion");
		} catch (NyARepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo NyARepetido_Exception");
		} catch (NoPuedeHaberMasDe6Mozos_Exception e) {

		} catch (MozoMenorDeEdad_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo MozoMenorDeEdad_Exception");
		}
	}

	@Test
	public void testEliminaMozoCorrecto() {
		try {
			this.func.eliminaMozo("Juan Perez");
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
		Mozo mozo = Sistema.getInstance().getMozos().get("Juan Perez");
		Assert.assertNull("El mozo NO deberia estar en la coleccion de mozos", mozo);
	}
	
	@Test
	public void testEliminaMozoIncorrecto() {
		try {
			this.func.eliminaMozo("Luis Miguel");
		} catch (NoExisteEnLaColeccion_Exception e) {
			
		}
		Assert.fail("Deberia lanzarse excepcion de que no existe el mozo a eliminar");
	}
	
	@Test
	public void testAgregaOperarioCorrecto() {
		try {
			this.func.agregaOperario("Juan Perez", "juanPerez", "Juan1234");
		} catch (UserNameRepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		} catch (ContrasenaInvalida_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
		Operario op = Sistema.getInstance().getOperarios().get("juanPerez");
		Assert.assertNotNull("El operario deberia estar en la coleccion de operarios", op);
	}
	
	@Test
	public void testAgregaOperarioIncorrecto() {
		try {
			this.func.agregaOperario("Perez", "juanPerez", "Juann89");
			Assert.fail("Deberia lanzarse excepcion de tipo UserNameRepetido_Exception");
		} catch (UserNameRepetido_Exception e) {
			
		} catch (ContrasenaInvalida_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
	}
	
	@Test
	public void testAgregaOperarioIncorrecto1() {
		try {
			this.func.agregaOperario("Juan Perez", "juanPerez123", "Juan1");
			Assert.fail("Deberia lanzarse excepcion de tipo ContrasenaInvalida_Exception");
		} catch (UserNameRepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");			
		} catch (ContrasenaInvalida_Exception e) {
		}
	}
	
	@Test
	public void testAgregaOperarioIncorrecto2() {
		try {
			this.func.agregaOperario("Juan Perez", "juan123", "Juan1235678910111216");
			Assert.fail("Deberia lanzarse excepcion de tipo ContrasenaInvalida_Exception");
		} catch (UserNameRepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");			
		} catch (ContrasenaInvalida_Exception e) {
		}
	}
	
	@Test
	public void testAgregaOperarioIncorrecto3() {
		try {
			this.func.agregaOperario("Juan Perez Rodriguez", "juanPerez124", "juan1234");
			Assert.fail("Deberia lanzarse excepcion de tipo ContrasenaInvalida_Exception");
		} catch (UserNameRepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");			
		} catch (ContrasenaInvalida_Exception e) {
		}
	}
	
	@Test
	public void testAgregaOperarioIncorrecto4() {
		try {
			this.func.agregaOperario("Juan Perez", "juanPerez145", "JuanPerez");
			Assert.fail("Deberia lanzarse excepcion de tipo ContrasenaInvalida_Exception");
		} catch (UserNameRepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");			
		} catch (ContrasenaInvalida_Exception e) {
		}
	}
	
	@Test
	public void testEliminaOperarioCorrecto() {
		try {
			this.func.eliminaOperario("juanPerez");
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
		Operario op = Sistema.getInstance().getOperarios().get("juanPerez");
		Assert.assertNull("El operario deberia haber sido eliminado");
	}
	
	@Test
	public void testEliminaOperarioIncorrecto() {
		try {
			this.func.eliminaOperario("pepe");
		} catch (NoExisteEnLaColeccion_Exception e) {

		}
		Assert.fail("Deberia lanzar excepcion de que no existe el operario a eliminar");
	}
	
	@Test
	public void testPasswordValida() {
		boolean res = false;
		res = this.func.PasswordValida("Hernan1234");
		Assert.assertTrue("La contrasenia deberia ser valida", res);
	}
	
	@Test
	public void testPasswordValidaIncorrecto() {
		boolean res = true;
		res = this.func.PasswordValida("hernan1234");
		Assert.assertFalse("La contrasenia NO deberia ser valida", res);
	}

/*	
	@Test
	public void testActivaDesactivaOperario() {
		this.func.activaDesactivaOperario("hernan", false);
		Sistema.getInstance().log
		Assert.assertFalse("Deberia estar inactivo el operario", Sistema.getInstance().getOperarios().get("hernan").get);
	}
	*/
	
	
	@Test
	public void testAgregaProductoCorrecto() {
		this.func.agregaProducto("Agua", 10, 20, 10);
		Producto producto = Sistema.getInstance().getProductos().get(1);
		Assert.assertNotNull("El producto deberia estar en la coleccion de productos", producto);
	}
	
	@Test
	public void testEliminaProductoCorrecto() {
		try {
			this.func.eliminaProducto(0);
		} catch (ProductoEstaEnComanda_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
		Assert.assertNull("El producto deberia haber sido eliminado", Sistema.getInstance().getProductos().get(0));
	}
	
	@Test
	public void testEliminaProductoIncorrecto() {
		try {
			this.func.eliminaProducto(15);
			Assert.fail("Deberia haber una excepcion de que no existe el producto");
		} catch (ProductoEstaEnComanda_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
			
		}
	}
	
	@Test
	public void testEliminaProductoIncorrecto1() {
		try {
			Sistema.getInstance().getComandas().put(0, new Comanda(Sistema.getInstance().getMesas().get(0)));
			Producto prod = new Producto ("Pizza", 10, 20, 10);
			Sistema.getInstance().getProductos().put(prod.getIdProd(), prod);
			Comanda comanda = Sistema.getInstance().getComandas().get(0);
			comanda.getPedidos().add(new Pedido(prod, 2));
			this.func.eliminaProducto(prod.getIdProd());
			Assert.fail("Deberia haber una excepcion de que esta en uso");
		} catch (ProductoEstaEnComanda_Exception e) {
			
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testModificaStockProductoCorrecto() {
		try {
			this.func.modificaStockDeProducto(0, 12, 25);
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
		Assert.assertEquals("El stock inicial se deberia haber modificado",12, Sistema.getInstance().getProductos().get(0).getStockInicial());
		Assert.assertEquals("El stock actual se deberia haber modificado",25, Sistema.getInstance().getProductos().get(0).getStockActual());
	}
	
	@Test
	public void testModificaStockProductoIncorrecto() {
		try {
			this.func.modificaStockDeProducto(500, 12, 25);
		} catch (NoExisteEnLaColeccion_Exception e) {
			
		}
		Assert.fail("Deberia tirar una excepcion de que no existe el producto");
	}
	

	@Test
	public void testAgregaMesaCorrecto() {
		try {
			this.func.agregaMesa(5);
		} catch (SilasDebenSerMayoresA1CuandoNroMesaMayorA1_Exception e) {
			Assert.fail("No deberia tirar excepcion");
		}
	}
	
	@Test
	public void testAgregaMesaIncorrecto() {
		try {
			this.func.agregaMesa(1);
			Assert.fail("Deberia tirar excepcion SilasDebenSerMayoresA1CuandoNroMesaMayorA1_Exception");
		} catch (SilasDebenSerMayoresA1CuandoNroMesaMayorA1_Exception e) {
			
		}
	}
	
	@Test
	public void testEliminaMesaCorrecto() {
		try {
			this.func.eliminaMesa(0);
		} catch (NoExisteEnLaColeccion_Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
		Assert.assertNull("La mesa deberia haber sido eliminada", Sistema.getInstance().getMesas().get(0));
	}
	
	@Test
	public void testEliminaMesaIncorrecto() {
		try {
			this.func.eliminaMesa(55);
		} catch (NoExisteEnLaColeccion_Exception e) {
		}
		Assert.fail("Deberia tirar excepcion");
	}
}

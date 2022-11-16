package test;

import static org.junit.Assert.fail;

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
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import negocio.FuncionalidadAdmin;
import negocio.Sistema;

public class FuncionalidadAdminTest {
	
	private FuncionalidadAdmin func = new FuncionalidadAdmin(new Administrador("Admin", "pass"));

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUpEscenario1() throws Exception {
		Sistema.getInstance().getMozos().clear();
		Sistema.getInstance().getMesas().clear();
		Sistema.getInstance().getOperarios().clear();
		Sistema.getInstance().getProductos().clear();
		//se ejecuta antes de cada metodo
	}

	@After
	public void tearDown() throws Exception {
		Sistema.getInstance().getMozos().clear();
		Sistema.getInstance().getMesas().clear();
		Sistema.getInstance().getOperarios().clear();
		Sistema.getInstance().getProductos().clear();
	}
	
	@Test
	public void testAgregaMozoCorrecto() {
		try {
			this.func.agregaMozo("Juan Perez", 2, 10, 1983, 5);		
		} catch (NyARepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		} catch (NoPuedeHaberMasDe6Mozos_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		} catch (MozoMenorDeEdad_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
		Mozo mozo = Sistema.getInstance().getMozos().get("Juan Perez");
		Assert.assertNotNull("El mozo deberia estar en la coleccion de mozos", mozo);
	}
	
	@Test
	public void testAgregaMozoIncorrecto1() {
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
	public void testAgregaMozoIncorrecto2() {
		try {
			this.func.agregaMozo("Juancito", 5, 6, 2007, 0);	
			Assert.fail("Se deberia haber lanzado excepcion");
		} catch (NyARepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo NyARepetido_Exception");
		} catch (NoPuedeHaberMasDe6Mozos_Exception e) {
			Assert.fail("No deberia lanzarse excepcion de tipo NoPuedeHaberMasDe6Mozos_Exception");
		} catch (MozoMenorDeEdad_Exception e) {
			
		}
	}
	
	
	@Test
	public void testEliminaMozo() {
		try {
			this.func.eliminaMozo("Juan Perez");
			Assert.fail("Deberia tirar excepcion de que no existe el mozo a eliminar");
		} catch (NoExisteEnLaColeccion_Exception e) {
			
		}
	}
	
	
	@Test
	public void testAgregaOperarioCorrecto() {
		try {
			this.func.agregaOperario("Hernan Cattaneo", "hernan", "Cattaneo123");
		} catch (UserNameRepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		} catch (ContrasenaInvalida_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
		Operario op = Sistema.getInstance().getOperarios().get("hernan");
		Assert.assertNotNull("El operario deberia estar en la coleccion de operarios", op);
	}

	
	@Test
	public void testAgregaOperarioIncorrecto2() {
		try {
			this.func.agregaOperario("Luisa Fernandez", "luisaF", "luisa");
			Assert.fail("Deberia lanzarse excepcion de tipo ContrasenaInvalida_Exception");
		} catch (UserNameRepetido_Exception e) {
			Assert.fail("No deberia lanzarse excepcion");			
		} catch (ContrasenaInvalida_Exception e) {
		}
	}
	
	@Test
	public void testEliminaOperario() {
		try {
			this.func.eliminaOperario("hernan");
			Assert.fail("Deberia tirar excepcion de que no existe el mozo a eliminar");
		} catch (NoExisteEnLaColeccion_Exception e) {
			
		}
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

/*	NO PODEMOS OBTENER EL ESTADO PARA TESTEARLO
	@Test
	public void testActivaDesactivaOperario() {
		this.func.activaDesactivaOperario("hernan", false);
		Sistema.getInstance().log
		Assert.assertFalse("Deberia estar inactivo el operario", Sistema.getInstance().getOperarios().get("hernan").get);
	}
	*/
	
	@Test
	public void testAgregaProductoCorrecto() {
		this.func.agregaProducto("Pizza", 10, 20, 10);
		Producto producto = Sistema.getInstance().getProductos().get(0);
		Assert.assertNotNull("El producto deberia estar en la coleccion de productos", producto);
	}
	
	@Test
	public void testEliminaProductoIncorrecto() {
		try {
			this.func.eliminaProducto(0);
			Assert.fail("Deberia lanzar excepcion de que no existe el producto");
		} catch (ProductoEstaEnComanda_Exception e) {
			Assert.fail("No  se debe lanzar esta excepcion");
		} catch (NoExisteEnLaColeccion_Exception e) {
		}
	}
	

	@Test
	public void testAgregaMesaCorrecto() {
		try {
			this.func.agregaMesa(1);
		} catch (SilasDebenSerMayoresA1CuandoNroMesaMayorA1_Exception e) {
			Assert.fail("No se debe lanzar esta excepcion");
		}
		Mesa mesa = Sistema.getInstance().getMesas().get(0);
		Assert.assertNotNull("Se deberia haber agregado la mesa", mesa);
	}

	
	@Test
	public void testEliminaMesa() {
		try {
			this.func.eliminaMesa(0);
			Assert.fail("Deberia lanzar excepcion de que no existe la mesa");
		} catch (NoExisteEnLaColeccion_Exception e) {
		
		}
	}
}

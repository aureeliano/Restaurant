package test;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.ContrasenaIncorrecta_Exception;
import excepciones.OperarioNoActivo_Exception;
import excepciones.UserNameIncorrecto_Exception;
import modelo.Administrador;
import modelo.Operario;
import negocio.FuncionalidadOperarios;
import negocio.Sistema;

public class SistemaTest_ColeccionVacia {

	private Sistema sistema = Sistema.getInstance();
	private Operario op = new Operario("Juan", "juanPerez", "Juan123");
	private Administrador admin = new Administrador("Admin", "ADMIN1234");
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		admin.setNyA("Admin");
		admin.setPassword("Admin1234");
		Sistema.getInstance().getOperarios().clear();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoginCorrectoOp() {
		FuncionalidadOperarios func=null;
		try {
			func = this.sistema.login(op.getUsername(), op.getPassword(), admin.getNyA(), admin.getPassword());
			Assert.fail("se deberia lanzar excepcion de que no existe");
		} catch (UserNameIncorrecto_Exception e) {
			
		} catch (ContrasenaIncorrecta_Exception e) {
			Assert.fail("No debería lanzar excepcion");
		} catch (OperarioNoActivo_Exception e) {
			Assert.fail("No debería lanzar excepcion");
		}
	}
	
	@Test
	public void testLoginIncorrectoAdCorto() {
		FuncionalidadOperarios func=null;
		try {
			this.sistema.login("ADMIN", "ADMIN1234", admin.getNyA(), "Admi1");
			Assert.fail("Se deberia lanzar excepcion");
		} catch (UserNameIncorrecto_Exception e) {
			Assert.fail("No se deberia lanzar excepcion");
		} catch (ContrasenaIncorrecta_Exception e) {
			
		} catch (OperarioNoActivo_Exception e) {
			Assert.fail("No se deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testLoginIncorrectoLargo() {
		FuncionalidadOperarios func=null;
		try {
			this.sistema.login("ADMIN", "ADMIN1234", admin.getNyA(), "Admi1255555555555555555");
			Assert.fail("Se deberia lanzar excepcion");
		} catch (UserNameIncorrecto_Exception e) {
			Assert.fail("No se deberia lanzar excepcion");
		} catch (ContrasenaIncorrecta_Exception e) {
			
		} catch (OperarioNoActivo_Exception e) {
			Assert.fail("No se deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testLoginIncorrectoAdSinMayus() {
		FuncionalidadOperarios func=null;
		try {
			this.sistema.login("ADMIN", "ADMIN1234", admin.getNyA(), "admin125");
			Assert.fail("Se deberia lanzar excepcion");
		} catch (UserNameIncorrecto_Exception e) {
			Assert.fail("No se deberia lanzar excepcion");
		} catch (ContrasenaIncorrecta_Exception e) {
			
		} catch (OperarioNoActivo_Exception e) {
			Assert.fail("No se deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testLoginIncorrectoAdSinDigito() {
		FuncionalidadOperarios func=null;
		try {
			this.sistema.login("ADMIN", "ADMIN1234", admin.getNyA(), "Adminnnn");
			Assert.fail("Se deberia lanzar excepcion");
		} catch (UserNameIncorrecto_Exception e) {
			Assert.fail("No se deberia lanzar excepcion");
		} catch (ContrasenaIncorrecta_Exception e) {
			
		} catch (OperarioNoActivo_Exception e) {
			Assert.fail("No se deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testLoginIncorrectoAdNulo() {
		FuncionalidadOperarios func=null;
		try {
			this.sistema.login("ADMIN", "ADMIN1234", admin.getNyA(),null);
			Assert.fail("Se deberia lanzar excepcion");
		} catch (UserNameIncorrecto_Exception e) {
			Assert.fail("No se deberia lanzar excepcion");
		} catch (ContrasenaIncorrecta_Exception e) {
			
		} catch (OperarioNoActivo_Exception e) {
			Assert.fail("No se deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testLoginIncorrectoAdVacio() {
		FuncionalidadOperarios func=null;
		try {
			this.sistema.login("ADMIN", "ADMIN1234", admin.getNyA(),"");
			Assert.fail("Se deberia lanzar excepcion");
		} catch (UserNameIncorrecto_Exception e) {
			Assert.fail("No se deberia lanzar excepcion");
		} catch (ContrasenaIncorrecta_Exception e) {
			
		} catch (OperarioNoActivo_Exception e) {
			Assert.fail("No se deberia lanzar excepcion");
		}
	}
	
	
	//ES REDUNDANTE HACER ESTOS
	/*@Test
	public void testLoginOperario() {
		fail("Not yet implemented");
	}
	@Test
	public void testLoginAdmin() {
		fail("Not yet implemented");
	}*/

}

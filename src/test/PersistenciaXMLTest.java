package test;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Mesa;
import persistencia.PersistenciaXML;

public class PersistenciaXMLTest {

	private PersistenciaXML persiste = new PersistenciaXML();
	
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

	/*@Test
	public void testAbrirInput() {
		try {
			this.persiste.abrirInput("Archivo.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void testCerrarInput() {
		try {
			this.persiste.cerrarInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAbrirOutput() {
		try {
			this.persiste.abrirOutput("Archivo.xml");
		} catch (IOException e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
	}
	
	@Test
	public void testcerrarOutput() {
		try {
			this.persiste.cerrarOutput();
		} catch (IOException e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
	}
	
	@Test
	public void testEscribir() {
		try {
			this.persiste.escribir(new Mesa(5));
		} catch (IOException e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
	}
	
	/*@Test
	public void testLeer() {
		try {
			Mesa mesa = (Mesa) this.persiste.leer();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	

}

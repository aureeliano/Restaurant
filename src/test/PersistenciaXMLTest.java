package test;

import java.io.IOException;
import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Comanda;
import modelo.Enumerados;
import modelo.Mesa;
import modelo.MesaAtendida;
import modelo.Mozo;
import modelo.Producto;
import negocio.Sistema;
import persistencia.PersistenciaXML;

public class PersistenciaXMLTest {

	private PersistenciaXML persiste = new PersistenciaXML();
	private Mesa m1 = new Mesa(8);;
	private Producto prod1;
	
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
			this.persiste.abrirInput("Archivo.xml");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	
	public void agregaObj() {
		Mesa m2 = new Mesa(8);
		Sistema.getInstance().getMesas().put(m1.getNroMesa(), m1);
		Sistema.getInstance().getMesas().put(m2.getNroMesa(), m2);
		prod1 = new Producto ("Pizza", 10, 20, 20);
		Sistema.getInstance().getProductos().put(prod1.getIdProd(), prod1);
		MesaAtendida mesaAtendida = new MesaAtendida(m1, null, 200, Enumerados.formaDePago.CTADNI, null);
		Mozo mozo = new Mozo ("Juan", new Date(2,3,1999), 4);
		Mozo mozo2 = new Mozo ("Pedro", new Date(2,3,1999), 4);
		mozo.getMesasAtendidas().add(mesaAtendida);
		Sistema.getInstance().getMozos().put(mozo.getNyA(), mozo);
		Sistema.getInstance().getMozos().put(mozo2.getNyA(), mozo2);
		Comanda com = new Comanda(m1);
		Sistema.getInstance().getComandas().put(m1.getNroMesa(), com);
	}
	
	public void vacia() {
		Sistema.getInstance().getMesas().clear();
		Sistema.getInstance().getMozos().clear();
		Sistema.getInstance().getMesas().clear();
		Sistema.getInstance().getMesas().clear();
	}
	
	@Test
	public void testEscribir() {
		try {
			this.agregaObj();
			this.persiste.abrirOutput("Archivo.xml");
			this.persiste.escribir(Sistema.getInstance().getMozos().get("Juan"));
			this.persiste.escribir(Sistema.getInstance().getMozos().get(prod1.getIdProd()));
			this.persiste.escribir(Sistema.getInstance().getMesas().get(m1.getNroMesa()));
			this.persiste.escribir(Sistema.getInstance().getMozos().get("Juan").getMesasAtendidas().get(0));
			this.persiste.escribir(Sistema.getInstance().getComandas().get(0));
			this.persiste.cerrarOutput();
		} catch (IOException e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
	}
	
	//habria que ponerlo como DTO?? o eso va en el leer?
	@Test
	public void testLeer() throws ClassNotFoundException {
		try {
			this.persiste.abrirInput("Archivo.xml");			
			Mozo mozo = (Mozo) this.persiste.leer();
			Producto prod = (Producto) this.persiste.leer();
			Mesa mesa = (Mesa) this.persiste.leer();
			MesaAtendida mesaAt = (MesaAtendida) this.persiste.leer();
			Comanda com = (Comanda) this.persiste.leer();
			Assert.assertTrue ("No se leyo correctamente el producto", prod.getNombre().equals(prod1.getNombre()) && prod1.getIdProd()==prod.getIdProd());
			Assert.assertTrue ("No se leyo correctamente el mozo", mozo.getNyA().equals("Juan") && mozo.getCantHijos()==4);
			Assert.assertTrue ("No se leyo correctamente la mesa atendida",mesaAt.getTotal()==200 && mesaAt.getMesa().getNroMesa()==0);
			Assert.assertTrue ("No se leyo correctamente la comanda",com.getMesa().getNroMesa()==0);
			Assert.assertTrue ("No se leyo correctamente la mesa", mesa.getNroMesa()==this.m1.getNroMesa() && mesa.getCantSillas()==this.m1.getCantSillas());
			this.persiste.cerrarInput();
		} catch (IOException e) {
			Assert.fail("No deberia lanzarse excepcion");
		}
	}
	

}

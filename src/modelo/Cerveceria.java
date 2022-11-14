package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import negocio.Sistema;

public class Cerveceria {

	
	private HashMap<String, Mozo> mozos = new HashMap<String, Mozo>();
	private HashMap<Integer, Producto> productos = new HashMap<Integer, Producto>();	
	private HashMap<String, Operario> operarios = new HashMap<String, Operario>();
	private HashMap<Integer, Mesa> mesas = new HashMap<Integer, Mesa>();
	private HashMap<Integer, PromocionProd> promocionProds = new HashMap<Integer, PromocionProd>();
	private ArrayList<PromocionTemporal> promocionTemp = new ArrayList<PromocionTemporal>();
	private HashMap<Integer,Comanda> comandas = new HashMap<Integer,Comanda>();
	private static Cerveceria instance = null;
	private String usuarioAdministrador="ADMIN";
	private String codigoAdministrador = "ADMIN1234";
	
	public static Cerveceria getInstance() {
		if (instance == null)
			instance = new Cerveceria();
		return instance;
	}

	public HashMap<String, Mozo> getMozos() {
		return mozos;
	}

	public HashMap<Integer, Producto> getProductos() {
		return productos;
	}

	public HashMap<String, Operario> getOperarios() {
		return operarios;
	}

	public HashMap<Integer, Mesa> getMesas() {
		return mesas;
	}

	public HashMap<Integer, PromocionProd> getPromocionProds() {
		return promocionProds;
	}

	public HashMap<Integer,Comanda> getComandas() {
		return comandas;
	}

	public ArrayList<PromocionTemporal> getPromocionTemp() {
		return promocionTemp;
	}
	
	
	
	
	
}

package persistencia;

import java.io.Serializable;
import java.util.HashMap;

import modelo.Producto;

public class CerveceriaDTO implements Serializable
{
	private HashMap<String, MozoDTO> mozos = new HashMap<String, MozoDTO>();
	private HashMap<Integer, Producto> productos = new HashMap<Integer, Producto>();
	private HashMap<Integer,ComandaDTO> comandas = new HashMap<Integer,ComandaDTO>();
	
	
	public CerveceriaDTO() {}


	public HashMap<String, MozoDTO> getMozos() {
		return mozos;
	}


	public void setMozos(HashMap<String, MozoDTO> mozos) {
		this.mozos = mozos;
	}


	public HashMap<Integer, Producto> getProductos() {
		return productos;
	}


	public void setProductos(HashMap<Integer, Producto> productos) {
		this.productos = productos;
	}


	public HashMap<Integer, ComandaDTO> getComandas() {
		return comandas;
	}


	public void setComandas(HashMap<Integer, ComandaDTO> comandas) {
		this.comandas = comandas;
	}
	
	
}

package persistencia;

import java.util.HashMap;
import java.util.Map.Entry;

import modelo.Cerveceria;
import modelo.Comanda;
import modelo.Mozo;
import negocio.Sistema;

public class UtilCerveceria {
	public static CerveceriaDTO cerveceriaDTOfromCerveceria(Cerveceria cerveceria) 
	{
		CerveceriaDTO respuesta = new CerveceriaDTO();
		
		//hago serializacion de los mozos
		HashMap<String, MozoDTO> mozosdto = new HashMap<String, MozoDTO>();
		java.util.Iterator<Entry<String, Mozo>> it1 = Sistema.getInstance().getMozos().entrySet().iterator();
		Entry<String, Mozo> entry1 = null;
		while(it1.hasNext())
		{
			entry1 = it1.next();
			mozosdto.put(entry1.getKey(), mozoDTOfromMozo(entry1.getValue()));
		}
		respuesta.setMozos(mozosdto);
		
		
		//hago serializacion de las comandas
		HashMap<Integer, ComandaDTO> comandasdto = new HashMap<Integer, ComandaDTO>();
		java.util.Iterator<Entry<Integer, Comanda>> it2 = Sistema.getInstance().getComandas().entrySet().iterator();
		Entry<Integer, Comanda> entry2 = null;
		while(it2.hasNext())
		{
			entry2 = it2.next();
			comandasdto.put(entry2.getKey(), comandaDTOfromComanda(entry2.getValue()));
		}
		respuesta.setComandas(comandasdto);
	
		respuesta.setProductos(Sistema.getInstance().getProductos());
		
		return respuesta;

	}

	public static Cerveceria cerveceriafromCerveceriaDTO(CerveceriaDTO cervDTO) 
	{
		Cerveceria cerveceria = new Cerveceria();
		
		//hago serializacion de mozos
		HashMap<String, Mozo> mozos = new HashMap<String, Mozo>();
		java.util.Iterator<Entry<String, MozoDTO>> it1 = cervDTO.getMozos().entrySet().iterator();
		Entry<String, MozoDTO> entry1 = null;
		while(it1.hasNext())
		{
			entry1 = it1.next();
			mozos.put(entry1.getKey(), mozofromMozoDTO(entry1.getValue()));
		}
		cerveceria.setMozos(mozos);
		
		//hago serializacion de comandas
		HashMap<Integer,Comanda> comandas = new HashMap<Integer,Comanda>();
		java.util.Iterator<Entry<Integer, ComandaDTO>> it2 = cervDTO.getComandas().entrySet().iterator();
		Entry<Integer, ComandaDTO> entry2 = null;
		while(it2.hasNext())
		{
			entry2 = it2.next();
			comandas.put(entry2.getKey(), comandafromComandaDTO(entry2.getValue()));
		}
		cerveceria.setComandas(comandas);
		
		cerveceria.setProductos(cervDTO.getProductos());
		
		return cerveceria;
	}

	

	public static MozoDTO mozoDTOfromMozo(Mozo mozo) 
	{
		MozoDTO respuesta = new MozoDTO();
		
		respuesta.setEstado(mozo.getEstado());
		respuesta.setNyA(mozo.getNyA());
		respuesta.setFechaDeNacimiento(mozo.getFechaDeNacimiento());
		respuesta.setCantHijos(mozo.getCantHijos());
		respuesta.setMesasAtendidas(mozo.getMesasAtendidas());
		respuesta.setSueldo(mozo.getSueldo());
		
		return respuesta;
	}


	public static Mozo mozofromMozoDTO(MozoDTO mozodto) 
	{
		Mozo respuesta = new Mozo(mozodto.getNyA(), mozodto.getFechaDeNacimiento(),
				mozodto.getCantHijos());
		
		return respuesta;
	}

	public static ComandaDTO comandaDTOfromComanda(Comanda comanda)
	{
		ComandaDTO respuesta = new ComandaDTO();
		
		respuesta.setEstado(comanda.getEstado());
		respuesta.setFecha(comanda.getFecha());
		respuesta.setMesa(comanda.getMesa());
		respuesta.setPedidos(comanda.getPedidos());
		
		return respuesta;
	}
	
	public static Comanda comandafromComandaDTO(ComandaDTO comandadto)
	{
		Comanda respuesta = new Comanda(comandadto.getMesa());
		
		return respuesta;
	}
}

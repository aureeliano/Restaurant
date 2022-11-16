package prueba;

import java.io.IOException;

import modelo.Cerveceria;
import persistencia.CerveceriaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.PersistenciaXML;
import persistencia.UtilCerveceria;

public class PruebaEscritura {

	public static void main(String[] args) 
	{
		IPersistencia persistencia = new PersistenciaXML();
	    //IPersistencia persistencia = new PersistenciaBIN();
		Cerveceria cerveceria = new Cerveceria();
		
		/*
		 * crear los mozos, pedidos, etc etc para serializar 
		 */
		
		try
		{
			persistencia.abrirOutput("Cerveceria.xml");
			//persistencia.abrirOutput("Cerveceria.BIN");
            System.out.println("Crea archivo escritura");
            CerveceriaDTO cervDTO = UtilCerveceria.cerveceriaDTOfromCerveceria(cerveceria);
            
            persistencia.escribir(cervDTO);
            
            System.out.println("Escuela grabada exitosamente");
            persistencia.cerrarOutput();
            System.out.println("Archivo cerrado");
            
		} catch (IOException e)
		{
			System.out.println(e.getLocalizedMessage());
		}
		
	}

}

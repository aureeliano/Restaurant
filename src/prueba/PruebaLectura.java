package prueba;

import java.io.IOException;

import modelo.Cerveceria;
import persistencia.CerveceriaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.PersistenciaXML;
import persistencia.UtilCerveceria;

public class PruebaLectura {

	public static void main(String[] args) 
	{
		IPersistencia persistencia = new PersistenciaXML();
        //IPersistencia persistencia = new PersistenciaBIN();
		
		Cerveceria cerveceria = null;
		
		try
		{
			persistencia.abrirInput("Cerveceria.xml");
			//persistencia.abrirInput("Cerveceria.BIN");
			System.out.println("Archivo abierto");
			CerveceriaDTO cervDTO = (CerveceriaDTO) persistencia.leer();
			UtilCerveceria.cerveceriafromCerveceriaDTO(cervDTO);
			
			System.out.println("Cerveceria grabada exitosamente");
			persistencia.cerrarInput();
            System.out.println("Archivo cerado");
			
		}catch (IOException e)
        {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

	}

}

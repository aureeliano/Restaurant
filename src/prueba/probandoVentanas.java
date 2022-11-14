package prueba;

import java.util.GregorianCalendar;

import controlador.ControladorVentana;
import modelo.Cerveceria;
import modelo.Mozo;

public class probandoVentanas {

	public static void main(String[] args) 
	{
		Mozo mozo1 = new Mozo("Valen", new GregorianCalendar(1999,8,20),0);
		Mozo mozo2 = new Mozo("Marti", new GregorianCalendar(1998,9,21),0);
		Mozo mozo3 = new Mozo("Pau", new GregorianCalendar(1997,10,22),0);
		Mozo mozo4 = new Mozo("Aure", new GregorianCalendar(1996,11,23),0);
		
		Cerveceria.getInstance().getMozos().put(mozo1.getNyA(), mozo1);
		Cerveceria.getInstance().getMozos().put(mozo2.getNyA(), mozo2);
		Cerveceria.getInstance().getMozos().put(mozo3.getNyA(), mozo3);
		Cerveceria.getInstance().getMozos().put(mozo4.getNyA(), mozo4);
		
		ControladorVentana controladorVentana = new ControladorVentana();
	}

}

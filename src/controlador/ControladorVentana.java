package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import vista.IVista;
import vista.Ventana;

public class ControladorVentana implements ActionListener, Observer
{

	private IVista vista;
	
	
	
	
	public ControladorVentana() 
	{
		this.vista = new Ventana();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

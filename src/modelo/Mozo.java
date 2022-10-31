package modelo;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Mozo
{
	private Enumerados.estadoMozo estado;
	private String NyA;
	private GregorianCalendar fechaDeNacimiento;
	private int cantHijos;
	private ArrayList <MesaAtendida> mesasAtendidas;
	private double sueldo;

    /**
     * Constructor de cada mozo. <br>
     * Pre: el mozo debe ser mayor a 18 a�os. <br>
     * Pre: la cantidad de hijos debe ser >= 0. <br>
     * @param NyA perteneciente al nombre y apellido del mozo. <br>
     * @param cantHijos cantidad de hijos que tiene el mozo. <br>
     */
    public Mozo(String NyA, GregorianCalendar fechaDeNacimiento, int cantHijos) {
        this.estado = Enumerados.estadoMozo.ACTIVO;
        this.NyA = NyA;
        this.fechaDeNacimiento = fechaDeNacimiento; //setear fecha actual
        this.cantHijos = cantHijos;
        this.mesasAtendidas = new ArrayList<MesaAtendida>();
        this.sueldo =  Sueldo.calculaSueldo(cantHijos);
    }

	public Enumerados.estadoMozo getEstado()
	{
		return estado;
	}

	public void setEstado(Enumerados.estadoMozo estado)
	{
		this.estado = estado;
	}

	public String getNyA()
	{
		return NyA;
	}

	public void setNyA(String nyA)
	{
		NyA = nyA;
	}

	public int getCantHijos()
	{
		return cantHijos;
	}

	public void setCantHijos(int cantHijos)
	{
		this.cantHijos = cantHijos;
	}

	public ArrayList<MesaAtendida> getMesasAtendidas()
	{
		return mesasAtendidas;
	}

	public double getSueldo()
	{
		return sueldo;
	}

    
    
}

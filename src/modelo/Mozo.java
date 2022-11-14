package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Mozo
{
	private Enumerados.estadoMozo estado = Enumerados.estadoMozo.ACTIVO;;
	private String NyA;
	private Date fechaDeNacimiento;
	private int cantHijos;
	private ArrayList<MesaAtendida> mesasAtendidas = new ArrayList<MesaAtendida>();
	private double sueldo;

	/**
	 * Constructor de cada mozo. <br>
	 * Pre: el mozo debe ser mayor a 18 a�os. <br>
	 * Pre: la cantidad de hijos debe ser >= 0. <br>
	 * 
	 * @param NyA       perteneciente al nombre y apellido del mozo. <br>
	 * @param cantHijos cantidad de hijos que tiene el mozo. <br>
	 */
	public Mozo(String NyA, Date fechaDeNacimiento, int cantHijos)
	{
		this.NyA = NyA;
		this.fechaDeNacimiento = fechaDeNacimiento; // setear fecha DE NACIMIENTO
		this.cantHijos = cantHijos;
		this.sueldo = Sueldo.calculaSueldo(cantHijos);
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

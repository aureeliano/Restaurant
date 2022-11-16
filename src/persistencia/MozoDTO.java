package persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import modelo.Enumerados;
import modelo.MesaAtendida;

public class MozoDTO implements Serializable
{
	private Enumerados.estadoMozo estado;
	private String NyA;
	private Date fechaDeNacimiento;
	private int cantHijos;
	private ArrayList<MesaAtendida> mesasAtendidas = new ArrayList<MesaAtendida>();
	private double sueldo;
	
	
	public MozoDTO() {}


	public Enumerados.estadoMozo getEstado() {
		return estado;
	}


	public void setEstado(Enumerados.estadoMozo estado) {
		this.estado = estado;
	}


	public String getNyA() {
		return NyA;
	}


	public void setNyA(String nyA) {
		NyA = nyA;
	}


	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}


	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}


	public int getCantHijos() {
		return cantHijos;
	}


	public void setCantHijos(int cantHijos) {
		this.cantHijos = cantHijos;
	}


	public ArrayList<MesaAtendida> getMesasAtendidas() {
		return mesasAtendidas;
	}


	public void setMesasAtendidas(ArrayList<MesaAtendida> mesasAtendidas) {
		this.mesasAtendidas = mesasAtendidas;
	}


	public double getSueldo() {
		return sueldo;
	}


	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	
	
	
}

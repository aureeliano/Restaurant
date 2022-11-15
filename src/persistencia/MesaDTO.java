package persistencia;

import java.io.Serializable;

import modelo.Enumerados;

public class MesaDTO implements Serializable
{
	private int nroMesa;
    private int cantSillas;
    private Enumerados.estadoMesa estado = Enumerados.estadoMesa.LIBRE;
    private MozoDTO mozo; 
	
    
    public MesaDTO() {}


	public int getNroMesa() {
		return nroMesa;
	}


	public void setNroMesa(int nroMesa) {
		this.nroMesa = nroMesa;
	}


	public int getCantSillas() {
		return cantSillas;
	}


	public void setCantSillas(int cantSillas) {
		this.cantSillas = cantSillas;
	}


	public Enumerados.estadoMesa getEstado() {
		return estado;
	}


	public void setEstado(Enumerados.estadoMesa estado) {
		this.estado = estado;
	}


	public MozoDTO getMozo() {
		return mozo;
	}


	public void setMozo(MozoDTO mozo) {
		this.mozo = mozo;
	}
    
    
}

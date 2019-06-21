package com.canplimplam.gestormultas.model;

import java.io.Serializable;
import java.util.Date;

public class Multa implements Serializable {
	private static final long serialVersionUID = 6541L;
	
	private Long codigo;
	private Date fechaHora;
	private Agente agente;
	private String motivo;
	private boolean aceptada;
	private String observaciones;
	private double importe;
	private Tipo tipo;
	
	public Multa() {
		
	}

	public Multa(Long codigo, Date fechaHora, Agente agente, String motivo, boolean aceptada, String observaciones,
			double importe, Tipo tipo) {
		this.codigo = codigo;
		this.fechaHora = fechaHora;
		this.agente = agente;
		this.motivo = motivo;
		this.aceptada = aceptada;
		this.observaciones = observaciones;
		this.importe = importe;
		this.tipo = tipo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Agente getAgente() {
		return agente;
	}

	public void setAgente(Agente agente) {
		this.agente = agente;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public boolean isAceptada() {
		return aceptada;
	}

	public void setAceptada(boolean aceptada) {
		this.aceptada = aceptada;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Multa other = (Multa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Multa [codigo=" + codigo + ", fechaHora=" + fechaHora + ", agente=" + agente + ", motivo=" + motivo
				+ ", aceptada=" + aceptada + ", observaciones=" + observaciones + ", importe=" + importe + ", tipo="
				+ tipo + "]";
	}
		
}

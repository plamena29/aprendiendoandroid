package com.canplimplam.gestormultas.services;

import java.util.List;

import com.canplimplam.gestormultas.model.Agente;
import com.canplimplam.gestormultas.model.Multa;

public interface MultaServices {

	/**
	 * 
	 * Se crea una multa con un nuevo c�digo
	 * 
	 * @param multa
	 * @return
	 */
	public Multa create(Multa multa);
	
	public Multa read(Long codigo);
	
	public void delete(Long codigo);
	
	public List<Multa> getAll();
	public List<Multa> getByAgente(Agente agente);
	
	
}

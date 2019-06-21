package com.canplimplam.gestormultas.services;

import java.util.List;

import com.canplimplam.gestormultas.model.Agente;

public interface AgenteServices {

	
	public Agente create(Agente agente);
	
	public Agente read(Long codigo);
	public void delete(Long codigo);
	
	public List<Agente> getAll();
	
}


package com.canplimplam.gestormultas.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.canplimplam.gestormultas.model.Agente;
import com.canplimplam.gestormultas.services.AgenteServices;

public class AgenteServicesImpl implements AgenteServices{

	private static final AgenteServices INSTANCE = new AgenteServicesImpl();
	private static final Map<Long,Agente> AGENTES;
	
	static {
		
		AGENTES = new TreeMap<Long,Agente>();
		
		Agente agente1 = new Agente(100L,"Pepín","Gálvez","Ridruejo");
		Agente agente2 = new Agente(101L,"Honorio","Martín","Salvador");
		Agente agente3 = new Agente(102L,"Carlota","Cifuentes","Arderiu");
		
		AGENTES.put(agente1.getCodigo(), agente1);
		AGENTES.put(agente2.getCodigo(), agente2);
		AGENTES.put(agente3.getCodigo(), agente3);
		
	}
	
	private AgenteServicesImpl() {
		
	}
	
	public static AgenteServices getInstance() {
		return INSTANCE;
	}
	
	
	@Override
	public Agente create(Agente agente) {
		
		if (agente.getCodigo() != null) {
			throw new IllegalArgumentException("El c�digo ha de ser nulo");
		}
		
		Long maxCode = ((TreeMap<Long,Agente>) AGENTES).lastKey();
		Long nextCode = ++maxCode;
		
		agente.setCodigo(nextCode);
		
		AGENTES.put(agente.getCodigo(), agente);
		
		Agente agenteCreado = AGENTES.get(nextCode);
		
		return agenteCreado;
	}

	@Override
	public Agente read(Long codigo) {
		return AGENTES.get(codigo);
	}

	@Override
	public void delete(Long codigo) {
		AGENTES.remove(codigo);
	}

	@Override
	public List<Agente> getAll() {
		return new ArrayList<Agente>(AGENTES.values());
	}

}

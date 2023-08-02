package fr.hb.ewan.plages.service;

import java.util.List;

import fr.hb.ewan.plages.business.Parasol;

public interface ParasolService {
	Parasol ajouterParasol(Parasol file);
	List<Parasol> recupererParasols();
	Parasol recupererParasol(Long id);
}

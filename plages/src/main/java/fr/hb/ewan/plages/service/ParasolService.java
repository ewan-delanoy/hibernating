package fr.hb.ewan.plages.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.hb.ewan.plages.business.Parasol;

public interface ParasolService {
	Parasol ajouterParasol(Parasol file);
	Page<Parasol> recupererParasols(Pageable pageable);
	Parasol recupererParasol(Long id);
}

package fr.hb.ewan.plages.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.hb.ewan.plages.business.Parasol;
import fr.hb.ewan.plages.dao.ParasolDao;
import fr.hb.ewan.plages.service.ParasolService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ParasolServiceImpl implements ParasolService {

	// Dépendances Spring
	private final ParasolDao parasolDao;

	@Override
	public Parasol ajouterParasol(Parasol parasol) {
		return parasolDao.save(parasol);
	}

	@Override
	public List<Parasol> recupererParasols() {
		return parasolDao.findAll();
	}

	@Override
	public Parasol recupererParasol(Long id) {
		return parasolDao.findById(id).orElse(null);
	}

}

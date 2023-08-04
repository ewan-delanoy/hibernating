package fr.hb.ewan.plages.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Parasol enregistrerParasol(Parasol parasol) {
		return parasolDao.save(parasol);
	}

	@Override
	public Page<Parasol> recupererParasols(Pageable pageable) {
		return parasolDao.findAll(pageable);
	}

	@Override
	public Parasol recupererParasol(Long id) {
		return parasolDao.findById(id).orElse(null);
	}

	@Override
	public Page<Parasol> recupererParasols(Pageable pageable, Long idFile) {
		return parasolDao.findByFileId(pageable,idFile);
	}

}

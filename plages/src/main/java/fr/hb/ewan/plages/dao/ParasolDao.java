package fr.hb.ewan.plages.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.hb.ewan.plages.business.Parasol;

public interface ParasolDao extends JpaRepository<Parasol, Long> {

	Page<Parasol> findByFileId(Pageable pageable, Long idFile);
    
	List<Parasol> findByNumEmplacementLessThan(byte i);
}

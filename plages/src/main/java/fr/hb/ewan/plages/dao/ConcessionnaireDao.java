package fr.hb.ewan.plages.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.hb.ewan.plages.business.Concessionnaire;

@Repository
public interface ConcessionnaireDao extends JpaRepository<Concessionnaire, Long> {

}

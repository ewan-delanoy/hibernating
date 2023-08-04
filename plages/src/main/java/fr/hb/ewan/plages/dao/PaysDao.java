package fr.hb.ewan.plages.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.hb.ewan.plages.business.Pays;

@Repository
public interface PaysDao extends JpaRepository<Pays, String> {

}

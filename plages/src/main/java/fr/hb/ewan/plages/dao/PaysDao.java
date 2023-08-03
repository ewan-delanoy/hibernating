package fr.hb.ewan.plages.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hb.ewan.plages.business.Pays;

public interface PaysDao extends JpaRepository<Pays, String> {

}

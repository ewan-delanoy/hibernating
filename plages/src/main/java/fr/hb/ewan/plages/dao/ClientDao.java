package fr.hb.ewan.plages.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hb.ewan.plages.business.Client;

public interface ClientDao extends JpaRepository<Client,Long> {

}

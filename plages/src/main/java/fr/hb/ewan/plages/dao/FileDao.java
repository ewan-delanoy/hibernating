package fr.hb.ewan.plages.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hb.ewan.plages.business.File;

public interface FileDao extends JpaRepository<File,Long> {

}

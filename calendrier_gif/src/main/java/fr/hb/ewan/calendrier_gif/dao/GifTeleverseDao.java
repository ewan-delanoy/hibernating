package fr.hb.ewan.calendrier_gif.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.hb.ewan.calendrier_gif.business.GifTeleverse;

@Repository
public interface GifTeleverseDao extends JpaRepository<GifTeleverse, Long> {

}

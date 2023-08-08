package fr.hb.ewan.plages.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.hb.ewan.plages.business.Utilisateur;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {

	Utilisateur findByEmailAndMotDePasse(String email, String motDePasse);

}

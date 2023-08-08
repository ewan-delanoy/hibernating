package fr.hb.ewan.plages.service.impl;

import org.springframework.stereotype.Service;

import fr.hb.ewan.plages.business.Utilisateur;
import fr.hb.ewan.plages.dao.UtilisateurDao;
import fr.hb.ewan.plages.service.UtilisateurService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

	private final UtilisateurDao utilisateurDao;
	
	@Override
	public Utilisateur recupererUtilisateur(String email, String motDePasse) {
		return utilisateurDao.findByEmailAndMotDePasse(email,motDePasse);
	}

}

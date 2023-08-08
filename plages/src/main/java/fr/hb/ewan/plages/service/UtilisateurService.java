package fr.hb.ewan.plages.service;

import fr.hb.ewan.plages.business.Utilisateur;

public interface UtilisateurService {
	 // R(ead)
    Utilisateur recupererUtilisateur(String email,String motDePasse);
}

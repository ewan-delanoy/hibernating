package fr.hb.ewan.calendrier_gif.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.hb.ewan.calendrier_gif.business.Theme;
import fr.hb.ewan.calendrier_gif.business.Utilisateur;
import fr.hb.ewan.calendrier_gif.dao.UtilisateurDao;
import fr.hb.ewan.calendrier_gif.dto.UtilisateurDto;
import fr.hb.ewan.calendrier_gif.mapper.UtilisateurMapper;
import fr.hb.ewan.calendrier_gif.service.UtilisateurService;
import fr.hb.ewan.calendrier_gif.util.NbInscrits;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

	private final UtilisateurDao utilisateurDao;
	// private final ThemeService themeService;

	@Override
	public Utilisateur enregistrerUtilisateur(Utilisateur utilisateur) {
		return utilisateurDao.save(utilisateur);
	}

	@Override
	public Utilisateur enregistrerUtilisateur(UtilisateurDto utilisateurDto) {
		Utilisateur utilisateur = UtilisateurMapper.INSTANCE.toEntity(utilisateurDto);
		return enregistrerUtilisateur(utilisateur);
	}

	@Override
	public Utilisateur ajouterUtilisateur(String nom, String prenom, String email, String motDePasse,
			Theme recupererTheme) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setMotDePasse(motDePasse);
		utilisateur.setTheme(recupererTheme);
		return utilisateurDao.save(utilisateur);
	}

	@Override
	public Utilisateur recupererUtilisateur(String email, String motDePasse) {
		return utilisateurDao.findLastByEmailAndMotDePasse(email, motDePasse);
	}

	@Override
	public List<Utilisateur> recupererUtilisateursAyantReagiAuMoinsCinqFois() {
		return utilisateurDao.findUtilisateursHavingAtLeastFiveReactions();
	}

	@Override
	public Utilisateur recupererUtilisateur(Long id) {
		return utilisateurDao.findById(id).orElse(null);
	}

	@Override
	public Utilisateur ajouterUtilisateurAleatoire() {
		return null;
	}

	@Override
	public List<NbInscrits> recupererNbInscrits() {
		return utilisateurDao.findNbInscrits();
	}

	@Override
	public List<Utilisateur> recupererUtilisateurs() {
		return utilisateurDao.findAll();
	}
}

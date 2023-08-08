package fr.hb.ewan.plages.controller;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.hb.ewan.plages.business.Utilisateur;
import fr.hb.ewan.plages.service.UtilisateurService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UtilisateurController {

	// Dépendances
    private final HttpSession httpSession;
	private final UtilisateurService utilisateurService;
	private final ParasolController parasolController;

	 @RequestMapping(value = {"connexion", "index" }, method = RequestMethod.GET)
	 public ModelAndView getConnexion() {
	        return  new ModelAndView("index");
	 }
	    

	@RequestMapping(value = "connexion", method = RequestMethod.POST)
	public ModelAndView postConnexion(@RequestParam(name = "EMAIL", required = true) String email,
			@RequestParam(name = "MOT_DE_PASSE", required = true) String motDePasse) {
		
			Utilisateur utilisateur = utilisateurService.recupererUtilisateur(email, motDePasse);
			
			if(utilisateur!=null) {
				httpSession.setAttribute("utilisateurConnecte", utilisateur);
				if(utilisateur.getClass().getSimpleName().equals("Client")) {
					return new ModelAndView("tableauDeBord");
				} else {
					// Autre implémentation possible : 
					// return new ModelAndView("redirect:/parasols");
					return parasolController.getParasols(PageRequest.of(0, 8), null);
				}
				
			} else {
				ModelAndView mav = new ModelAndView("index");
				mav.addObject("erreurConnexion","Adresse e-mail ou mot de passe incorrect");
				return mav;
			}
			
			
	}

}

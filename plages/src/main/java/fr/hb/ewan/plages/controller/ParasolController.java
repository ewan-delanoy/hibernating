package fr.hb.ewan.plages.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.hb.ewan.plages.business.Parasol;
import fr.hb.ewan.plages.service.FileService;
import fr.hb.ewan.plages.service.ParasolService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ParasolController {
	
	// Dépendances 
	private final HttpSession httpSession;
	private final ParasolService parasolService;
	private final FileService fileService;
	
	// La méthode sera invoquée lorsqu'une requête sur l'URL parasol est reçue 
	@GetMapping("parasol")
	public ModelAndView getParasol(@ModelAttribute Parasol parasol) {
		ModelAndView mav = new ModelAndView();
		// On définit la view (ici c'est des jsp) 
		mav.setViewName("parasol");
		// La ligne ci-dessous est rendue inutile par l'argument fourni à la présente méthode, getParasol
		// mav.addObject("parasol", new Parasol());
		// on ajoute dans le compartiment à petite bille la liste des files
		mav.addObject("files",fileService.recupererFiles());
		return mav;
		
	}
	
	@PostMapping("parasol")
	public ModelAndView postParasol(@Valid @ModelAttribute Parasol parasol,BindingResult result) {
		if(result.hasErrors()) {
			return getParasol(parasol);
		}
		parasolService.ajouterParasol(parasol);
		return new ModelAndView("redirect:/parasol");
	}
	
}

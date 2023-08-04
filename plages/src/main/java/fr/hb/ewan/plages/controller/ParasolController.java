package fr.hb.ewan.plages.controller;

import java.util.Iterator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView getParasol(@ModelAttribute(name = "parasol") Parasol parasol,
			@RequestParam(name = "ID_PARASOL", required = false) Long idParasol
			) {
		ModelAndView mav = new ModelAndView();
		// On définit la view (ici c'est des jsp)
		mav.setViewName("parasol");
		// La ligne ci-dessous est rendue inutile par l'argument fourni à la présente
		// méthode, getParasol
		// mav.addObject("parasol", new Parasol());
		// on ajoute dans le compartiment à petite bille la liste des files
		mav.addObject("files", fileService.recupererFiles());
		
		if (idParasol != null) {
			mav.addObject("parasol", parasolService.recupererParasol(idParasol));
		} 
		mav.addObject("files", fileService.recupererFiles());
		
		return mav;

	}

	@PostMapping("parasol")
	public ModelAndView postParasol(@Valid @ModelAttribute(name = "parasol") Parasol parasol, BindingResult result) {
		if (result.hasErrors()) {
			return getParasol(parasol,parasol.getId());
		}
		parasolService.enregistrerParasol(parasol);
		return new ModelAndView("redirect:/parasols");
	}

	/**
	 * 
	 * @param pageable
	 * @param idFile
	 * @return
	 */

	@GetMapping("parasols")
	public ModelAndView getParasols(
			@PageableDefault(size = 10, sort = { "file.numero", "numEmplacement" }) Pageable pageable,
			@RequestParam(name = "ID_FILE", required = false) Long idFile) {
		ModelAndView mav = new ModelAndView();
		// On définit la view (ici c'est des jsp)
		mav.setViewName("parasols");
		// on souhaite afficher une page de parasols à la JSP
		if (idFile == null || idFile == 0) {
			mav.addObject("pageDeParasols", parasolService.recupererParasols(pageable));
		} else {
			mav.addObject("pageDeParasols", parasolService.recupererParasols(pageable,idFile));
		}
		mav.addObject("files", fileService.recupererFiles());
		mav.addObject("idFile", idFile);
		Iterator<Order> iterator = pageable.getSort().iterator();
        StringBuilder sort = new StringBuilder();
        while (iterator.hasNext()) {
            sort.append(iterator.next().getProperty());
            if (iterator.hasNext()) {
                sort.append(",");
            }
        }
        mav.addObject("sort", sort.toString());
		return mav;

	}

}

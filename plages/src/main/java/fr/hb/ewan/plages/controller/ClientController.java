package fr.hb.ewan.plages.controller;

import java.util.Iterator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.hb.ewan.plages.business.Client;
import fr.hb.ewan.plages.service.ClientService;
import fr.hb.ewan.plages.service.LienDeParenteService;
import fr.hb.ewan.plages.service.PaysService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final PaysService paysService;
    private final LienDeParenteService lienDeParenteService;
    private final HttpSession httpSession;

//    @GetMapping("client")
    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ModelAndView getClient(@ModelAttribute Client client,
    		@RequestParam(name = "ID_CLIENT", required = false) Long idClient) {
        ModelAndView mav = new ModelAndView("client");
        // Si le @ModelAttribute n'est pas present:
        // mav.addObject("client", new Client());
        mav.addObject("pays", paysService.recupererListePays());
        mav.addObject("liensDeParente", lienDeParenteService.recupererLiensDeParente());
        if (idClient != null) {
			mav.addObject("client", clientService.recupererClient(idClient));
		} 
        return mav;
    }
    
//  @PostMapping("client")
    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public ModelAndView postClient(@Valid @ModelAttribute Client client, BindingResult result) {
        if (result.hasErrors()) {
            System.err.println("[ERROR] Client non ajouté:");
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("\t- " + error.getDefaultMessage());
            }
            return getClient(client,client.getId());
        } else {
            try {
                clientService.ajouterClient(client);
                System.out.println("Client ajouté!");
                ModelAndView mav = new ModelAndView("redirect:/clients");
                return mav;
            } catch (Exception e) {
                System.err.println(e);
                System.out.println("Client non ajouté!");
                ModelAndView mav = new ModelAndView("redirect:/client");
                return mav;
            }
        }
    }
    
    @GetMapping("clients")
	public ModelAndView getClients(
			@PageableDefault(size = 10, sort = { "dateHeureInscription" }) Pageable pageable
			) {
		ModelAndView mav = new ModelAndView();
		// On définit la view (ici c'est des jsp)
		mav.setViewName("clients");
		// on souhaite afficher une page de parasols à la JSP
		mav.addObject("pageDeClients", clientService.recupererClients(pageable));
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
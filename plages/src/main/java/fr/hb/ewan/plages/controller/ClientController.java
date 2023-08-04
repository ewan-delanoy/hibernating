package fr.hb.ewan.plages.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ModelAndView getClient(@ModelAttribute Client client) {
        ModelAndView mav = new ModelAndView("client");
        // Si le @ModelAttribute n'est pas present:
        // mav.addObject("client", new Client());
        mav.addObject("pays", paysService.recupererListePays());
        mav.addObject("liensDeParente", lienDeParenteService.recupererLiensDeParente());
        return mav;
    }
    
//  @PostMapping("parasol")
    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public ModelAndView postClient(@Valid @ModelAttribute Client client, BindingResult result) {
        if (result.hasErrors()) {
            System.err.println("[ERROR] Client non ajouté:");
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("\t- " + error.getDefaultMessage());
            }
            return getClient(client);
        } else {
            try {
                clientService.ajouterClient(client);
                System.out.println("Client ajouté!");
                ModelAndView mav = new ModelAndView("redirect:/client");
                return mav;
            } catch (Exception e) {
                System.err.println(e);
                System.out.println("Client non ajouté!");
                ModelAndView mav = new ModelAndView("redirect:/client");
                return mav;
            }
        }
    }
}
package fr.hb.ewan.plages.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.hb.ewan.plages.business.LienDeParente;

public interface LienDeParenteService {

    // C(reate)
    LienDeParente ajouterLienDeParente(LienDeParente lienDeParente);
    
    // R(ead)
    LienDeParente recupererLienDeParente(Long id);
    List<LienDeParente> recupererLiensDeParente();
    Page<LienDeParente> recupererLiensDeParente(Pageable pageable);
    
    // U(pdate)
    
    // D(elete)
}

package fr.hb.ewan.plages.service;

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

package fr.hb.ewan.plages.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.hb.ewan.plages.business.LienDeParente;
import fr.hb.ewan.plages.service.LienDeParenteService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LienDeParenteServiceImpl implements LienDeParenteService {

    private final LienDeParenteDao lienDeParenteDao;
    
    @Override
    public LienDeParente ajouterLienDeParente(LienDeParente lienDeParente) {
        return lienDeParenteDao.save(lienDeParente);
    }

    @Override
    public LienDeParente recupererLienDeParente(Long id) {
        return lienDeParenteDao.findById(id).orElse(null);
    }

    @Override
    public List<LienDeParente> recupererLiensDeParente() {
        return lienDeParenteDao.findAll();
    }

    @Override
    public Page<LienDeParente> recupererLiensDeParente(Pageable pageable) {
        return lienDeParenteDao.findAll(pageable);
    }

}
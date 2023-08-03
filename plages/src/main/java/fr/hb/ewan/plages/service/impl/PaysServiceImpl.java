package fr.hb.ewan.plages.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.hb.ewan.plages.business.Pays;
import fr.hb.ewan.plages.dao.PaysDao;
import fr.hb.ewan.plages.service.PaysService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaysServiceImpl implements PaysService {

    private final PaysDao paysDao;
    
    @Override
    public Pays ajouterPays(Pays pays) {
        return paysDao.save(pays);
    }
    
    @Override
    public Pays recupererPays(String code) {
        return paysDao.findById(code).orElse(null);
    }

    @Override
    public List<Pays> recupererListePays() {
        return paysDao.findAll();
    }

    @Override
    public Page<Pays> recupererListePays(Pageable pageable) {
        return paysDao.findAll(pageable);
    }

}
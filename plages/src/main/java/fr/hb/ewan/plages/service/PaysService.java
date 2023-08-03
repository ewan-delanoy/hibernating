package fr.hb.ewan.plages.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.hb.ewan.plages.business.Pays;

public interface PaysService {

    //C(reate)
    Pays ajouterPays(Pays pays);
    //R(ead)
    Pays recupererPays(String code);
    List<Pays> recupererListePays();
        Page<Pays> recupererListePays(Pageable pageable);
    //U(pdate)
    
    //D(elete)
}
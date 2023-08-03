package fr.hb.ewan.plages.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.hb.ewan.plages.business.Client;

public interface ClientService {
    
    // C(reate)
    Client ajouterClient(Client client);
    
    // R(ead)
    Client recupererClient(Long id);
    List<Client> recupererClients();
    Page<Client> recupererClients(Pageable pageable);
    
    // U(pdate)
    
    // D(elete)

}
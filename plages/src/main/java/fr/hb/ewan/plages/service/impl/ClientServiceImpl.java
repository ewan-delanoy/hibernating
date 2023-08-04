package fr.hb.ewan.plages.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.hb.ewan.plages.business.Client;
import fr.hb.ewan.plages.dao.ClientDao;
import fr.hb.ewan.plages.service.ClientService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;
    
    @Override
    public Client ajouterClient(Client client) {
        return clientDao.save(client);
    }

    @Override
    public Client recupererClient(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Override
    public List<Client> recupererClients() {
        return clientDao.findAll();
    }

    @Override
    public Page<Client> recupererClients(Pageable pageable) {
        return clientDao.findAll(pageable);
    }

}
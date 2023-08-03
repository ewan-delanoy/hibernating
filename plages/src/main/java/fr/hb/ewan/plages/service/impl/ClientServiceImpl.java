package fr.hb.ewan.plages.service.impl;

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
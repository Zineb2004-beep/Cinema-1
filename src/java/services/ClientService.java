package services;

import dao.ClientDao;
import entities.Client;
import java.util.List;

public class ClientService implements IService<Client> {

    private final ClientDao clientDao;

    public ClientService() {
        this.clientDao = new ClientDao();
    }

    @Override
    public boolean create(Client client) {
        try {
            return clientDao.create(client);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Client client) {
        try {
            return clientDao.delete(client);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client client) {
        try {
            return clientDao.update(client);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Client findById(int id) {
        try {
            return clientDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Client> findAll() {
        try {
            return clientDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Recherche un client par son email
    public Client findByEmail(String email) {
        try {
            return clientDao.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Recherche un client par son nom
    public List<Client> findByName(String name) {
        try {
            return clientDao.findByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Connexion d'un client par email et mot de passe
    public Client clientLogin(String email, String motDePasse) {
        Client client = findByEmail(email);
        if (client != null && client.getMotDePasse().equals(motDePasse)) {
            return client;
        }
        return null; // Le client n'a pas été trouvé ou les mots de passe ne correspondent pas
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.UserDao;
import entities.Admin;
import entities.Client;
import entities.User;
import java.util.List;

public class UserService implements IService<User> {

    private final UserDao ud;

    public UserService() {
        this.ud = new UserDao();
    }

    @Override
    public boolean create(User o) {
        return ud.create(o);
    }

    @Override
    public boolean delete(User o) {
        return ud.delete(o);
    }

    @Override
    public boolean update(User o) {
        return ud.update(o);
    }

    @Override
    public User findById(int id) {
        return ud.findById(id);
    }

    @Override
    public List<User> findAll() {
        return ud.findAll();
    }

    public List<User> findByEmail(String email) {
        return ud.findByEmail(email);
    }

    // Méthode pour connecter un administrateur avec son email et mot de passe
    public Admin adminLogin(String email, String motDePasse) {
        List<User> users = ud.findByEmail(email);
        if (users != null && !users.isEmpty()) {
            User user = users.get(0);
            if (user instanceof Admin && user.getMotDePasse().equals(motDePasse)) {
                return (Admin) user;
            }
        }
        return null;
    }

    // Méthode pour connecter un client avec son email et mot de passe
    public Client clientLogin(String email, String motDePasse) {
        List<User> users = ud.findByEmail(email);
        if (users != null && !users.isEmpty()) {
            User user = users.get(0);
            if (user instanceof Client && user.getMotDePasse().equals(motDePasse)) {
                return (Client) user;
            }
        }
        return null;
    }

    public boolean isEmailTaken(String email) {
        List<User> users = ud.findByEmail(email);
        return users != null && !users.isEmpty(); // Si l'email existe déjà, retourne true
    }

    public boolean registerClient(Client client) {
        // Vous pouvez également ajouter une logique pour vérifier d'autres critères
        return ud.create(client); // Utiliser la méthode create de UserDao pour insérer le client
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Admin;
import entities.Client;
import services.UserService;

/**
 *
 * @author User
 */
public class TestConnexion {

    public static void main(String[] args) {
        UserService userService = new UserService();
        Admin admin = userService.adminLogin("atiqa@gmail.com", "atiqa123");

        if (admin != null) {
            System.out.println("Connexion admin réussie : " + admin.getNom());
        } else {
            System.out.println("Échec de la connexion admin.");
        }
        
        Client client = userService.clientLogin("zineb@gmail.com", "zineb123");
        if (client != null) {
            System.out.println("Connexion client réussie : " + client.getNom());
        } else {
            System.out.println("Échec de la connexion client.");
        }
    }
}

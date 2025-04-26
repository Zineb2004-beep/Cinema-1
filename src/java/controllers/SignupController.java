/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UserService;

/**
 *
 * @author User
 */
@WebServlet(name = "SignupController", urlPatterns = {"/SignupController"})

public class SignupController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cin = request.getParameter("cin");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        // Vérification de la validité des données (par exemple, vérifier que l'email n'est pas déjà pris)
        if (userService.isEmailTaken(email)) {
            request.setAttribute("error", "Cet email est déjà utilisé. Veuillez en choisir un autre.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Créer un nouvel objet Client
        Client client = new Client(cin, nom, prenom, email, motDePasse);

        // Appeler la méthode d'inscription dans le UserService
        boolean isRegistered = userService.registerClient(client);

        // Vérifier si l'inscription a réussi
        if (isRegistered) {
            // Redirection vers la page de connexion avec un message de succès
            request.setAttribute("message", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Affichage d'un message d'erreur en cas de problème lors de l'inscription
            request.setAttribute("error", "Erreur d'inscription. Veuillez réessayer.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirection vers la page d'inscription
        request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
    }
}

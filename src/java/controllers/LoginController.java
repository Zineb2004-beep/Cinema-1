package controllers;

import entities.Admin;
import entities.Client;
import services.UserService;
import services.ClientService;  // Import du ClientService
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private UserService userService;
    private ClientService clientService;  // Déclaration du ClientService

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();  // Initialisation de UserService pour l'admin
        clientService = new ClientService();  // Initialisation de ClientService pour le client
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération des informations de connexion (email et mot de passe)
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        // Vérification de l'admin
        Admin admin = userService.adminLogin(email, motDePasse);   // Vérification pour un admin

        // Vérification du client
        Client client = clientService.findByEmail(email);  // Utilisation de ClientService pour vérifier le client

        // Si l'admin est trouvé, on crée une session et on redirige vers la page d'accueil admin
        if (admin != null && admin.getMotDePasse().equals(motDePasse)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", admin);
            response.sendRedirect(request.getContextPath() + "/view/homeAdmin.jsp");
        } // Si le client est trouvé, on crée une session et on redirige vers la page d'accueil client
        else if (client != null && client.getMotDePasse().equals(motDePasse)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", client);
            response.sendRedirect(request.getContextPath() + "/view/homeClient.jsp");
        } // Si l'email ou le mot de passe est incorrect, on renvoie une erreur
        else {
            request.setAttribute("error", "E-mail ou mot de passe incorrect.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si la requête est de type GET, on redirige vers la page de connexion
        response.sendRedirect(request.getContextPath() + "/view/login.jsp");
    }
}

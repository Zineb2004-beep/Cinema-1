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

        // Vérification de la validité des données
        if (userService.isEmailTaken(email)) {
            request.setAttribute("error", "Cet email est déjà utilisé. Veuillez en choisir un autre.");
            request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
            return;
        }

        // Créer un nouvel objet Client
        Client client = new Client(cin, nom, prenom, email, motDePasse);

        // Enregistrement
        boolean isRegistered = userService.registerClient(client);

        if (isRegistered) {
            request.setAttribute("message", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Erreur d'inscription. Veuillez réessayer.");
            request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirection vers la page d'inscription
        request.getRequestDispatcher("/view/signup.jsp").forward(request, response);
    }
}

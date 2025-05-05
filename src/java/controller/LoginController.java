package controller;

import entities.Admin;
import entities.Client;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        Admin admin = userService.adminLogin(email, motDePasse);
        Client client = userService.clientLogin(email, motDePasse);

        HttpSession session = request.getSession();

        if (admin != null) {
            session.setAttribute("user", admin);
            session.setAttribute("role", "admin");
            response.sendRedirect("admin/adminDashboard.jsp");
        } else if (client != null) {
            session.setAttribute("user", client);
            session.setAttribute("role", "client");
            response.sendRedirect("client/dashboard.jsp");
        } else {
            request.setAttribute("erreur", "Email ou mot de passe incorrect.");
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        }
    }
}

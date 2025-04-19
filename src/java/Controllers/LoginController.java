package controllers;

import entities.Admin;
import services.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        Admin admin = userService.adminLogin(email, motDePasse);

        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", admin);
            response.sendRedirect("film.jsp");
        } else {
            request.setAttribute("error", "Accès refusé : uniquement les administrateurs peuvent se connecter.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}

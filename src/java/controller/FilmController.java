package controller;

import entities.Film;
import entities.Genre;
import services.FilmService;
import services.GenreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "FilmController", urlPatterns = {"/FilmController"})
public class FilmController extends HttpServlet {

    private FilmService fs;
    private GenreService gs;

    @Override
    public void init() throws ServletException {
        fs = new FilmService();
        gs = new GenreService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");

        if (op == null) {
            // Ajout ou modification
            String id = request.getParameter("id");
            String titre = request.getParameter("titre");
            String realisateur = request.getParameter("realisateur");
            double duree = Double.parseDouble(request.getParameter("duree"));
            int genreId = Integer.parseInt(request.getParameter("genreId"));

            Genre genre = gs.findById(genreId);
            Film f = new Film(titre, realisateur, duree, genre);

            if (id == null || id.isEmpty()) {
                fs.create(f);
            } else {
                f.setId(Integer.parseInt(id));
                fs.update(f);
            }

            response.sendRedirect("admin/films.jsp");

        } else if (op.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            fs.delete(fs.findById(id));
            response.sendRedirect("admin/films.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "FilmController";
    }
}

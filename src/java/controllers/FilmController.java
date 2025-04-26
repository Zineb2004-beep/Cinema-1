package controllers;

import entities.Film;
import entities.Genre;
import services.FilmService;
import services.GenreService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FilmController", urlPatterns = {"/FilmController"})

public class FilmController extends HttpServlet {

    private FilmService fs;
    private GenreService gs;

    @Override
    public void init() throws ServletException {
        super.init();
        fs = new FilmService();
        gs = new GenreService();
    }

    private void addFilm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String titre = request.getParameter("titre");
            String realisateur = request.getParameter("realisateur");
            double duree = Double.parseDouble(request.getParameter("duree"));
            int genreId = Integer.parseInt(request.getParameter("genreId"));
            String image = request.getParameter("image"); // Nouveau champ image

            Genre genre = gs.findById(genreId);
            Film film = new Film(titre, realisateur, duree, genre, image);
            fs.create(film);
            response.sendRedirect("films.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Erreur lors de l'ajout du film");
        }
    }

    private void updateFilm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String titre = request.getParameter("titre");
            String realisateur = request.getParameter("realisateur");
            double duree = Double.parseDouble(request.getParameter("duree"));
            int genreId = Integer.parseInt(request.getParameter("genreId"));
            String image = request.getParameter("image");

            Genre genre = gs.findById(genreId);
            Film film = fs.findById(id);
            film.setTitre(titre);
            film.setRealisateur(realisateur);
            film.setDuree(duree);
            film.setGenre(genre);
            film.setImage(image);

            fs.update(film);
            response.sendRedirect("films.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Erreur lors de la mise à jour du film");
        }
    }

    private void deleteFilm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        fs.delete(fs.findById(id));
        response.sendRedirect("films.jsp");
    }

    private void editFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Film film = fs.findById(id);
        List<Film> films = fs.findAll();
        List<Genre> genres = gs.findAll();

        request.setAttribute("film", film);
        request.setAttribute("films", films);
        request.setAttribute("genres", genres);
        request.getRequestDispatcher("films.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        request.setAttribute("genres", gs.findAll());

        if (op != null) {
            switch (op) {
                case "delete":
                    deleteFilm(request, response);
                    break;
                case "update":
                    editFilm(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp?error=Opération invalide");
            }
            if (op == null) {
                List<Film> films = fs.findAll();
                List<Genre> genres = new GenreService().findAll(); // ← ICI
                request.setAttribute("films", films);
                request.setAttribute("genres", genres); // ← ICI
                request.getRequestDispatcher("film.jsp").forward(request, response);
            }

        } else {
            List<Film> films = fs.findAll();
            request.setAttribute("films", films);
            request.getRequestDispatcher("films.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            addFilm(request, response);
        } else {
            updateFilm(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Film Controller Servlet";
    }
}

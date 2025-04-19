/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Film;
import entities.Genre;
import services.FilmService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "FilmController", urlPatterns = {"/FilmController"})
public class FilmController extends HttpServlet {

    private FilmService fs;

    @Override
    public void init() throws ServletException {
        super.init();
        fs = new FilmService(); // Initialisation du service Film
    }

    private void addFilm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String titre = request.getParameter("titre");
        String realisateur = request.getParameter("realisateur");
        double duree = Double.parseDouble(request.getParameter("duree"));
        String genreId = request.getParameter("genreId");

        // Trouver le genre en fonction de l'ID du genre
        Genre genre = fs.findGenreById(Integer.parseInt(genreId)); // méthode à ajouter dans le service

        try {
            Film film = new Film(titre, realisateur, duree, genre);
            fs.create(film); // Ajouter le film via le service
            response.sendRedirect("films.jsp"); // Rediriger après ajout
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Erreur lors de l'ajout du film");
        }
    }

    private void updateFilm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String titre = request.getParameter("titre");
        String realisateur = request.getParameter("realisateur");
        double duree = Double.parseDouble(request.getParameter("duree"));
        String genreId = request.getParameter("genreId");

        // Trouver le genre en fonction de l'ID du genre
        Genre genre = fs.findGenreById(Integer.parseInt(genreId)); // méthode à ajouter dans le service

        try {
            Film film = new Film(titre, realisateur, duree, genre);
            film.setId(Integer.parseInt(id)); // Mettre à jour l'id
            fs.update(film); // Mise à jour du film via le service
            response.sendRedirect("films.jsp"); // Rediriger après mise à jour
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Erreur lors de la mise à jour du film");
        }
    }

    private void deleteFilm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        fs.delete(fs.findById(Integer.parseInt(id))); // Supprimer le film via le service
        response.sendRedirect("films.jsp"); // Rediriger après suppression
    }

    private void editFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Film film = fs.findById(Integer.parseInt(id)); // Trouver le film par ID
        List<Film> films = fs.findAll(); // Liste de films pour la page
        List<Genre> genres = fs.findAllGenres(); // Liste des genres pour remplir un select

        request.setAttribute("film", film);
        request.setAttribute("films", films);
        request.setAttribute("genres", genres);
        request.getRequestDispatcher("films.jsp").forward(request, response); // Afficher la page avec le formulaire
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");

        if (op == null) {
            // Si aucune opération n'est spécifiée, on ajoute ou modifie un film
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                addFilm(request, response); // Ajouter le film
            } else {
                updateFilm(request, response); // Modifier le film
            }
        } else {
            // Gérer les opérations de suppression et de modification selon "op"
            switch (op) {
                case "delete":
                    deleteFilm(request, response);
                    break;
                case "update":
                    editFilm(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp?error=Opération invalide");
                    break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");

        if (op != null) {
            int id = Integer.parseInt(request.getParameter("id"));

            if (op.equals("delete")) {
                fs.delete(fs.findById(id)); // Supprimer un film
                response.sendRedirect("films.jsp"); // Rediriger après suppression
            } 
            else if (op.equals("update")) {
                Film film = fs.findById(id);
                List<Film> films = fs.findAll();
                List<Genre> genres = fs.findAllGenres();

                request.setAttribute("film", film); // Pré-remplir le formulaire de modification
                request.setAttribute("films", films); // Afficher la liste des films
                request.setAttribute("genres", genres); // Afficher la liste des genres
                request.getRequestDispatcher("films.jsp").forward(request, response); // Reste sur la même page
            }
        } else {
            response.sendRedirect("films.jsp"); // Rediriger vers la page des films
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String titre = request.getParameter("titre");
        String realisateur = request.getParameter("realisateur");
        double duree = Double.parseDouble(request.getParameter("duree"));
        String genreId = request.getParameter("genreId");

        Genre genre = fs.findGenreById(Integer.parseInt(genreId)); // méthode à ajouter dans le service

        if (idParam == null || idParam.isEmpty()) {
            // Ajout d'un nouveau film
            Film film = new Film(titre, realisateur, duree, genre);
            fs.create(film);
        } else {
            // Mise à jour d'un film existant
            int id = Integer.parseInt(idParam);
            Film film = fs.findById(id);
            film.setTitre(titre);
            film.setRealisateur(realisateur);
            film.setDuree(duree);
            film.setGenre(genre);
            fs.update(film);
        }

        response.sendRedirect("films.jsp"); // Rediriger après ajout ou mise à jour
    }

    @Override
    public String getServletInfo() {
        return "Film Controller Servlet";
    }
}


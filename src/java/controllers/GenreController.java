/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Genre;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import services.GenreService;

/**
 *
 * @author User
 */
@WebServlet(name = "GenreController", urlPatterns = {"/GenreController"})
public class GenreController extends HttpServlet {

    private final GenreService genreService = new GenreService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");
        String idStr = request.getParameter("id");
        Genre genre = null;

        if (op != null && idStr != null) {
            int id = Integer.parseInt(idStr);
            genre = genreService.findById(id);

            if (genre != null) {
                switch (op) {
                    case "delete":
                        genreService.delete(genre);
                        break;
                    case "update":
                        request.setAttribute("genre", genre);
                        break;
                }
            }
        }

        request.setAttribute("genres", genreService.findAll());
        request.getRequestDispatcher("view/genre.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String idStr = request.getParameter("id");

        Genre genre;
        if (idStr != null && !idStr.isEmpty()) {
            genre = genreService.findById(Integer.parseInt(idStr));
            if (genre != null) {
                genre.setNom(nom);
                genre.setDescription(description);
                genreService.update(genre);
            }
        } else {
            genre = new Genre(nom, description);
            genreService.create(genre);
        }

        response.sendRedirect("GenreController");
    }
}

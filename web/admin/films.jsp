<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, entities.Film, entities.Genre, services.FilmService, services.GenreService" %>
<%
    FilmService fs = new FilmService();
    GenreService gs = new GenreService();

    List<Film> films = fs.findAll();
    List<Genre> genres = gs.findAll();

    String id = request.getParameter("id") != null ? request.getParameter("id") : "";
    String titre = request.getParameter("titre") != null ? request.getParameter("titre") : "";
    String realisateur = request.getParameter("realisateur") != null ? request.getParameter("realisateur") : "";
    String duree = request.getParameter("duree") != null ? request.getParameter("duree") : "";
    String genreId = request.getParameter("genreId") != null ? request.getParameter("genreId") : "";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gestion des Films</title>
        <style>
            header {
                background-color: #3e2723;  /* Marron foncé */
                padding: 20px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            header .logo h1 {
                color: white;
                font-size: 24px;
            }

            header nav a {
                color: white;
                text-decoration: none;
                margin-left: 20px;
                font-size: 18px;
                transition: color 0.3s;
            }

            header nav a:hover {
                color: #d1a13d;  /* Doré clair */
            }
            body {
                font-family: 'Segoe UI', sans-serif;
                background-color: #f6f1eb;
                margin: 0;
                padding: 5px;
                color: #3e2f1c;
            }

            h2 {
                color: #5a3825;
                border-bottom: 2px solid #d8c3a5;
                padding-bottom: 10px;
            }

            form {
                background-color: #fff8f0;
                padding: 20px;
                border: 1px solid #decbb7;
                border-radius: 10px;
                margin-bottom: 30px;
                max-width: 600px;
            }

            form label {
                display: block;
                margin-top: 10px;
                font-weight: bold;
            }

            form input, form select {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border-radius: 5px;
                border: 1px solid #cbbda1;
                background-color: #fff;
            }

            .btn {
                display: inline-block;
                padding: 8px 15px;
                margin-top: 15px;
                background-color: #8c5c3e;
                color: white;
                border: none;
                border-radius: 5px;
                text-decoration: none;
                cursor: pointer;
            }

            .btn:hover {
                background-color: #6d422c;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background-color: #fff;
            }

            table th, table td {
                padding: 12px;
                border: 1px solid #e0d5c0;
                text-align: left;
            }

            table th {
                background-color: #d8c3a5;
                color: #3e2f1c;
            }

            table td {
                background-color: #fefcf9;
            }

            .action-links a {
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <header>
            <div class="logo">
                <h1>Cinématy</h1>
            </div>
            <nav>
                <a href="adminDashboard.jsp">Dashboard</a>
            </nav>
        </header>

        <h2><%= id.isEmpty() ? "Ajouter un Film" : "Modifier un Film"%></h2>

        <form action="/Cinema/FilmController" method="POST">
            <input type="hidden" name="id" value="<%= id%>">

            <label>Titre :</label>
            <input type="text" name="titre" value="<%= titre%>" required>

            <label>Réalisateur :</label>
            <input type="text" name="realisateur" value="<%= realisateur%>" required>

            <label>Durée (en minutes) :</label>
            <input type="number" name="duree" step="0.1" value="<%= duree%>" required>

            <label>Genre :</label>
            <select name="genreId" required>
                <option value="">-- Sélectionnez --</option>
                <% for (Genre g : genres) {%>
                <option value="<%= g.getId()%>" <%= g.getId() == Integer.parseInt(genreId.isEmpty() ? "0" : genreId) ? "selected" : ""%>><%= g.getNom()%></option>
                <% }%>
            </select>

            <button type="submit" class="btn"><%= id.isEmpty() ? "Ajouter" : "Mettre à jour"%></button>
        </form>

        <h2>Liste des Films</h2>
        <table>
            <thead>
                <tr>
                    <th>Titre</th>
                    <th>Réalisateur</th>
                    <th>Durée</th>
                    <th>Genre</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% if (films != null && !films.isEmpty()) {
                        for (Film film : films) {%>
                <tr>
                    <td><%= film.getTitre()%></td>
                    <td><%= film.getRealisateur()%></td>
                    <td><%= film.getDuree()%> min</td>
                    <td><%= film.getGenre().getNom()%></td>
                    <td class="action-links">
                        <a href="films.jsp?id=<%= film.getId()%>&titre=<%= film.getTitre()%>&realisateur=<%= film.getRealisateur()%>&duree=<%= film.getDuree()%>&genreId=<%= film.getGenre().getId()%>" class="btn">Modifier</a>
                        <a href="../FilmController?op=delete&id=<%= film.getId()%>" class="btn" onclick="return confirm('Supprimer ce film ?')">Supprimer</a>
                    </td>
                </tr>
                <%  }
                } else { %>
                <tr><td colspan="5">Aucun film trouvé.</td></tr>
                <% }%>
            </tbody>
        </table>

    </body>
</html>

<%-- 
    Document   : film
    Created on : 19 avr. 2025, 01:27:55
    Author     : User
--%>
<%@ page import="java.util.List"%>
<%@ page import="entities.Admin" %>
<%@ page import="entities.Film" %>
<%@ page import="entities.Genre" %>
<%@ page import="services.FilmService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>

<%
    // Vérifier si l'utilisateur est connecté
    Admin admin = (Admin) session.getAttribute("user");
    boolean isAdmin = (admin != null && admin.getRole().equals("admin"));

    if (!isAdmin && admin == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Récupération des films
    FilmService filmService = new FilmService();
    List<Film> films = filmService.findAll();
    Film filmToEdit = (Film) request.getAttribute("film");
    boolean isUpdate = filmToEdit != null;
    List<Genre> genres = (List<Genre>) request.getAttribute("genres");
%>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Films</title>
        <style>
            :root {
                --background: #121212;
                --card-bg: #1e1e1e;
                --primary-color: #e50914;
                --text-color: #ffffff;
                --input-bg: #2c2c2c;
                --border-radius: 10px;
                --btn-hover: #b20710;
            }

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            body {
                background-color: var(--background);
                color: var(--text-color);
                padding: 20px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            h2 {
                color: var(--primary-color);
                margin-bottom: 20px;
            }

            .logout-button {
                background-color: var(--primary-color);
                color: white;
                padding: 10px 20px;
                text-decoration: none;
                border-radius: var(--border-radius);
                margin-bottom: 20px;
            }

            .form-container, .films-container {
                width: 100%;
                max-width: 1200px;
                margin-bottom: 40px;
            }

            .form-container form {
                background-color: var(--card-bg);
                padding: 20px;
                border-radius: var(--border-radius);
            }

            .form-container label,
            .form-container input,
            .form-container select {
                width: 100%;
                padding: 12px;
                margin: 10px 0;
                border-radius: var(--border-radius);
                background-color: var(--input-bg);
                color: white;
                border: 1px solid #444;
            }

            .form-container button {
                width: 100%;
                padding: 12px;
                background-color: var(--primary-color);
                border: none;
                border-radius: var(--border-radius);
                color: white;
                font-size: 16px;
                cursor: pointer;
            }

            .form-container button:hover {
                background-color: var(--btn-hover);
            }

            .films-container {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
                gap: 20px;
            }

            .film-card {
                background-color: var(--card-bg);
                border-radius: var(--border-radius);
                padding: 20px;
                transition: transform 0.3s;
            }

            .film-card:hover {
                transform: scale(1.05);
            }

            .film-card img {
                width: 100%;
                border-radius: 8px;
                margin-bottom: 10px;
            }

            .film-card h3 {
                font-size: 20px;
                margin-bottom: 5px;
            }

            .film-card p {
                font-size: 14px;
                color: #ccc;
            }

            .film-actions a {
                display: inline-block;
                margin-right: 10px;
                font-size: 14px;
                color: var(--primary-color);
                text-decoration: none;
            }

            .film-actions a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>

        <a class="logout-button" href="login.jsp">🔓 Déconnexion</a>

        <h2>🎬 Liste des Films</h2>

        <% if (isAdmin) {%>
        <!-- Formulaire d'ajout/modification -->
        <div class="form-container">
            <form action="<%= request.getContextPath()%>/FilmController" method="post">
                <input type="hidden" name="id" value="<%= isUpdate ? filmToEdit.getId() : ""%>"/>
                <label>Titre :</label>
                <input type="text" name="titre" required value="<%= isUpdate ? filmToEdit.getTitre() : ""%>"/>

                <label>Réalisateur :</label>
                <input type="text" name="realisateur" required value="<%= isUpdate ? filmToEdit.getRealisateur() : ""%>"/>

                <label>Durée (en minutes) :</label>
                <input type="number" name="duree" required value="<%= isUpdate ? filmToEdit.getDuree() : ""%>"/>

                <label>Genre :</label>
                <select name="genre" required>
                    <% if (genres != null) {
                        for (Genre genre : genres) {%>
                    <option value="<%= genre.getId()%>" <%= isUpdate && filmToEdit.getGenre() != null && filmToEdit.getGenre().getId() == genre.getId() ? "selected" : ""%>><%= genre.getNom()%></option>
                    <% }
                } else { %>
                    <option disabled selected>Aucun genre disponible</option>
                    <% }%>
                </select>

                <button type="submit"><%= isUpdate ? "Modifier" : "Ajouter"%> le film</button>
            </form>
        </div>
        <% } %>

        <!-- Liste des films -->
        <div class="films-container">
            <% if (films != null && !films.isEmpty()) {
                for (Film f : films) {%>
            <div class="film-card">
                <img src="<%= request.getContextPath()%>/assets/<%= f.getImage() != null ? f.getImage() : "default.jpg"%>" alt="<%= f.getTitre()%>"/>
                <h3><%= f.getTitre()%></h3>
                <p><strong>Réalisateur:</strong> <%= f.getRealisateur()%></p>
                <p><strong>Durée:</strong> <%= f.getDuree()%> min</p>
                <p><strong>Genre:</strong> <%= f.getGenre() != null ? f.getGenre().getNom() : "Inconnu"%></p>

                <div class="film-actions">
                    <% if (isAdmin) {%>
                    <a href="<%= request.getContextPath()%>/FilmController?id=<%= f.getId()%>&op=update">Modifier</a>
                    <a href="<%= request.getContextPath()%>/FilmController?id=<%= f.getId()%>&op=delete" onclick="return confirm('Supprimer ce film ?');">Supprimer</a>
                    <% } else {%>
                    <a href="<%= request.getContextPath()%>/FilmDetailsController?id=<%= f.getId()%>">Détails</a>
                    <% } %>
                </div>
            </div>
            <% }
        } else { %>
            <p>Aucun film disponible</p>
            <% }%>
        </div>
    </body>
</html>




<%-- 
    Document   : film
    Created on : 19 avr. 2025, 01:27:55
    Author     : User
--%>

<%@page import="java.util.List"%>
<%@ page import="entities.Admin" %>
<%@ page import="entities.Film" %>
<%@ page import="entities.Genre" %>
<%@ page import="services.FilmService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>

<%
    // Récupération de l'utilisateur connecté
    Admin admin = (Admin) session.getAttribute("user");
    if (admin == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Récupération des films existants
    FilmService filmService = new FilmService(); // Assure-toi que FilmService est correctement implémenté
    java.util.List<Film> films = filmService.findAll(); // Cette méthode doit récupérer tous les films de la base de données
    Film filmToEdit = (Film) request.getAttribute("film");
    boolean isUpdate = filmToEdit != null;
    List<Genre> genres = (List<Genre>) request.getAttribute("genres"); // Assure-toi que les genres sont passés à la page
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Films</title>
    <style>
        /* Style CSS comme avant */
    </style>
</head>
<body>
    <fieldset>
        <legend>Gestion des Films</legend>
        <a class="logout-button" href="login.jsp">🔓 Déconnexion</a>

        <!-- Formulaire d'ajout / modification de film -->
        <form action="<%= request.getContextPath() %>/FilmController" method="post">
            <input type="hidden" name="id" value="<%= isUpdate ? filmToEdit.getId() : "" %>" />

            <label>Titre:</label><br>
            <input type="text" name="titre" required value="<%= isUpdate ? filmToEdit.getTitre() : "" %>"><br>

            <label>Réalisateur:</label><br>
            <input type="text" name="realisateur" required value="<%= isUpdate ? filmToEdit.getRealisateur() : "" %>"><br>

            <label>Durée (en minutes):</label><br>
            <input type="number" name="duree" required value="<%= isUpdate ? filmToEdit.getDuree() : "" %>"><br>

            <label>Genre:</label><br>
            <select name="genre" required>
                <% for (Genre genre : genres) { %>
                    <option value="<%= genre.getId() %>" <%= isUpdate && filmToEdit.getGenre() != null && filmToEdit.getGenre().getId() == genre.getId() ? "selected" : "" %>><%= genre.getNom() %></option>
                <% } %>
            </select><br><br>

            <button type="submit" class="<%= isUpdate ? "update" : "" %>">
                <%= isUpdate ? "Modifier" : "Ajouter" %> le film
            </button>
        </form>

        <!-- Tableau des films -->
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Titre</th>
                    <th>Réalisateur</th>
                    <th>Durée (min)</th>
                    <th>Genre</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% if(films != null && !films.isEmpty()) {
                    for(Film f : films) { %>
                        <tr>
                            <td><%= f.getId() %></td>
                            <td><%= f.getTitre() %></td>
                            <td><%= f.getRealisateur() %></td>
                            <td><%= f.getDuree() %></td>
                            <td><%= f.getGenre() != null ? f.getGenre().getNom() : "" %></td>
                            <td>
                                <a href="<%= request.getContextPath() %>/FilmController?id=<%= f.getId() %>&op=delete">Supprimer</a>
                                <a href="<%= request.getContextPath() %>/FilmController?id=<%= f.getId() %>&op=update">Modifier</a>
                            </td>
                        </tr>
                <%  }
                } else { %>
                    <tr>
                        <td colspan="6" class="empty-message">Aucun film trouvé</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </fieldset>
</body>
</html>

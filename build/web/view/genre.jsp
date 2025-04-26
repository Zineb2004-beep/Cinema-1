<%-- 
    Document   : genre
    Created on : 25 avr. 2025, 19:07:42
    Author     : User
--%>

<%@page import="java.util.List"%>
<%@page import="entities.Genre"%>
<%@page import="entities.Admin"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Admin admin = (Admin) session.getAttribute("user");
    if (admin == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Genre genreToEdit = (Genre) request.getAttribute("genre");
    boolean isUpdate = genreToEdit != null;

    List<Genre> genres = (List<Genre>) request.getAttribute("genres");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Genres</title>
    <style>
        body {
            background-color: #141414;
            color: #fff;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #000;
            padding: 20px 40px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        header h1 {
            color: #e50914;
            font-size: 28px;
            margin: 0;
        }

        .logout {
            background-color: #e50914;
            padding: 8px 15px;
            border: none;
            color: white;
            border-radius: 4px;
            text-decoration: none;
        }

        .logout:hover {
            background-color: #b20710;
        }

        nav {
            background-color: #141414;
            padding: 10px 40px;
            display: flex;
            gap: 25px;
        }

        nav a {
            color: #fff;
            text-decoration: none;
            font-weight: bold;
        }

        nav a:hover {
            color: #e50914;
        }

        .container {
            padding: 40px;
            max-width: 800px;
            margin: auto;
        }

        h2 {
            color: #e50914;
        }

        form {
            background-color: #1f1f1f;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 40px;
            box-shadow: 0 0 10px rgba(229, 9, 20, 0.2);
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
        }

        input[type="text"], textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 12px;
            border: none;
            border-radius: 5px;
        }

        button {
            background-color: #e50914;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #b20710;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #1f1f1f;
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #333;
            text-align: left;
        }

        th {
            background-color: #000;
        }

        a.action-link {
            color: #e50914;
            text-decoration: none;
            margin-right: 10px;
        }

        a.action-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<header>
    <h1>CINÉ-FLIX Admin</h1>
    <a class="logout" href="login.jsp">Déconnexion</a>
</header>

<nav>
    <a href="homeAdmin.jsp">🏠 Accueil</a>
    <a href="film.jsp">🎬 Films</a>
    <a href="genre.jsp">📚 Genres</a>
</nav>

<div class="container">
    <h2><%= isUpdate ? "Modifier le Genre" : "Ajouter un Genre" %></h2>
    <form action="GenreController" method="post">
        <input type="hidden" name="id" value="<%= isUpdate ? genreToEdit.getId() : "" %>">

        <label>Nom:</label>
        <input type="text" name="nom" required value="<%= isUpdate ? genreToEdit.getNom() : "" %>">

        <label>Description:</label>
        <textarea name="description" rows="4"><%= isUpdate ? genreToEdit.getDescription() : "" %></textarea>

        <button type="submit"><%= isUpdate ? "Modifier" : "Ajouter" %></button>
    </form>

    <h2>Liste des Genres</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% if (genres != null && !genres.isEmpty()) {
                for (Genre g : genres) { %>
                    <tr>
                        <td><%= g.getId() %></td>
                        <td><%= g.getNom() %></td>
                        <td><%= g.getDescription() %></td>
                        <td>
                            <a class="action-link" href="GenreController?id=<%= g.getId() %>&op=update">Modifier</a>
                            <a class="action-link" href="GenreController?id=<%= g.getId() %>&op=delete" onclick="return confirm('Supprimer ce genre ?')">Supprimer</a>
                        </td>
                    </tr>
            <%  }
            } else { %>
                <tr><td colspan="4">Aucun genre trouvé</td></tr>
            <% } %>
        </tbody>
    </table>
</div>

</body>
</html>


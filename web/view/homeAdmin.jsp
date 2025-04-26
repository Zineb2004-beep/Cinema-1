<%-- 
    Document   : homeAdmin
    Created on : 25 avr. 2025, 19:05:45
    Author     : User
--%>

<%@page import="entities.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Admin admin = (Admin) session.getAttribute("user");
    if (admin == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Admin Cinéma</title>
        <style>
            body {
                margin: 0;
                padding: 0;
                font-family: 'Arial', sans-serif;
                background-color: #141414;
                color: #ffffff;
            }

            header {
                background-color: #000000;
                padding: 20px 40px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            header h1 {
                color: #e50914;
                margin: 0;
                font-size: 28px;
            }

            nav {
                background-color: #141414;
                padding: 15px 40px;
                display: flex;
                gap: 30px;
            }

            nav a {
                color: #ffffff;
                text-decoration: none;
                font-weight: bold;
                font-size: 16px;
                transition: color 0.3s ease;
            }

            nav a:hover {
                color: #e50914;
            }

            .logout {
                background-color: #e50914;
                color: white;
                padding: 8px 15px;
                text-decoration: none;
                border-radius: 4px;
            }

            .logout:hover {
                background-color: #b20710;
            }

            .container {
                padding: 60px;
                text-align: center;
            }

            .welcome {
                font-size: 26px;
                margin-bottom: 20px;
            }

            .card {
                background-color: #1f1f1f;
                padding: 30px;
                border-radius: 10px;
                width: 50%;
                margin: 0 auto;
                box-shadow: 0 0 20px rgba(229, 9, 20, 0.2);
            }
        </style>
    </head>
    <body>

        <header>
            <h1>CINÉ-FLIX Admin</h1>
            <a class="logout" href="login.jsp">Déconnexion</a>
        </header>

        <nav>
            <a href="film.jsp">🎬 Films</a>
            <a href="genre.jsp">📚 Genres</a>
            <!-- <a href="seance.jsp">🕒 Séances</a> -->
        </nav>

        <div class="container">
            <div class="card">
                <div class="welcome">Bienvenue, <strong><%= admin.getNom()%></strong> 🎟️</div>
                <p>Gérez vos films, genres et autres éléments de la plateforme cinéma en toute simplicité.</p>
            </div>
        </div>

    </body>
</html>


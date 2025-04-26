<%-- 
    Document   : homeClient
    Created on : 26 avr. 2025, 01:26:57
    Author     : User
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");

    if (username == null || !"user".equals(role)) {
        response.sendRedirect("login.jsp"); // redirection si non autorisé
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Accueil Client - Cinéma</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">

        <style>
            body {
                margin: 0;
                font-family: 'Roboto', sans-serif;
                background-color: #141414;
                color: #fff;
            }
            header {
                background-color: #e50914;
                padding: 20px;
                text-align: center;
                font-size: 24px;
                font-weight: bold;
            }
            .container {
                padding: 30px;
                text-align: center;
            }
            .btn {
                display: inline-block;
                margin: 15px;
                padding: 15px 30px;
                background-color: #e50914;
                color: #fff;
                border-radius: 10px;
                text-decoration: none;
                font-size: 18px;
                transition: background-color 0.3s ease;
            }
            .btn:hover {
                background-color: #f6121d;
            }
            footer {
                margin-top: 50px;
                text-align: center;
                font-size: 14px;
                color: #aaa;
            }
        </style>
    </head>
    <body>

        <header>
            🎬 Bienvenue, <%= username%> !
        </header>

        <div class="container">
            <h2>Que souhaitez-vous faire ?</h2>

            <a href="film.jsp" class="btn">🎞️ Voir les films</a>
            <a href="seance.jsp" class="btn">📅 Voir les séances</a>
            <a href="mesReservations.jsp" class="btn">🎟️ Mes réservations</a>
            <a href="logout.jsp" class="btn">🚪 Se déconnecter</a>
        </div>

        <footer>
            &copy; 2025 - CinémaApp - Tous droits réservés.
        </footer>

    </body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, services.FilmService, entities.Film" %>
<%@ include file="/view/includes/header.jsp" %>
<%@ include file="/view/includes/sidebar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Liste des films</title>
        <link rel="stylesheet" href="../assets/style.css">
        <style>
            body {
                font-family: 'Segoe UI', sans-serif;
                background-color: #fdf6ef;
            }

            .main-content {
                padding: 2rem;
                flex-grow: 1;
                margin-left: 300px;
                margin-top: -700px; 
            }

            .film-grid {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
                gap: 2rem;
            }

            .film-card {
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                overflow: hidden;
                transition: transform 0.3s ease;
            }

            .film-card:hover {
                transform: translateY(-5px);
            }

            .film-card img {
                width: 100%;
                height: 300px;
                object-fit: cover;
            }

            .film-details {
                padding: 1rem;
            }

            .film-title {
                margin: 0;
                color: #7c4a21;
                font-size: 1.2rem;
                font-weight: bold;
            }

            .film-info {
                margin: 0.5rem 0;
                color: #5c4033;
                font-size: 0.95rem;
            }
        </style>
    </head>
    <body>
        <div class="dashboard-container">
            <div class="main-content">
                <h2 style="color:#5c4033;">🎬 Liste des films</h2>

                <div class="film-grid">
                    <%
                        FilmService service = new FilmService();
                        List<Film> films = service.findAll();
                        for (Film film : films) {
                    %>
                    <div class="film-card">
                        <div class="film-details">
                            <h3 class="film-title"><%= film.getTitre()%></h3>
                            <p class="film-info">🎞 Réalisateur : <%= film.getRealisateur()%></p>
                            <p class="film-info">⏱ Durée : <%= film.getDuree()%> min</p>
                            <p class="film-info">📂 Genre : <%= film.getGenre().getNom()%></p>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>

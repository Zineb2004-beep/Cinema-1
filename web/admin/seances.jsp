<%@page import="entities.Seance"%>
<%@page import="services.SeanceService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, entities.Film, entities.Genre, services.FilmService, services.GenreService" %>
<%@ page import="java.util.*, entities.Seance, entities.Film, services.FilmService, services.SeanceService" %>
<%
    SeanceService ss = new SeanceService();
    FilmService fs = new FilmService();
    List<Seance> seances = ss.findAll();
    List<Film> films = fs.findAll();
    Seance seance = (request.getAttribute("seance") != null) ? (Seance) request.getAttribute("seance") : new Seance();
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestion des Séances</title>
        <style>


            body {
                font-family: Arial, sans-serif;
                background-color: #f5f0e6;
                margin: 0;
                padding: 5px;
            }

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
            h1 {
                color: #5d3a00;
                text-align: center;
            }

            .card {
                background-color: #fff8f0;
                border: 1px solid #d9bfa9;
                border-radius: 10px;
                padding: 20px;
                margin-bottom: 30px;
                box-shadow: 0 4px 8px rgba(93, 58, 0, 0.1);
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            th, td {
                padding: 10px;
                text-align: left;
            }

            th {
                background-color: #a17c5b;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f2e8dc;
            }

            input, select {
                width: 100%;
                padding: 8px;
                margin: 6px 0;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .btn {
                background-color: #5d3a00;
                color: white;
                border: none;
                padding: 10px 16px;
                cursor: pointer;
                border-radius: 5px;
            }

            .btn:hover {
                background-color: #7a5015;
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

        <h1>Gestion des Séances</h1>

        <div class="card">
            <h2>Ajouter / Modifier une Séance</h2>
            <form action="/Cinema/SeanceController" method="POST">
                <input type="hidden" name="id" value="<%= seance.getId()%>">

                <label>Date :</label> 
                <input type="date" name="date" 
                       value="<%= seance.getDate() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(seance.getDate()) : new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())%>" 
                       required>

                <label>Heure :</label>
                <input type="time" name="heure" 
                       value="<%= seance.getHeure() != null ? new java.text.SimpleDateFormat("HH:mm").format(seance.getHeure()) : "00:00"%>" 
                       required>

                <label>Salle :</label>
                <input type="text" name="salle" value="<%= seance.getSalle() != null ? seance.getSalle() : ""%>" required>

                <label>Places disponibles :</label>
                <input type="number" name="places" 
                       value="<%= seance.getPlaces() > 0 ? seance.getPlaces() : "0"%>" required>

                <label>Film :</label>
                <select name="filmId" required>
                    <option value="">-- Sélectionnez --</option>
                    <% for (Film f : films) {%>
                    <option value="<%= f.getId()%>" <%= seance.getFilm() != null && seance.getFilm().getId() == f.getId() ? "selected" : ""%>>
                        <%= f.getTitre()%>
                    </option>
                    <% }%>
                </select>

                <button class="btn" type="submit"><%= seance.getId() != 0 ? "Mettre à jour" : "Ajouter"%></button>
            </form>
        </div>

        <div class="card">
            <h2>Liste des Séances</h2>
            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Heure</th>
                        <th>Salle</th>
                        <th>Places</th>
                        <th>Film</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Seance s : seances) {%>
                    <tr>
                        <td><%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(s.getDate())%></td>
                        <td><%= s.getHeure().toString().substring(0, 5)%></td>
                        <td><%= s.getSalle()%></td>
                        <td><%= s.getPlaces()%></td>
                        <td><%= s.getFilm().getTitre()%></td>
                        <td>
                            <a href="/Cinema/SeanceController?op=delete&id=<%= s.getId()%>" class="btn" onclick="return confirm('Supprimer cette séance ?')">Supprimer</a>
                            <a href="/Cinema/SeanceController?op=edit&id=<%= s.getId()%>" class="btn">Modifier</a>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>

    </body>
</html>

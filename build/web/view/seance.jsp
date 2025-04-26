<%-- 
    Document   : seance
    Created on : 26 avr. 2025, 01:27:51
    Author     : User
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entities.Seance, java.util.List"%>
<%
    String role = (String) session.getAttribute("role");
    List<Seance> seances = (List<Seance>) request.getAttribute("seances");
    Seance seance = (Seance) request.getAttribute("seance");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Séances - Cinéma</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <style>
            body {
                margin: 0;
                font-family: 'Roboto', sans-serif;
                background-color: #141414;
                color: #fff;
            }
            h1, h2 {
                text-align: cesnter;
                color: #e50914;
            }
            .form-container {
                max-width: 500px;
                margin: 20px auto;
                background-color: #222;
                padding: 20px;
                border-radius: 10px;
            }
            label {
                display: block;
                margin-bottom: 10px;
            }
            input[type="text"], input[type="date"], input[type="time"] {
                width: 100%;
                padding: 10px;
                margin-top: 4px;
                border: none;
                border-radius: 5px;
            }
            button {
                margin-top: 15px;
                width: 100%;
                background-color: #e50914;
                color: #fff;
                border: none;
                padding: 10px;
                border-radius: 5px;
                cursor: pointer;
                font-weight: bold;
            }
            .grid {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
                gap: 20px;
                padding: 20px;
            }
            .card {
                background-color: #222;
                border-radius: 10px;
                padding: 15px;
                text-align: center;
                box-shadow: 0 0 10px #000;
            }
            .card h3 {
                color: #e50914;
            }
            .card .btn {
                display: inline-block;
                margin-top: 10px;
                padding: 8px 12px;
                background-color: #e50914;
                color: #fff;
                border-radius: 5px;
                text-decoration: none;
            }
            .card .btn:hover {
                background-color: #f6121d;
            }
            .actions {
                display: flex;
                justify-content: space-around;
                gap: 10px;
            }
        </style>
    </head>
    <body>

        <h1>🎬 Séances</h1>

        <% if ("admin".equals(role)) {%>
        <div class="form-container">
            <h2><%= (seance != null ? "Modifier" : "Ajouter")%> une séance</h2>
            <form action="SeanceController" method="post">
                <% if (seance != null) {%>
                <input type="hidden" name="id" value="<%= seance.getId()%>">
                <% }%>
                <label>Date :
                    <input type="date" name="date" value="<%= seance != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(seance.getDate()) : ""%>">
                </label>
                <label>Heure :
                    <input type="time" name="heure" value="<%= seance != null ? seance.getHeure().toString().substring(0, 5) : ""%>">
                </label>
                <label>Salle :
                    <input type="text" name="salle" value="<%= seance != null ? seance.getSalle() : ""%>">
                </label>
                <label>Places :
                    <input type="text" name="place" value="<%= seance != null ? seance.getPlace() : ""%>">
                </label>
                <button type="submit"><%= (seance != null ? "Mettre à jour" : "Ajouter")%></button>
            </form>
        </div>
        <% } %>

        <h2>📅 Liste des séances</h2>
        <div class="grid">
            <% if (seances != null) {
            for (Seance s : seances) {%>
            <div class="card">
                <h3><%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(s.getDate())%></h3>
                <p><strong>Heure :</strong> <%= s.getHeure().toString().substring(0, 5)%></p>
                <p><strong>Salle :</strong> <%= s.getSalle()%></p>
                <p><strong>Places :</strong> <%= s.getPlace()%></p>

                <% if ("admin".equals(role)) {%>
                <div class="actions">
                    <a class="btn" href="SeanceController?id=<%= s.getId()%>&op=update">✏️ Modifier</a>
                    <a class="btn" href="SeanceController?id=<%= s.getId()%>&op=delete" onclick="return confirm('Supprimer cette séance ?')">🗑️ Supprimer</a>
                </div>
                <% } else if ("user".equals(role)) {%>
                <a class="btn" href="ReservationController?seanceId=<%= s.getId()%>">🎟️ Réserver</a>
                <% } %>
            </div>
            <% }
    } else { %>
            <p style="text-align:center;">Aucune séance disponible.</p>
            <% }%>
        </div>

    </body>
</html>



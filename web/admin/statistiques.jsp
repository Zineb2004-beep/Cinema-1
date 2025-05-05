<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/includes/header.jsp" %>
<%@ include file="/admin/includes/sidebar.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="mapper.ReservationGenreStat" %>

<%
    // Récupérer la liste des statistiques à partir de la requête
    List<ReservationGenreStat> stats = (List<ReservationGenreStat>) request.getAttribute("stats");
    String labels = "";
    String values = "";

    // Vérifier si des statistiques sont présentes
    if (stats == null || stats.isEmpty()) {
        out.println("<p style='color:red;'>Aucune statistique à afficher.</p>");
    } else {
        // Générer les données du graphique
        for (ReservationGenreStat stat : stats) {
            labels += "'" + stat.getGenre() + "',";
            values += stat.getTotal() + ",";
        }

        // Supprimer la dernière virgule pour les labels et les valeurs
        if (!labels.isEmpty()) {
            labels = labels.substring(0, labels.length() - 1);
            values = values.substring(0, values.length() - 1);
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Statistiques des réservations</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
     <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f5dc; /* beige */
            margin: 0;
            padding: 5px;
        }
        .container {
            background-color: #fff;
            border-radius: 10px;
            padding: 30px;
            max-width: 800px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            margin-left: 350px;
            margin-top: -700px;
        }
        h2 {
            text-align: center;
            color: #5e3b19; /* marron */
        }
        canvas {
            margin-top: 30px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Statistiques des réservations par genre</h2>
        <canvas id="statChart" height="100"></canvas>
    </div>

    <script>
        const ctx = document.getElementById('statChart').getContext('2d');
        const chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: [<%= labels %>],
                datasets: [{
                    label: 'Nombre de réservations',
                    data: [<%= values %>],
                    backgroundColor: '#8B4513' // marron
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        display: true,
                        position: 'top'
                    },
                    title: {
                        display: true,
                        text: 'Réservations par genre de film'
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            stepSize: 1
                        }
                    }
                }
            }
        });
    </script>
</body>
</html>

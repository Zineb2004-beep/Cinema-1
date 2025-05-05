<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/includes/header.jsp" %>
<%@ include file="/admin/includes/sidebar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Dashboard Admin</title>
        <style>
            /* Global Styles */
            body {
                margin: 0;
                font-family: 'Segoe UI', sans-serif;
                background-color: #f5f5dc; /* beige clair */
                color: #333;
            }


            .dashboard-container{
                display: flex;
                margin-left: 0px;
                margin-top: -380px; 
            }
            /* Main Content */
            .dashboard-container {
                margin-left: 150px;
                padding: 40px;

            }

            .main-content h2 {
                color: #002b5c;
                font-size: 28px;

            }

            /* Card Style */
            .card {
                background-color: #ffffff;
                padding: 0px;
                margin-bottom: 10px;
                border-left: 6px solid #002b5c;
                border-radius: 8px;
                box-shadow: 0 3px 8px rgba(0, 0, 0, 0.05);
                width: 800px;
            }

            .card h3 {
                margin-top: 0;
                color: #002b5c;
            }

            .card p {
                color: #555;
            }

            .card .btn {
                display: inline-block;
                margin-top: 10px;
                padding: 8px 15px;
                background-color: #002b5c;
                color: #fff;
                border-radius: 4px;
                font-weight: bold;
                transition: background-color 0.3s;
            }

            .card .btn:hover {
                background-color: #003f88;
            }

            /* Responsive */
            @media (max-width: 768px) {
                .sidebar {
                    width: 100%;
                    height: auto;
                    position: relative;
                }

                .dashboard-container {
                    margin-left: 0;
                    padding: 20px;

                }
            }

        </style>

    </head>
    <body>
        <div class="dashboard-container">
            <div class="dashboard-container">
                <div class="main-content">
                    <h2>Bienvenue dans le Dashboard Admin</h2>

                    <div class="card">
                        <h3>🎬 Gérer les films</h3>
                        <p>Ajouter, modifier ou supprimer les films.</p>
                        <a href="films.jsp" class="btn">Gérer les films</a>
                    </div>

                    <div class="card">
                        <h3>📅 Gérer les séances</h3>
                        <p>Créer ou mettre à jour les séances pour chaque film.</p>
                        <a href="seances.jsp" class="btn">Gérer les séances</a>
                    </div>

                    <div class="card">
                        <h3>📖 Gérer les réservations</h3>
                        <p>Suivre les réservations effectuées par les clients.</p>
                        <a href="adminReservations.jsp" class="btn">Voir les réservations</a>
                    </div>

                    <div class="card">
                        <h3>📊 Statistiques</h3>
                        <p>Consulter les statistiques des réservations.</p>
                        <a href="statistiques.jsp" class="btn">Voir les statistiques</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


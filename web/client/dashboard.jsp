<%@ include file="/view/includes/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/includes/sidebar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Client</title>
    <link rel="stylesheet" href="../assets/style.css">
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background-color: #fdf6ef;
            height: 100%;
        }

        .dashboard-container {
            display: flex;
            margin-left: 300px;
            margin-top: -700px; 
        }

        .main-content {
            flex-grow: 1;
            padding: 2rem;
        }

        h2 {
            color: #5c4033;
        }

        .card {
            background-color: #ffffff;
            border-radius: 10px;
            padding: 1.5rem;
            margin-bottom: 2rem;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        .card h3 {
            margin-top: 0;
            color: #7c4a21;
        }

        .btn {
            padding: 10px 20px;
            background-color: #7c4a21;
            color: white;
            border: none;
            border-radius: 8px;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #5c3a1e;
        }
    </style>
</head>
<body>
<div class="dashboard-container">

    <div class="main-content">
        <h2>Bienvenue sur votre espace client</h2>

        <div class="card">
            <h3>🎬Liste des films</h3>
            <p>Découvrez les films disponibles dans notre cinéma de luxe.</p>
            <a href="film.jsp" class="btn">Voir les films</a>
        </div>

        <div class="card">
            <h3>📅Liste des séances</h3>
            <p>Consultez les horaires et planifiez votre séance parfaite.</p>
            <a href="seances.jsp" class="btn">Voir les séances</a>
        </div>

        <div class="card">
            <h3>Mes réservations</h3>
            <p>Consultez vos réservations passées et à venir.</p>
            <a href="mesReservations.jsp" class="btn">Voir mes réservations</a>
        </div>
    </div>
</div>
</body>
</html>

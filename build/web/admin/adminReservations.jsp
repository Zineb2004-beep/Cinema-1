<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entities.ReservationCinema"%>
<%@page import="entities.Seance"%>
<%@page import="entities.Film"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gestion des Réservations - Admin</title>
        <link rel="stylesheet" href="css/styles.css"> <!-- Lien vers ton fichier CSS -->
        <style>
            /* Design général */
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                color: #333;
                margin: 0;
                padding: 0;
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
                color: #5a3825;
                border-bottom: 2px solid #d8c3a5;
                padding-bottom: 10px;
            }



            /* Tableau des réservations */
            table {
                width: 90%;
                margin: 20px auto;
                border-collapse: collapse;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            }

            table thead {
                background-color: #6d4b3d;
                color: white;
            }

            table th, table td {
                padding: 10px;
                text-align: center;
            }

            table th {
                font-size: 16px;
            }

            table td {
                font-size: 14px;
            }

            table tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            table tr:hover {
                background-color: #f1e6db;
            }

            .action-link {
                color: #c75e5e;
                text-decoration: none;
                font-weight: bold;
            }

            .action-link:hover {
                color: #ff6347;
            }

            /* Messages vides */
            .c-empty-message {
                text-align: center;
                font-size: 18px;
                color: #6d4b3d;
                padding: 20px;
            }

            /* Styles des cartes */
            .reservation-card {
                background-color: #fff;
                border-radius: 10px;
                padding: 15px;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
                margin: 10px;
                text-align: center;
            }

            .reservation-card h3 {
                margin: 10px 0;
                font-size: 20px;
                color: #6d4b3d;
            }

            .reservation-card p {
                color: #555;
            }

            .reservation-card .action-link {
                margin-top: 10px;
                display: inline-block;
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
        <h1>Liste des réservations</h1>

        <c:if test="${not empty message}">
            <div class="alert">${message}</div>
        </c:if>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Client</th>
                    <th>Film</th>
                    <th>Date Séance</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reservation" items="${reservations}">
                    <tr>
                        <td>${reservation.id}</td>
                        <td>${reservation.client.nom} ${reservation.client.prenom}</td>
                        <td>${reservation.seance.film.titre}</td>
                        <td>${reservation.seance.date}</td>
                        <td>
                            <a href="deleteReservation?id=${reservation.id}">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>

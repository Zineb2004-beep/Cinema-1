<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/view/includes/header.jsp" %>
<%@ include file="/view/includes/sidebar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Séances</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f6f8fa;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ccc;
            text-align: center;
        }
        th {
            background-color: #2c3e50;
            color: white;
        }
        form {
            margin: 0;
        }
        .btn {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 6px 12px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>

    <h2 style="text-align:center;">Séances disponibles</h2>

    <c:if test="${not empty seances}">
        <table>
            <thead>
                <tr>
                    <th>Film</th>
                    <th>Date</th>
                    <th>Heure</th>
                    <th>Salle</th>
                    <th>Places</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="seance" items="${seances}">
                    <tr>
                        <td>${seance.film.titre}</td>
                        <td>${seance.date}</td>
                        <td>${seance.heure}</td>
                        <td>${seance.salle}</td>
                        <td>${seance.places}</td>
                        <td>
                            <form action="ReservationCinemaController" method="post">
                                <input type="hidden" name="action" value="reserver"/>
                                <input type="hidden" name="seanceId" value="${seance.id}"/>
                                <input type="submit" class="btn" value="Réserver"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty seances}">
        <p style="text-align:center;">Aucune séance disponible.</p>
    </c:if>

</body>
</html>


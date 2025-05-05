<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="dao.ReservationCinemaDao"%>
<%@ page import="entities.ReservationCinema" %>
<%@ page import="entities.Client" %>
<%@ page import="java.util.List" %>
<%@ include file="includes/header.jsp" %>

<!-- Contenu principal -->
<div class="container mt-4">
    <h2 class="text-center mb-4">Mes réservations</h2>

    <% 
        // Récupérer le client à partir de la session
        Client client = (Client) session.getAttribute("client");

        if (client != null) {
            // Récupérer la liste des réservations du client
            List<ReservationCinema> reservations = (List<ReservationCinema>) request.getAttribute("reservations");

            // Si le client a des réservations
            if (reservations != null && !reservations.isEmpty()) {
    %>
        <!-- Table des réservations -->
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Film</th>
                    <th>Salle</th>
                    <th>Place</th>
                    <th>Date de réservation</th>
                </tr>
            </thead>
            <tbody>
                <% for (ReservationCinema reservation : reservations) { %>
                    <tr>
                        <td><%= reservation.getSeance().getFilm().getTitre() %></td>
                        <td><%= reservation.getSeance().getSalle() %></td>
                        <td><%= reservation.getSeance().getPlaces() %></td>
                        <td><%= reservation.getDateReservation() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% 
        } else {
    %>
        <!-- Message si aucune réservation -->
        <div class="alert alert-info" role="alert">
            Vous n'avez pas encore de réservations.
        </div>
    <% 
        }
    } else {
    %>
        <div class="alert alert-danger" role="alert">
            Client introuvable. Veuillez vous connecter.
        </div>
    <% 
    }
    %>
</div>

<!-- Ajouter les scripts de Bootstrap (optionnel pour certaines interactions comme les alertes) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zyUzV2CJw4lZ15r7J2b5L5tFQ+K6kF4Z6S3kh5d2" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js" integrity="sha384-cw8HYTpmszqqXVoYOa8OaWmVZEk1lXGGnRZ35CkFCzvJf5g2YFf82t+FjpTVofk" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0s7NczsR01b4v6KZlwB8vYslRhYMGAa4iV1VGAhF2h4jA6" crossorigin="anonymous"></script>

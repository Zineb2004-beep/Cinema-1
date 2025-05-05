<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="dao.ReservationCinemaDao"%>
<%@ page import="entities.ReservationCinema" %>
<%@ page import="entities.User" %>
<%@ page import="entities.Client" %>
<%@ page import="java.util.List" %>
<%@ include file="/view/includes/header.jsp" %>
<%@ include file="/view/includes/sidebar.jsp" %>

<%
    Client client = (Client) session.getAttribute("client");
    if (client != null) {
        ReservationCinemaDao reservationDao = new ReservationCinemaDao(); // Création de l'instance
        List<ReservationCinema> reservations = reservationDao.findByClient(client); // Appel non-statique

        if (reservations != null && !reservations.isEmpty()) {
%>
            <h3>Mes réservations :</h3>
            <ul>
                <% for (ReservationCinema reservation : reservations) { %>
                    <li>
                        Film : <%= reservation.getSeance().getFilm().getTitre() %><br/>
                        Salle : <%= reservation.getSeance().getSalle() %><br/>
                        Place : <%= reservation.getSeance().getPlaces() %>
                    </li>
                <% } %>
            </ul>
<%
        } else {
%>
            <p>Vous n'avez pas encore de réservations.</p>
<%
        }
    } else {
%>
        <p>Client introuvable. Veuillez vous connecter.</p>
<%
    }
%>

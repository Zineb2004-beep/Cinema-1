/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author User
 */

import entities.Client;
import entities.ReservationCinema;
import entities.Seance;
import services.ReservationCinemaService;
import services.ClientService;
import services.SeanceService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ReservationCinemaController", urlPatterns = {"/ReservationCinemaController"})
public class ReservationCinemaController extends HttpServlet {

    private ReservationCinemaService reservationCinemaService;
    private ClientService clientService;
    private SeanceService seanceService;

    @Override
    public void init() throws ServletException {
        super.init();
        reservationCinemaService = new ReservationCinemaService();
        clientService = new ClientService();
        seanceService = new SeanceService();
    }

    private void addReservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int clientId = Integer.parseInt(request.getParameter("clientId"));
            int seanceId = Integer.parseInt(request.getParameter("seanceId"));
            String dateStr = request.getParameter("dateReservation");
            
            // Convertir la date
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateReservation = sdf.parse(dateStr);
            
            Client client = clientService.findById(clientId);
            Seance seance = seanceService.findById(seanceId);

            ReservationCinema reservationCinema = new ReservationCinema();
            reservationCinema.setClient(client);
            reservationCinema.setSeance(seance);
            reservationCinema.setDateReservation(dateReservation);
            
            reservationCinemaService.create(reservationCinema);
            response.sendRedirect("reservations.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Erreur lors de la création de la réservation");
        }
    }

    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int clientId = Integer.parseInt(request.getParameter("clientId"));
            int seanceId = Integer.parseInt(request.getParameter("seanceId"));
            ReservationCinema reservation = reservationCinemaService.findByClientAndSeance(clientId, seanceId);
            if (reservation != null) {
                reservationCinemaService.delete(reservation);
                response.sendRedirect("reservations.jsp");
            } else {
                response.sendRedirect("error.jsp?error=Réservation non trouvée");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Erreur lors de la suppression de la réservation");
        }
    }

    private void viewReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clientId = Integer.parseInt(request.getParameter("clientId"));
        List<ReservationCinema> reservations = reservationCinemaService.findByClient(clientId);
        request.setAttribute("reservations", reservations);
        request.getRequestDispatcher("reservations.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");

        if (op != null) {
            switch (op) {
                case "add":
                    addReservation(request, response);
                    break;
                case "delete":
                    deleteReservation(request, response);
                    break;
                case "view":
                    viewReservations(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp?error=Opération invalide");
                    return;
            }
        } else {
            response.sendRedirect("error.jsp?error=Opération manquante");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addReservation(request, response);
        } else if ("delete".equals(action)) {
            deleteReservation(request, response);
        } else {
            response.sendRedirect("error.jsp?error=Action invalide");
        }
    }

    @Override
    public String getServletInfo() {
        return "Gestion des réservations au cinéma";
    }
}


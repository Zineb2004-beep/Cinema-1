package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import entities.*;
import services.*;

import java.io.IOException;
import java.util.List;
import mapper.ReservationGenreStat;

@WebServlet(name = "ReservationCinemaController", urlPatterns = {"/seances", "/reserver", "/mesReservations", "/adminReservations", "/deleteReservation"})
public class ReservationCinemaController extends HttpServlet {

    private final SeanceService seanceService = new SeanceService();
    private final ReservationCinemaService reservationService = new ReservationCinemaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/seances":
                // Afficher les séances disponibles pour le client
                List<Seance> seances = seanceService.findAll();
                request.setAttribute("seances", seances);
                request.getRequestDispatcher("/seance.jsp").forward(request, response);
                break;

            case "/mesReservations":
                // Afficher les réservations spécifiques au client
                HttpSession session = request.getSession(false);
                Client client = (Client) session.getAttribute("user");

                if (client != null) {
                    List<ReservationCinema> reservations = reservationService.findByClient(client);
                    request.setAttribute("reservations", reservations);
                    request.getRequestDispatcher("/mesReservations.jsp").forward(request, response);
                } else {
                    response.sendRedirect("login.jsp");
                }
                break;

            case "/adminReservations":
                // Afficher toutes les réservations des clients pour l'admin
                Admin admin = (Admin) request.getSession(false).getAttribute("user");

                if (admin != null) {
                    List<ReservationCinema> allReservations = reservationService.findAllReservations();
                    request.setAttribute("reservations", allReservations);
                    System.out.println("Nombre total de réservations : " + allReservations.size());
                    for (ReservationCinema r : allReservations) {
                        System.out.println("Réservation : ID=" + r.getpK() + ", Client=" + r.getClient().getNom());
                    }

                    request.getRequestDispatcher("/admin/adminReservations.jsp").forward(request, response);
                } else {
                    response.sendRedirect("login.jsp");
                }
                break;

            case "/deleteReservation":
                // Supprimer une réservation par l'admin
                int reservationId = Integer.parseInt(request.getParameter("id"));
                boolean isDeleted = reservationService.delete(reservationService.findById(reservationId));

                // Affichage d'un message de succès ou d'erreur
                if (isDeleted) {
                    request.setAttribute("message", "Réservation supprimée avec succès.");
                } else {
                    request.setAttribute("message", "Erreur lors de la suppression de la réservation.");
                }

                // Recharger la liste des réservations
                List<ReservationCinema> allReservationsAfterDelete = reservationService.findAllReservations();
                request.setAttribute("reservations", allReservationsAfterDelete);
                request.getRequestDispatcher("/admin/adminReservations.jsp").forward(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action non trouvée");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/reserver")) {
            // Créer une réservation pour le client
            int idSeance = Integer.parseInt(request.getParameter("idSeance"));
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");

            if (user != null) {
                reservationService.create(user.getId(), idSeance);
                response.sendRedirect("mesReservations");
            } else {
                response.sendRedirect("login.jsp");
            }
        }
    }
}

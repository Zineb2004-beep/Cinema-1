/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import services.ReservationCinemaService;
import mapper.ReservationGenreStat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;

@WebServlet("/statistiques")
public class StatistiquesController extends HttpServlet {

    private ReservationCinemaService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        reservationService = new ReservationCinemaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<ReservationGenreStat> stats = reservationService.getStatsByGenre();

        System.out.println("Statistiques récupérées : " + stats.size());
        for (ReservationGenreStat stat : stats) {
            System.out.println(stat.getGenre() + " - " + stat.getTotal());
        }

        if (stats == null || stats.isEmpty()) {
            request.setAttribute("message", "Aucune statistique à afficher.");
        } else {
            request.setAttribute("stats", stats);
        }

        request.getRequestDispatcher("/admin/statistiques.jsp").forward(request, response);
    }

    public void showStats(HttpServletRequest request, HttpServletResponse response) {
        List<ReservationGenreStat> stats;
        stats = reservationService.getStatsByGenre();
        request.setAttribute("stats", stats);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/statistiques.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}

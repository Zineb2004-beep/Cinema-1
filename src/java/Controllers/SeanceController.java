/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import entities.Seance;
import services.SeanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SeanceController", urlPatterns = {"/SeanceController"})
public class SeanceController extends HttpServlet {

    private SeanceService seanceService;

    @Override
    public void init() throws ServletException {
        seanceService = new SeanceService();
    }

    private void addSeance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
            Time heure = Time.valueOf(request.getParameter("heure") + ":00");
            String salle = request.getParameter("salle");
            String place = request.getParameter("place");

            Seance s = new Seance(date, heure, salle, place);
            seanceService.create(s);

            response.sendRedirect("seances.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Erreur lors de l'ajout de la séance");
        }
    }

    private void updateSeance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
            Time heure = Time.valueOf(request.getParameter("heure") + ":00");
            String salle = request.getParameter("salle");
            String place = request.getParameter("place");

            Seance s = seanceService.findById(id);
            s.setDate(date);
            s.setHeure(heure);
            s.setSalle(salle);
            s.setPlace(place);

            seanceService.update(s);

            response.sendRedirect("seances.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Erreur lors de la mise à jour de la séance");
        }
    }

    private void deleteSeance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        seanceService.delete(seanceService.findById(id));
        response.sendRedirect("seances.jsp");
    }

    private void editSeance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Seance s = seanceService.findById(id);
        List<Seance> seances = seanceService.findAll();

        request.setAttribute("seance", s);
        request.setAttribute("seances", seances);
        request.getRequestDispatcher("seances.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");

        if (op != null) {
            switch (op) {
                case "delete":
                    deleteSeance(request, response);
                    break;
                case "update":
                    editSeance(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp?error=Opération invalide");
                    break;
            }
        } else {
            List<Seance> seances = seanceService.findAll();
            request.setAttribute("seances", seances);
            request.getRequestDispatcher("seances.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            addSeance(request, response);
        } else {
            updateSeance(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Seance Controller Servlet";
    }
}


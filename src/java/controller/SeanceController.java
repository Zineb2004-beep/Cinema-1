package controller;

import entities.Film;
import entities.Seance;
import services.FilmService;
import services.SeanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SeanceController", urlPatterns = {"/SeanceController"})
public class SeanceController extends HttpServlet {

    private SeanceService ss;
    private FilmService fs;

    @Override
    public void init() throws ServletException {
        ss = new SeanceService();
        fs = new FilmService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");

        if ("delete".equals(op)) {
            int id = Integer.parseInt(request.getParameter("id"));
            ss.delete(ss.findById(id));
            response.sendRedirect("admin/seances.jsp");

        } else if ("edit".equals(op)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Seance seance = ss.findById(id);
            request.setAttribute("seance", seance);
            request.getRequestDispatcher("admin/seances.jsp").forward(request, response);

        } else if (op == null) {
            // Traitement ajout/modification (POST)
            String id = request.getParameter("id");
            String dateStr = request.getParameter("date");
            String heureStr = request.getParameter("heure");
            String salle = request.getParameter("salle");
            int places = Integer.parseInt(request.getParameter("places"));
            int filmId = Integer.parseInt(request.getParameter("filmId"));

            Film film = fs.findById(filmId);
            Date date = null;
            Time heure = null;

            try {
                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                date = sdfDate.parse(dateStr);

                SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
                Date parsedTime = sdfTime.parse(heureStr);
                heure = new Time(parsedTime.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Seance s = new Seance(date, heure, salle, places);
            s.setFilm(film);

            if (id == null || id.isEmpty() || "0".equals(id)) {
                ss.create(s);
            } else {
                s.setId(Integer.parseInt(id));
                ss.update(s);
            }

            response.sendRedirect("admin/seances.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "SeanceController";
    }
}

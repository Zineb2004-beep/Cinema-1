package test;

import dao.ClientDao;
import dao.FilmDao;
import dao.GenreDao;
import dao.ReservationCinemaDao;
import dao.SeanceDao;
import entities.Client;
import entities.Film;
import entities.Genre;
import entities.ReservationCinema;
import entities.ReservationCinemaPK;
import entities.Seance;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

public class Test {

    public static void main(String[] args) {
        ClientDao clientDao = new ClientDao();
        GenreDao genreDao = new GenreDao();
        FilmDao filmDao = new FilmDao();
        SeanceDao seanceDao = new SeanceDao();
        ReservationCinemaDao reservationDao = new ReservationCinemaDao();

        // Création d'un client
        Client client = new Client("E141516", "BAIDAS", "Zineb", "zineb@gmail.com", "zineb123");
        clientDao.create(client);

        // Création d'un genre
        Genre genre = new Genre("Action", "Des scènes explosives, des poursuites et des combats intenses qui font monter l’adrénaline.");
        genreDao.create(genre);

        // Création d'un film
        Film film = new Film("Mission: Impossible – Fallout", "Christopher McQuarrie", 147.00,genre);
        filmDao.create(film);

        // Création d'une séance
        Seance seance = new Seance();
        seance.setDate(new Date());
        seance.setHeure(new Time(System.currentTimeMillis()));
        seance.setSalle("Salle 1");
        seance.setPlace("Place A");
        seance.setFilm(film);
        seanceDao.create(seance);

        // Création de la réservation de cinéma
        Date dateReservation = new Date(System.currentTimeMillis());
        ReservationCinemaPK pk = new ReservationCinemaPK(client.getId(), seance.getId(), dateReservation);
        ReservationCinema reservationCinema = new ReservationCinema(pk, dateReservation, client, seance);
        reservationDao.create(reservationCinema);

        System.out.println("Réservation enregistrée avec succès!");

        // Requêtes pour récupérer les films, séances, et réservations
        Session session = HibernateUtil.getSessionFactory().openSession();

        String genreCible = "Action";
        String hql1 = "SELECT DISTINCT f FROM Film f WHERE f.genre.nom = :genre";
        Query query1 = session.createQuery(hql1);
        query1.setParameter("genre", genreCible);
        List<Film> films = query1.list();
        System.out.println("\nFilms d'action:");
        for (Film f : films) {
            System.out.println("- " + f.getTitre() + " (" + f.getRealisateur() + ")");
        }

        String titreCible = "Mission: Impossible – Fallout";
        String hql2 = "SELECT DISTINCT s FROM Seance s WHERE s.film.titre = :titre";
        Query query2 = session.createQuery(hql2);
        query2.setParameter("titre", titreCible);
        List<Seance> seances = query2.list();
        System.out.println("\nSéances de " + titreCible + ":");
        for (Seance s : seances) {
            System.out.println("- " + s.getDate() + " à " + s.getHeure() + ", Salle: " + s.getSalle());
        }

        String emailCible = "zineb@gmail.com";
        String hql3 = "SELECT DISTINCT r FROM ReservationCinema r WHERE r.client.email = :email";
        Query query3 = session.createQuery(hql3);
        query3.setParameter("email", emailCible);
        List<ReservationCinema> reservations = query3.list();
        System.out.println("\nRéservations de " + emailCible + " :");
        for (ReservationCinema r : reservations) {
            System.out.println("- Film: " + r.getSeance().getFilm().getTitre() + ", Place: " + r.getSeance().getPlace());
        }

        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}

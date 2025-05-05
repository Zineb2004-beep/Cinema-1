/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ClientDao;
import dao.ReservationCinemaDao;
import dao.SeanceDao;
import entities.ReservationCinema;
import entities.Client;
import entities.Seance;
import entities.ReservationCinemaPK;
import java.util.List;
import mapper.ReservationGenreStat;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author User
 */
public class ReservationCinemaService implements IService<ReservationCinema> {

    private final ReservationCinemaDao reservationCinemaDAO;

    public ReservationCinemaService() {
        this.reservationCinemaDAO = new ReservationCinemaDao();
    }

    @Override
    public boolean create(ReservationCinema reservationCinema) {
        return reservationCinemaDAO.create(reservationCinema);
    }

    public boolean create(int clientId, int seanceId) {
        ClientDao clientDao = new ClientDao();
        Client client = clientDao.findById(clientId);

        SeanceDao seanceDao = new SeanceDao();
        Seance seance = seanceDao.findById(seanceId);

        if (client != null && seance != null) {
            // Créer une nouvelle instance de ReservationCinema
            ReservationCinema reservationCinema = new ReservationCinema();
            reservationCinema.setClient(client);
            reservationCinema.setSeance(seance);

            // Appeler la méthode create() du DAO pour persister l'objet
            return reservationCinemaDAO.create(reservationCinema);
        }

        return false;
    }

    @Override
    public boolean delete(ReservationCinema reservationCinema) {
        return reservationCinemaDAO.delete(reservationCinema);
    }

    @Override
    public boolean update(ReservationCinema reservationCinema) {
        return reservationCinemaDAO.update(reservationCinema);
    }

    @Override
    public ReservationCinema findById(int id) {
        return reservationCinemaDAO.findById(id);
    }

    @Override
    public List<ReservationCinema> findAll() {
        return reservationCinemaDAO.findAll();
    }

    // Récupérer les réservations d'un client
    public List<ReservationCinema> findByClient(Client client) {
        return reservationCinemaDAO.findByClient(client);
    }

    // Récupérer les séances réservées par un client
    public List<Seance> findSeancesByClient(Client client) {
        return reservationCinemaDAO.findSeancesByClient(client);
    }

    // Trouver une réservation spécifique en utilisant la clé primaire composite
    public ReservationCinema findByCompositeKey(ReservationCinemaPK pK) {
        Session session = null;
        Transaction tx = null;
        ReservationCinema reservationCinema = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            String hql = "FROM ReservationCinema r WHERE r.pK = :pK";
            Query query = session.createQuery(hql);
            query.setParameter("pK", pK);
            reservationCinema = (ReservationCinema) query.uniqueResult(); // Récupérer la réservation

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return reservationCinema;
    }

    public List<ReservationCinema> findAllReservations() {
        Session session = null;
        Transaction tx = null;
        List<ReservationCinema> reservations = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            String hql = "SELECT r FROM ReservationCinema r "
                    + "JOIN FETCH r.client "
                    + "JOIN FETCH r.seance s "
                    + "JOIN FETCH s.film";
            Query query = session.createQuery(hql);
            reservations = query.list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return reservations;
    }

    private ReservationCinemaDao reservationCinemaDao;

    public ReservationCinema findByClientAndSeance(int clientId, int seanceId) {
        return reservationCinemaDao.findByClientAndSeance(clientId, seanceId);
    }
    
    public List<ReservationGenreStat> getStatsByGenre() {
        return reservationCinemaDAO.getStatsByGenre();
    }

}

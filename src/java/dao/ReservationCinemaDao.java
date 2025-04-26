/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Client;
import entities.ReservationCinema;
import entities.ReservationCinemaPK;
import entities.Seance;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author User
 */
public class ReservationCinemaDao  extends AbstractDao<ReservationCinema> {

    public ReservationCinemaDao() {
        super(ReservationCinema.class);
    }
    
    // Trouver les réservations d'un client
    public List<ReservationCinema> findByClient(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM ReservationCinema r WHERE r.client = :client";
        Query query = session.createQuery(hql);
        query.setParameter("client", client);
        return query.list();
    }

    // Trouver les séances réservées par un client
    public List<Seance> findSeancesByClient(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT r.seance FROM ReservationCinema r WHERE r.client = :client";
        Query query = session.createQuery(hql);
        query.setParameter("client", client);
        return query.list();
    }

    // Trouver une réservation par clé primaire composite
    public ReservationCinema findByCompositeKey(ReservationCinemaPK pK) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM ReservationCinema r WHERE r.pK = :pK";
        Query query = session.createQuery(hql);
        query.setParameter("pK", pK);
        return (ReservationCinema) query.uniqueResult();
    }

    // Récupérer toutes les réservations
    public List<ReservationCinema> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM ReservationCinema";
        Query query = session.createQuery(hql);
        return query.list();
    }
    
    public ReservationCinema findByClientAndSeance(int clientId, int seanceId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ReservationCinema reservationCinema = null;
        try {
            // Utilisation de getNamedQuery()
            reservationCinema = (ReservationCinema) session.getNamedQuery("findByClientAndSeance")
                                       .setParameter("clientId", clientId)
                                       .setParameter("seanceId", seanceId)
                                       .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return reservationCinema;
    }

}


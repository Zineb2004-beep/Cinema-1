package dao;

import entities.Seance;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class SeanceDao extends AbstractDao<Seance> {

    public SeanceDao() {
        super(Seance.class);
    }

    public List<Seance> findByDate(Date date) {
        Session session = null;
        Transaction tx = null;
        List<Seance> seances = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            seances = session.getNamedQuery("Seance.findByDate")
                    .setParameter("date", date)
                    .list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return seances;
    }

    public List<Seance> findByHeure(Time heure) {
        Session session = null;
        Transaction tx = null;
        List<Seance> seances = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            seances = session.getNamedQuery("Seance.findByHeure")
                    .setParameter("heure", heure)
                    .list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return seances;
    }

    public List<Seance> findBySalle(String salle) {
        Session session = null;
        Transaction tx = null;
        List<Seance> seances = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            seances = session.getNamedQuery("Seance.findBySalle")
                    .setParameter("salle", salle)
                    .list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return seances;
    }

    public List<Seance> findByFilm(int filmId) {
        Session session = null;
        Transaction tx = null;
        List<Seance> seances = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            seances = session.getNamedQuery("Seance.findByFilm")
                    .setParameter("filmId", filmId)
                    .list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return seances;
    }

    public List<Seance> findByFilmAndDate(int filmId, Date date) {
        Session session = null;
        Transaction tx = null;
        List<Seance> seances = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            seances = session.getNamedQuery("Seance.findByFilmAndDate")
                    .setParameter("filmId", filmId)
                    .setParameter("date", date)
                    .list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return seances;
    }
}

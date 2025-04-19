package dao;

import entities.Film;
import entities.Seance;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class FilmDao extends AbstractDao<Film> {

    public FilmDao() {
        super(Film.class);
    }

    public List<Film> findByTitre(Film f) {
        Session session = null;
        Transaction tx = null;
        List<Film> films = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            films = session.getNamedQuery("findByTitre").setParameter("titre", f.getTitre()).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return films;
    }
    
    public List<Film> findByGenre(Film f) {
        Session session = null;
        Transaction tx = null;
        List<Film> films = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            films = session.getNamedQuery("findByGenre").setParameter("genre", f.getGenre()).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return films;
    }

    public List<Film> findByRealisateur(Film f) {
        Session session = null;
        Transaction tx = null;
        List<Film> films = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            films = session.getNamedQuery("findByRealisateur").setParameter("realisateur", f.getRealisateur()).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return films;
    }

    public List<Seance> findSeances(Film f) {
        Session session = null;
        Transaction tx = null;
        List<Seance> seances = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            seances = session.getNamedQuery("findSeances").setParameter("id", f.getId()).list();
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

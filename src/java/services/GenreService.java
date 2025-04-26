/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Genre;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author User
 */
public class GenreService implements IService<Genre> {

    @Override
    public boolean create(Genre genre) {
        return executeInsideTransaction(session -> session.save(genre));
    }

    @Override
    public boolean delete(Genre genre) {
        return executeInsideTransaction(session -> session.delete(genre));
    }

    @Override
    public boolean update(Genre genre) {
        return executeInsideTransaction(session -> session.update(genre));
    }

    @Override
    public Genre findById(int id) {
        Session session = null;
        Transaction tx = null;
        Genre genre = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            genre = (Genre) session.get(Genre.class, id);
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

        return genre;
    }

    @Override
    public List<Genre> findAll() {
        Session session = null;
        Transaction tx = null;
        List<Genre> genres = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Genre");
            genres = query.list();
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

        return genres;
    }

    // Méthode utilitaire pour factoriser les transactions
    private boolean executeInsideTransaction(Action action) {
        Transaction tx = null;
        Session session = null;
        boolean success = false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            action.execute(session);
            tx.commit();
            success = true;
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

        return success;
    }

    @FunctionalInterface
    private interface Action {

        void execute(Session session);
    }
}

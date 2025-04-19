/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.FilmDao;
import entities.Film;
import entities.Genre;
import entities.Seance;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class FilmService implements IService<Film> {

    private final FilmDao fd;

    public FilmService() {
        this.fd = new FilmDao();
    }

    @Override
    public boolean create(Film f) {
        return fd.create(f);
    }

    @Override
    public boolean delete(Film f) {
        return fd.delete(f);
    }

    @Override
    public boolean update(Film f) {
        return fd.update(f);
    }

    @Override
    public Film findById(int id) {
        return fd.findById(id);
    }

    @Override
    public List<Film> findAll() {
        return fd.findAll();
    }

    public List<Film> findByTitre(Film f) {
        return fd.findByTitre(f);
    }

    public List<Film> findByRealisateur(Film f) {
        return fd.findByRealisateur(f);
    }

    public List<Film> findByGenre(Film f) {
        return fd.findByGenre(f);
    }

    public List<Seance> findSeances(Film f) {
        return fd.findSeances(f);
    }

    // Méthode pour trouver un genre par ID en utilisant une NamedQuery
    public Genre findGenreById(int genreId) {
        Session session = null;
        Transaction tx = null;
        Genre genre = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // Utilisation de createQuery() avec une requête HQL au lieu de NamedQuery
            String hql = "FROM Genre g WHERE g.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", genreId);
            genre = (Genre) query.uniqueResult(); // Récupérer un seul résultat

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // Annuler la transaction en cas d'erreur
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Fermer la session
            }
        }
        return genre;
    }

    public List<Genre> findAllGenres() {
        Session session = null;
        Transaction tx = null;
        List<Genre> genres = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // Requête HQL pour récupérer tous les genres
            String hql = "FROM Genre";
            Query query = session.createQuery(hql);
            genres = query.list(); // Récupérer la liste de tous les genres

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // Annuler la transaction en cas d'erreur
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Fermer la session
            }
        }
        return genres;
    }

}

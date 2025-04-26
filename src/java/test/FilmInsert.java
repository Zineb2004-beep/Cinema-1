/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author User
 */
import org.hibernate.Session;
import org.hibernate.Transaction;
import entities.Film;
import entities.Genre;
import util.HibernateUtil;

public class FilmInsert {

    public static void main(String[] args) {
        // Récupération de la session Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Démarrage de la transaction
            transaction = session.beginTransaction();

            // Création des genres
            Genre sciFi = new Genre("Science Fiction", "Films basés sur des mondes futuristes, la technologie ou l'espace.");
            Genre action = new Genre("Action", "Films avec des scènes rapides, des combats et des poursuites.");
            Genre drama = new Genre("Drama", "Films centrés sur les émotions et les conflits humains.");
            Genre romance = new Genre("Romance", "Films explorant les relations amoureuses et les sentiments.");
            Genre crime = new Genre("Crime", "Films tournant autour d'enquêtes policières, de mafia ou de délits.");
            Genre history = new Genre("History", "Films inspirés d'événements ou de personnages historiques réels.");

            // Sauvegarde des genres
            session.save(sciFi);
            session.save(action);
            session.save(drama);
            session.save(romance);
            session.save(crime);
            session.save(history);

            // Films à insérer avec l'URL de l'image
            Film film1 = new Film("Inception", "Christopher Nolan", 148, sciFi, "assets/film1.webp");
            Film film2 = new Film("The Dark Knight", "Christopher Nolan", 152, action, "assets/film2.webp");
            Film film3 = new Film("Interstellar", "Christopher Nolan", 169, sciFi, "assets/film3.webp");
            Film film4 = new Film("The Matrix", "Wachowski Brothers", 136, action, "assets/film4.webp");
            Film film5 = new Film("Avatar", "James Cameron", 162, sciFi, "assets/film5.webp");
            Film film6 = new Film("Titanic", "James Cameron", 195, romance, "assets/film6.webp");
            Film film7 = new Film("The Shawshank Redemption", "Frank Darabont", 142, drama, "assets/film7.webp");
            Film film8 = new Film("Forrest Gump", "Robert Zemeckis", 142, drama, "assets/film8.webp");
            Film film9 = new Film("Gladiator", "Ridley Scott", 155, action, "assets/film9.webp");
            Film film10 = new Film("The Godfather", "Francis Ford Coppola", 175, crime, "assets/film10.webp");

            // Sauvegarde des films
            session.save(film1);
            session.save(film2);
            session.save(film3);
            session.save(film4);
            session.save(film5);
            session.save(film6);
            session.save(film7);
            session.save(film8);
            session.save(film9);
            session.save(film10);

            // Commit de la transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

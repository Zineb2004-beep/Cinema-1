package dao;

import entities.Client;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ClientDao extends AbstractDao<Client> {

    public ClientDao() {
        super(Client.class);
    }

    public Client findByEmail(String email) {
        // Ouvre la session Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try  {
            Client client;
            client = (Client) session.getNamedQuery("findByEmail")
                    .setParameter("email", email)
                    .uniqueResult();
            return client;
        } catch (Exception e) {
            // Si une erreur se produit, affiche la stack trace
            e.printStackTrace();
            return null;  // ou tu peux retourner une exception personnalisée si nécessaire
        }
    }

    public List<Client> findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Client> clients = null;
        try {
            tx = session.beginTransaction();
            // Requête nommée pour rechercher des clients par nom
            clients = session.getNamedQuery("findByName")
                    .setParameter("name", "%" + name + "%") // Utilisation du LIKE pour correspondre à un nom partiel
                    .list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return clients;
    }

}

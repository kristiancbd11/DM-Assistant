package dbhandlerCRUD;

import org.hibernate.Session;
import org.hibernate.Transaction;

import clases_personaje.Personaje;

public class PersonajeCRUD {
    
    public void guardarPersonaje(Personaje personaje) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(personaje);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

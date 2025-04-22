package dbhandlerCRUD;

import org.hibernate.Session;
import org.hibernate.Transaction;

import clases_estados.Estado;

public class EstadoCRUD {

    // Recuperar un estado por su ID
    public Estado recuperarEstado(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Estado.class, id);
        }
    }
}

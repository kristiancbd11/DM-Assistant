package dbhandlerCRUD;

import org.hibernate.Session;
import org.hibernate.Transaction;
import clases_habilidades.Rasgo;
import dbhandlerCRUD.HibernateUtil;

public class RasgoCRUD {

    public Rasgo recuperarRasgo(int idHabilidad) {
        Rasgo rasgo = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            System.out.println("\\n\\nTRANSACCIÓN CREADA\n\n" + idHabilidad);
            
            rasgo = session.get(Rasgo.class, idHabilidad);  // Recupera el objeto Rasgo
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return rasgo; // Devuelve el objeto recuperado
    }
}

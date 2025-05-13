package dbhandlerCRUD;

import clases_partida.TiendaObjeto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

public class TiendaObjetoCRUD {

    // Método para guardar o actualizar un TiendaObjeto
    public void guardarOActualizarTiendaObjeto(TiendaObjeto tiendaObjeto) {
        // Iniciar una nueva sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Usamos merge para guardar o actualizar el objeto
            session.merge(tiendaObjeto);

            // Commit de la transacción
            transaction.commit();
        } catch (HibernateException e) {
            // Si ocurre un error, hacer rollback
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            session.close();
        }
    }

    // Método para eliminar un TiendaObjeto
    public void eliminarTiendaObjeto(TiendaObjeto tiendaObjeto) {
        // Iniciar una nueva sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Eliminar el objeto TiendaObjeto
            session.remove(tiendaObjeto);

            // Commit de la transacción
            transaction.commit();
        } catch (HibernateException e) {
            // Si ocurre un error, hacer rollback
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            session.close();
        }
    }
}

package dbhandlerCRUD;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import clases_personaje.InventarioPersonaje;

public class InventarioPersonajeCRUD {
	
    // Método para guardar o actualizar un TiendaObjeto
    public void guardarOActualizarPersonajeIventario(InventarioPersonaje inp) {
        // Iniciar una nueva sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Usamos merge para guardar o actualizar el objeto
            session.merge(inp);

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
    public void eliminarInventarioPersonaje(InventarioPersonaje inp) {
        // Iniciar una nueva sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Eliminar el objeto TiendaObjeto
            session.remove(inp);

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

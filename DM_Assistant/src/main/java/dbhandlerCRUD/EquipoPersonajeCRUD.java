package dbhandlerCRUD;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import clases_personaje.EquipoPersonaje;

public class EquipoPersonajeCRUD {
	
    // Método para guardar o actualizar un TiendaObjeto
    public void guardarOActualizarEquipoPersonaje(EquipoPersonaje ep) {
        // Iniciar una nueva sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Usamos merge para guardar o actualizar el objeto
            session.merge(ep);

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
    public void eliminarEquipoPersonaje(EquipoPersonaje ep) {
        // Iniciar una nueva sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Eliminar el objeto TiendaObjeto
            session.remove(ep);

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

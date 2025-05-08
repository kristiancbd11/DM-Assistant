package dbhandlerCRUD;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import clases_personaje.Personaje;

public class PersonajeCRUD {
	
	public Personaje fetchPersonaje(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Personaje.class, id);
		}
	}

	public ArrayList<Personaje> fetchAllPersonajesInMundo(int idMundo) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Personaje> lista = session.createQuery("from Personaje", Personaje.class).getResultList();
			return new ArrayList<>(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}
	
	public boolean savePersonaje(Personaje personaje) {
	    Session session = null;
	    Transaction transaction = null;
	    try {
	        // Abrir sesión y comenzar la transacción
	        session = HibernateUtil.getSessionFactory().openSession();
	        transaction = session.beginTransaction();
	        
	        // Guardar el personaje en la base de datos
	        session.merge(personaje);
	        
	        // Commit de la transacción
	        transaction.commit();
	        
	        // Si llegamos aquí, significa que la operación fue exitosa
	        return true;
	    } catch (Exception e) {
	        // En caso de error, hacer rollback y mostrar el error
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return false;  // Retornar false si hubo un error
	    } finally {
	        // Cerrar la sesión para liberar recursos
	        if (session != null) {
	            session.close();
	        }
	    }
	}

	
	public ArrayList<Personaje> fetchNpcInMundo(int idMundo) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        List<Personaje> lista = session.createQuery("""
	            SELECT p FROM Personaje p
	            WHERE p.idPersonaje IN (
	                SELECT mn.idPersonaje FROM MUNDO_NPC mn
	                WHERE mn.idMundo = :idMundo
	            )
	        """, Personaje.class)
	        .setParameter("idMundo", idMundo)
	        .getResultList();

	        return new ArrayList<>(lista);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ArrayList<>(); // Devolver lista vacía en caso de error
	    }
	}
	
	public void deletePersonaje(int id) {
	    Transaction tx = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        tx = session.beginTransaction();

	        Personaje personaje = session.get(Personaje.class, id);
	        if (personaje != null) {
	            session.remove(personaje);
	        }

	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    }
	}
	
	public boolean updatePersonaje(int id, Personaje datosActualizados) {
	    Transaction tx = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        tx = session.beginTransaction();

	        Personaje personajeExistente = session.get(Personaje.class, id);
	        if (personajeExistente != null) {
	            // Actualizar solo los campos permitidos
	            personajeExistente.setNombre(datosActualizados.getNombre());
	            personajeExistente.setExperiencia(datosActualizados.getExperiencia());

	            // Aplicar los cambios
	            session.merge(personajeExistente);
	            tx.commit();
	            return true;
	        } else {
	            System.out.println("No se encontró el personaje con ID: " + id);
	            return false;
	        }
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}


}

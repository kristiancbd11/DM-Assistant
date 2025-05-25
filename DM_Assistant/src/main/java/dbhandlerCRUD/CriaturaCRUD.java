package dbhandlerCRUD;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import clases_partida.Criatura;
import clases_personaje.Personaje;

public class CriaturaCRUD {
	
	public Criatura fetchCriatura(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Criatura.class, id);
		}
	}
	
	public ArrayList<Criatura> fetchAllCriaturasInMundo(int idMundo) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Crear la consulta HQL para obtener las criaturas filtradas por idMundo
			String hql = "FROM Criatura c JOIN c.mundos m WHERE m.idMundo = :idMundo";
			Query<Criatura> query = session.createQuery(hql, Criatura.class);
			query.setParameter("idMundo", idMundo);

			// Obtener la lista de criaturas
			List<Criatura> lista = query.getResultList();
			return new ArrayList<>(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}
	
	public void deleteCriatura(int id) {
	    Transaction tx = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        tx = session.beginTransaction();

	        Criatura criatura = session.get(Criatura.class, id);
	        if (criatura != null) {
	            session.remove(criatura);
	        }

	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    }
	}
	
	public boolean saveCriatura(Criatura criatura) {
	    Session session = null;
	    Transaction transaction = null;
	    try {
	        // Abrir sesión y comenzar la transacción
	        session = HibernateUtil.getSessionFactory().openSession();
	        transaction = session.beginTransaction();
	        
	        session.merge(criatura);
	        
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
}

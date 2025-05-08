package dbhandlerCRUD;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import clases_partida.Nacion;
import clases_partida.O_Nacion;
import clases_partida.O_Reino;
import clases_partida.Reino;
import clases_personaje.Personaje;

public class NacionCRUD {
	
	public Nacion fetchNacion(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Nacion.class, id);
		}
	}

	public ArrayList<Nacion> fetchAllNaciones() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Nacion> lista = session.createQuery("from Nacion", Nacion.class).getResultList();
			return new ArrayList<>(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}

	public ArrayList<Nacion> fetchAllNacionesInMundo(int idMundo) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Crear la consulta HQL para obtener las naciones filtradas por idMundo
			String hql = "FROM Nacion n WHERE n.mundo.idMundo = :idMundo";
			Query<Nacion> query = session.createQuery(hql, Nacion.class);
			query.setParameter("idMundo", idMundo);

			// Obtener la lista de naciones
			List<Nacion> lista = query.getResultList();
			return new ArrayList<>(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}

	public ArrayList<O_Nacion> fetchAllONaciones() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<O_Nacion> lista = session.createQuery("from O_Nacion", O_Nacion.class).getResultList();
			return new ArrayList<>(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}
	
	public ArrayList<O_Reino> fetchAllOReinos() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<O_Reino> lista = session.createQuery("from O_Reino", O_Reino.class).getResultList();
			return new ArrayList<>(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}

	public ArrayList<Reino> fetchAllReinosInNacion(int idNacion) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Crear la consulta HQL para obtener los reinos filtrados por idNacion
			String hql = "FROM Reino r WHERE r.nacion.idNacion = :idNacion";
			Query<Reino> query = session.createQuery(hql, Reino.class);
			query.setParameter("idNacion", idNacion);

			// Obtener la lista de reinos
			List<Reino> lista = query.getResultList();
			return new ArrayList<>(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}
	
	public void deleteNacion(int id) {
	    Transaction tx = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        tx = session.beginTransaction();

	        Nacion nacion = session.get(Nacion.class, id);
	        if (nacion != null) {
	            session.remove(nacion);
	        }

	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    }
	}
}

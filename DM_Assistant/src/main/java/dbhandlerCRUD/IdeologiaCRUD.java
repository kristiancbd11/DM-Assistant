package dbhandlerCRUD;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import clases_personaje.Ideologia;

public class IdeologiaCRUD {
	
	public Ideologia fetchIdeologia(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Ideologia.class, id);
		}
	}

	public ArrayList<Ideologia> fetchAllIdeologias() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Ideologia> lista = session.createQuery("from Ideologia", Ideologia.class).getResultList();
			return new ArrayList<>(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}
}

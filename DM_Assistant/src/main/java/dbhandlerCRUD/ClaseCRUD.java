package dbhandlerCRUD;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import clases_personaje.Clase;

public class ClaseCRUD {
	
	public Clase fetchClase(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Clase.class, id);
		}
	}

	public ArrayList<Clase> fetchAllClases() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Clase> lista = session.createQuery("from Clase", Clase.class).getResultList();
			return new ArrayList<>(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}
}

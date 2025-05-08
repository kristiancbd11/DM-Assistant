package dbhandlerCRUD;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import clases_personaje.Religion;

public class ReligionCRUD {
	public Religion fetchReligion(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Religion.class, id);
		}
	}

	public ArrayList<Religion> fetchAllReligiones() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Religion> lista = session.createQuery("from Religion", Religion.class).getResultList();
			return new ArrayList<>(lista); // Convertimos el resultado a un ArrayList
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}
}

package dbhandlerCRUD;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import clases_personaje.Raza;

public class RazaCRUD {
	
	public Raza fetchRaza(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Raza.class, id);
		}
	}

	public ArrayList<Raza> fetchAllRazas() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Raza> lista = session.createQuery("from Raza", Raza.class).getResultList();
			return new ArrayList<>(lista); // Convertimos el resultado a un ArrayList
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}
}

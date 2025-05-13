package dbhandlerCRUD;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import clases_objetos.Objeto;

public class ObjetoCRUD {
	
	public ArrayList<Objeto> fetchAllObjetos() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Objeto> lista = session.createQuery("from Objeto", Objeto.class).getResultList();
			return new ArrayList<>(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}
}

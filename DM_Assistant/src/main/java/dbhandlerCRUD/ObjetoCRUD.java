package dbhandlerCRUD;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import clases_objetos.Arma;
import clases_objetos.Armadura;
import clases_objetos.Consumible;
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
	
	public ArrayList<Armadura> fetchAllObjetosArmadura() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Armadura> lista = session.createQuery("from Armadura", Armadura.class).getResultList();
			return new ArrayList<>(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}
	
	public ArrayList<Arma> fetchAllObjetosArma() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Arma> lista = session.createQuery("from Arma", Arma.class).getResultList();
			return new ArrayList<>(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}
	}
	
	// TODO -> Gestionar los consumibles
	public ArrayList<Consumible> fetchAllObjetosConsumible() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Consumible> lista = session.createQuery("from Consumible", Consumible.class).getResultList();
			return new ArrayList<>(lista);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>(); // En caso de error, devolver una lista vacía
		}		
	}
}

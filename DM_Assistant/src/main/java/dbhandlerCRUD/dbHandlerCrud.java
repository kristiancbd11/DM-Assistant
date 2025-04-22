package dbhandlerCRUD;

import org.hibernate.Session;
import org.hibernate.Transaction;

import clases_personaje.Ideologia;
import clases_personaje.Personaje;
import clases_personaje.Raza;
import clases_personaje.Reino;
import clases_personaje.Religion;
import clases_roles.Clase;

public class dbHandlerCrud {
	
	//Funciones para recuperar objetos
	public Raza recuperarRaza(int id) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        return session.get(Raza.class, id);
	    } 
	}
	
	public Religion recuperarReligion(int id) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        return session.get(Religion.class, id);
	    } 
	}
	
	public Reino recuperarReino(int id) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        return session.get(Reino.class, id);
	    } 
	}
	
	public Ideologia recuperarIdeologia(int id) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        return session.get(Ideologia.class, id);
	    } 
	}
	
	public Clase recuperarClase(int id) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        return session.get(Clase.class, id);
	    } 
	}
	
	public Personaje recuperarPersonaje(int id) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        return session.get(Personaje.class, id);
	    } 
	}
	
	public void guardarPersonaje(Personaje personaje) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(personaje);
        transaction.commit();
        session.close();
    }
	
}

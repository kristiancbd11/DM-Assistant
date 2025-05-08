//package app;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import clases_estados.Estado;
//import clases_habilidades.Rasgo;
//import clases_personaje.Ideologia;
//import clases_personaje.Personaje;
//import clases_personaje.Raza;
//import clases_personaje.Reino;
//import clases_personaje.Religion;
//import clases_roles.Clase;
//import dbhandlerCRUD.dbHandlerCrud;
//
//public class MainPruebas {
//
//	public static void main(String[] args) {
//
//		//Prueba de entorno v0.2
//		
//		System.out.println("1: Crear personaje\n" + "2: Consultar personje\n" + "0: Terminar programa");
//		Scanner sc = new Scanner(System.in);
//		int condicion = sc.nextInt();
//		dbHandlerCrud dbhc = new dbHandlerCrud();
//
//		while(condicion != 0) {
//			switch (condicion) {
//			case 1:
//
//				System.out.println("Nombre del personaje:");
//				String nombre = sc.next();
//				System.out.println("Id raza del personaje:");
//				int idRaza = sc.nextInt();
//				Raza raza = dbhc.fetchRaza(idRaza);
//				System.out.println("Sexo del personaje");
//				String sexo = sc.next();
//				System.out.println("Religion del personaje");
//				int idReligion = sc.nextInt();
//				Religion religion = dbhc.fetchReligion(idReligion);
//				System.out.println("Reino del personaje");
//				int idReino = sc.nextInt();
//				Reino reino = dbhc.fetchReino(idReino);
//				System.out.println("Ideología del personaje");
//				int idIdeologia = sc.nextInt();
//				List<Ideologia> listaIdeologias = new ArrayList<Ideologia>();
//				listaIdeologias.add(dbhc.fetchIdeologia(idIdeologia));
//				System.out.println("Edad del personaje");
//				int edad = sc.nextInt();
//				System.out.println("Clase del personaje");
//				int idClase = sc.nextInt();
//				Clase clase = dbhc.fetchClase(idClase);
//
//				Personaje personaje = new Personaje(nombre, raza, sexo, religion, reino, listaIdeologias, edad, clase);
//				personaje.setAllStats();
//				
//				dbhc.savePersonaje(personaje);
//				break;
//				
//			case 2:
//				System.out.println("ID del personaje:");
//				int idPj = sc.nextInt();
//				Personaje personaje2 = dbhc.fetchPersonaje(idPj);
//				System.out.println("Nombre: " + personaje2.getNombre() + " | Clase: " + personaje2.getClase().getNombre()
//						+ " | Nivel: " + personaje2.getNivel() + "\nReino: " + personaje2.getReino().getNombre()
//						+ " | Religion: " + personaje2.getReligion().getNombre());
//				System.out.print("Ideologías: ");
//				for (Ideologia ideo : personaje2.getListaIdeo()) {
//					System.out.println(ideo.getNombre());
//
//				}
//				
//				System.out.println("\n" + personaje2.getEstadoJson());
//
//				break;
//
//			case 0:
//				System.out.println("¡Sangre por Mirhai!");
//				break;
//			}
//			System.out.println("\n1: Crear personaje\n" + "2: Consultar personje\n" + "0: Terminar programa");
//			condicion = sc.nextInt();
//		}
//
//	}
//
//}

package designerView;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ElementoAux {

	private int id;
	private String nombre;
	private String token;

	// Mapa de tokens fijo y compartido por todas las instancias
	private static final Map<Integer, String> listaTokens;
	static {
		Map<Integer, String> temp = new HashMap<>();
		temp.put(1, "/tablero/cave/huesos 1.png");
		temp.put(2, "/tablero/cave/huesos 2.png");
		temp.put(3, "/tablero/cave/huesos 3.png");
		temp.put(4, "/tablero/cave/cueva pared 1_A.png");
		temp.put(5, "/tablero/cave/cueva pared 1_B.png");
		temp.put(6, "/tablero/cave/cueva pared 1_C.png");
		temp.put(7, "/tablero/cave/cueva pared 1_D.png");
		temp.put(8, "/tablero/cave/cueva pared 2_A.png");
		temp.put(9, "/tablero/cave/cueva pared 2_B.png");
		temp.put(10, "/tablero/cave/cueva pared 2_C.png");
		temp.put(11, "/tablero/cave/cueva pared 2_D.png");
		temp.put(12, "/tablero/cave/cueva pared 3_A.png");
		temp.put(13, "/tablero/cave/cueva pared 3_B.png");
		temp.put(14, "/tablero/cave/cueva pared 3_C.png");
		temp.put(15, "/tablero/cave/cueva pared 3_D.png");
		temp.put(16, "/tablero/cave/piedra 1.png");
		temp.put(17, "/tablero/cave/piedra 2.png");
		temp.put(18, "/tablero/cave/piedra 3.png");
		temp.put(19, "/tablero/cave/piedra 4.png");
		temp.put(20, "/tablero/cave/piedra 5.png");
		temp.put(21, "/tablero/decoration/caja.png");
		temp.put(22, "/tablero/decoration/silla.png");
		temp.put(23, "/tablero/forest/arbusto 1.png");
		temp.put(24, "/tablero/forest/arbusto 2.png");
		listaTokens = Collections.unmodifiableMap(temp);
	}

	// Constructor que recibe la ruta
	public ElementoAux(String path) {
		this.token = "/" + path;

		// Extraer el nombre del archivo sin la extensión
		String fileName = new File(path).getName();
		int dotIndex = fileName.lastIndexOf('.');
		this.nombre = (dotIndex > 0) ? fileName.substring(0, dotIndex) : fileName;

		// Buscar el ID correspondiente al token
		this.id = buscarIdPorToken(this.token);
	}

	// Constructor que recibe el ID
	public ElementoAux(int id) {
		this.id = id;
		String ruta = listaTokens.get(id);
		if (ruta != null) {
			this.token = ruta;

			// Extraer el nombre del archivo sin la extensión
			String fileName = new File(ruta).getName();
			int dotIndex = fileName.lastIndexOf('.');
			this.nombre = (dotIndex > 0) ? fileName.substring(0, dotIndex) : fileName;
		} else {
			this.token = "";
			this.nombre = "Desconocido";
		}
	}

	private int buscarIdPorToken(String token) {
		for (Map.Entry<Integer, String> entry : listaTokens.entrySet()) {
			if (entry.getValue().equalsIgnoreCase(token)) {
				return entry.getKey();
			}
		}
		return 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		// También actualiza token y nombre
		String ruta = listaTokens.get(id);
		if (ruta != null) {
			this.token = ruta;
			String fileName = new File(ruta).getName();
			int dotIndex = fileName.lastIndexOf('.');
			this.nombre = (dotIndex > 0) ? fileName.substring(0, dotIndex) : fileName;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
		this.id = buscarIdPorToken(token);
	}

	public static Map<Integer, String> getListaTokens() {
		return listaTokens;
	}

	@Override
	public String toString() {
		return nombre;
	}
}

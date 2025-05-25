package designerView;

import java.util.UUID;

import clases_partida.Criatura;
import clases_personaje.Personaje;
import dbhandlerCRUD.CriaturaCRUD;
import dbhandlerCRUD.PersonajeCRUD;

public class ElementoVisualData {
	private UUID clave;
    private String nombre;
    private double ejeY;
    private double ejeX;
    private ElementoVisualType tipo;
    private String token;
	private int id;

    private double posInicialX;
    private double posInicialY;
    private boolean isDragging = false;

    public ElementoVisualData(UUID clave, Object obj, double fila, double columna, ElementoVisualType tipo) {
        this.clave = clave;
    	this.tipo = tipo;
    	if (tipo == ElementoVisualType.PERSONAJE) {
        	Personaje personaje = (Personaje) obj;
        	id = personaje.getId();
            nombre = personaje.getNombre();
        } else if (tipo == ElementoVisualType.CRIATURA) {
        	Criatura criatura = (Criatura) obj;
        	id = criatura.getId();
            nombre = criatura.getNombre();
        } else if (tipo == ElementoVisualType.AUX){
            ElementoAux aux = (ElementoAux) obj;
            id = aux.getId();
            nombre = aux.getNombre();
        } else {
        	Personaje personaje = (Personaje) obj;
        	id = personaje.getId();
            nombre = personaje.getNombre();
        }

        this.ejeY = fila;
        this.ejeX = columna;

        if (obj instanceof Personaje personaje) {
            token = "/tablero/tokens/" + personaje.getToken();
        } else if (obj instanceof Criatura) {
            token = "/tablero/tokens/268894-Giant Spider Black.png";
        } else if (obj instanceof ElementoAux aux) {
            token = aux.getToken();
        } else {
            token = "/tablero/tokens/268894-Giant Spider Black.png";
        }
    }
    
    public Object BuscarObjeto(ElementoVisualType tipo, int id) {
    	PersonajeCRUD pjCrud = new PersonajeCRUD();
    	CriaturaCRUD criaturaCrud = new CriaturaCRUD();
    	if(tipo == ElementoVisualType.PERSONAJE) {
    		return pjCrud.fetchPersonaje(id);
    	} else if(tipo == ElementoVisualType.CRIATURA) {
    		return criaturaCrud.fetchCriatura(id);
    	} else if(tipo == ElementoVisualType.AUX) {
    		ElementoAux aux = new ElementoAux(id);
    		return aux;
    	} else {
    		return pjCrud.fetchPersonaje(id);
    	}
    }
    
    public ElementoVisualData clonar() {
        // Crear copia profunda si tiene objetos complejos, aquí ejemplo simple
        ElementoVisualData copia = new ElementoVisualData(this.clave, BuscarObjeto(tipo, id), this.getEjeX(), this.getEjeY(), this.getTipo());
        copia.setNombre(this.getNombre());
        copia.setToken(this.getToken());
        // Copia otros campos si hay
        return copia;
    }

    public ElementoVisualData() {}

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public double getEjeY() {
		return ejeY;
	}

	public void setEjeY(double ejeY) {
		this.ejeY = ejeY;
	}

	public double getEjeX() {
		return ejeX;
	}

	public void setEjeX(double ejeX) {
		this.ejeX = ejeX;
	}

    public String getToken() {
        return token;
    }

    public double getPosInicialX() {
        return posInicialX;
    }

    public void setPosInicialX(double posInicialX) {
        this.posInicialX = posInicialX;
    }

    public double getPosInicialY() {
        return posInicialY;
    }

    public void setPosInicialY(double posInicialY) {
        this.posInicialY = posInicialY;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public void setDragging(boolean dragging) {
        isDragging = dragging;
    }

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ElementoVisualType getTipo() {
		return tipo;
	}

	public void setTipo(ElementoVisualType tipo) {
		this.tipo = tipo;
	}

	public UUID getClave() {
		return clave;
	}

	public void setClave(UUID clave) {
		this.clave = clave;
	}
	
	
	
}
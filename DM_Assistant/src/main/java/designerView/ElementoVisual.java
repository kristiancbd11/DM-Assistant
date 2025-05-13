package designerView;

import clases_partida.Criatura;
import clases_personaje.Personaje;

public class ElementoVisual {
    private final String nombre;
    private int fila;
    private int columna;
    private final Object obj;
    private final String token;
    private double rotateAngle = 0;

    private double posInicialX;
    private double posInicialY;
    private boolean isDragging = false;

    public ElementoVisual(Object obj, int fila, int columna) {
        if (obj instanceof Personaje personaje) {
            nombre = personaje.getNombre();
        } else if (obj instanceof Criatura criatura) {
            nombre = criatura.getNombre();
        } else {
            ElementoAux aux = (ElementoAux) obj;
            nombre = aux.getToken();
        }

        this.obj = obj;
        this.fila = fila;
        this.columna = columna;

        if (obj instanceof Personaje personaje) {
            token = "/tablero/tokens/" + personaje.getToken();
        } else if (obj instanceof Criatura) {
            token = "/tablero/tokens/268894-Giant Spider Black.png";
        } else if (obj instanceof ElementoAux aux) {
            token = aux.getPath() + "/" + aux.getToken();
        } else {
            token = "/tablero/tokens/268894-Giant Spider Black.png";
        }
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Object getObj() {
        return obj;
    }

    public String getToken() {
        return token;
    }

    public double getRotateAngle() {
        return rotateAngle;
    }

    public void setRotateAngle(double rotateAngle) {
        this.rotateAngle = rotateAngle;
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
}

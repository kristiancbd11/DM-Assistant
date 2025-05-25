package clases_personaje;

import clases_objetos.Objeto;
import clases_personaje.Personaje;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "INVENTARIO_PERSONAJE")
public class InventarioPersonaje {

    @EmbeddedId
    private InventarioPersonajeId id;

    @MapsId("idObjeto")
    @ManyToOne
    @JoinColumn(name = "idObjeto", nullable = false)
    private Objeto objeto;

    @MapsId("idPersonaje")
    @ManyToOne
    @JoinColumn(name = "idPersonaje", nullable = false)
    private Personaje personaje;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    public InventarioPersonaje() {}

    public InventarioPersonaje(Objeto objeto, Personaje personaje, int cantidad) {
        this.objeto = objeto;
        this.personaje = personaje;
        this.cantidad = cantidad;
        this.id = new InventarioPersonajeId(objeto.getIdObjeto(), personaje.getId());
    }

    public InventarioPersonajeId getId() {
        return id;
    }

    public void setId(InventarioPersonajeId id) {
        this.id = id;
    }

    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Clase interna para la clave compuesta.
     */
    @Embeddable
    public static class InventarioPersonajeId implements Serializable {

        private int idObjeto;
        private int idPersonaje;

        public InventarioPersonajeId() {}

        public InventarioPersonajeId(int idObjeto, int idPersonaje) {
            this.idObjeto = idObjeto;
            this.idPersonaje = idPersonaje;
        }

        public int getIdObjeto() {
            return idObjeto;
        }

        public void setIdObjeto(int idObjeto) {
            this.idObjeto = idObjeto;
        }

        public int getIdPersonaje() {
            return idPersonaje;
        }

        public void setIdPersonaje(int idPersonaje) {
            this.idPersonaje = idPersonaje;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof InventarioPersonajeId)) return false;
            InventarioPersonajeId that = (InventarioPersonajeId) o;
            return idObjeto == that.idObjeto && idPersonaje == that.idPersonaje;
        }

        @Override
        public int hashCode() {
            return Objects.hash(idObjeto, idPersonaje);
        }
    }
}

package clases_personaje;

import clases_objetos.Objeto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "EQUIPO_PERSONAJE")
public class EquipoPersonaje {

    @EmbeddedId
    private EquipoPersonajeId id;

    @MapsId("idObjeto")
    @ManyToOne
    @JoinColumn(name = "idObjeto", nullable = false)
    private Objeto objeto;

    @MapsId("idPersonaje")
    @ManyToOne
    @JoinColumn(name = "idPersonaje", nullable = false)
    private Personaje personaje;

    @Column(name = "ranura", nullable = false)
    private int ranura;

    public EquipoPersonaje() {}

    public EquipoPersonaje(Objeto objeto, Personaje personaje, int ranura) {
        this.objeto = objeto;
        this.personaje = personaje;
        this.ranura = ranura;
        this.id = new EquipoPersonajeId(objeto.getIdObjeto(), personaje.getId());
    }

    public EquipoPersonajeId getId() {
        return id;
    }

    public void setId(EquipoPersonajeId id) {
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

    public int getRanura() {
        return ranura;
    }

    public void setRanura(int ranura) {
        this.ranura = ranura;
    }

    /**
     * Clase interna para la clave compuesta.
     */
    @Embeddable
    public static class EquipoPersonajeId implements Serializable {

        private int idObjeto;
        private int idPersonaje;

        public EquipoPersonajeId() {}

        public EquipoPersonajeId(int idObjeto, int idPersonaje) {
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
            if (!(o instanceof EquipoPersonajeId)) return false;
            EquipoPersonajeId that = (EquipoPersonajeId) o;
            return idObjeto == that.idObjeto && idPersonaje == that.idPersonaje;
        }

        @Override
        public int hashCode() {
            return Objects.hash(idObjeto, idPersonaje);
        }
    }
}

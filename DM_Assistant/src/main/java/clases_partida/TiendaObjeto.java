package clases_partida;

import clases_objetos.Objeto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TIENDA_OBJETOS")
public class TiendaObjeto {

    @EmbeddedId
    private TiendaObjetoId id;

    @MapsId("idTienda")
    @ManyToOne
    @JoinColumn(name = "idTienda", nullable = false)
    private Tienda tienda;

    @MapsId("idObjeto")
    @ManyToOne
    @JoinColumn(name = "idObjeto", nullable = false)
    private Objeto objeto;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    public TiendaObjeto() {}

    public TiendaObjeto(Tienda tienda, Objeto objeto, int cantidad) {
        this.tienda = tienda;
        this.objeto = objeto;
        this.cantidad = cantidad;
        this.id = new TiendaObjetoId(tienda.getUbicacion().getIdUbicacion(), objeto.getIdObjeto());
    }

    public TiendaObjetoId getId() {
        return id;
    }

    public void setId(TiendaObjetoId id) {
        this.id = id;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
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
    public static class TiendaObjetoId implements Serializable {

        private int idTienda;
        private int idObjeto;

        public TiendaObjetoId() {}

        public TiendaObjetoId(int idTienda, int idObjeto) {
            this.idTienda = idTienda;
            this.idObjeto = idObjeto;
        }

        public int getIdTienda() {
            return idTienda;
        }

        public void setIdTienda(int idTienda) {
            this.idTienda = idTienda;
        }

        public int getIdObjeto() {
            return idObjeto;
        }

        public void setIdObjeto(int idObjeto) {
            this.idObjeto = idObjeto;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TiendaObjetoId)) return false;
            TiendaObjetoId that = (TiendaObjetoId) o;
            return Objects.equals(idTienda, that.idTienda) && Objects.equals(idObjeto, that.idObjeto);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idTienda, idObjeto);
        }
    }
}

package fbhandler;

import java.util.ArrayList;
import java.util.List;

import clases_habilidades.Don;
import clases_habilidades.Talento;
import clases_habilidades.Hechizo;
import clases_habilidades.Ventaja;
import clases_habilidades.Rasgo;
import clases_personaje.EquipoPersonaje;
import clases_personaje.InventarioPersonaje;
import clases_personaje.Personaje;

public class PersonajeDTO {
    
    // Datos temáticos
    private String nombre;
    private String sexo;
    private int edad;
    private String token;

    private int razaId;
    private int religionId;
    private int nacionId;
    private int ideologiaId;

    // Datos de clase
    private int claseId;
    private Integer subclaseId; // Puede ser null, usar Integer en vez de int

    // Datos de estado
    private String estadoJson;

    // Datos de progreso
    private int oro;
    private int experiencia;
    private int nivel;
    
    // Habilidades y características por ID
    private List<Integer> talentoIds;
    private List<Integer> donIds;
    private List<Integer> hechizoIds;
    private List<Integer> ventajaIds;
    private List<Integer> rasgoIds;

    // Equipación
    private List<Integer> idEquipos;
    private List<Integer> ranuras;

    // Inventario con cantidad
    private List<Integer> idObjetos;
    private List<Integer> cantidadObjetos;

    // Constructor vacío
    public PersonajeDTO() {}

    public PersonajeDTO(Personaje personaje) {
        this.nombre = personaje.getNombre();
        this.sexo = personaje.getSexo();
        this.edad = personaje.getEdad();
        this.token = personaje.getToken();
        this.razaId = personaje.getRaza().getIdRaza();
        this.religionId = personaje.getReligion().getIdReligion();
        this.nacionId = personaje.getNacion().getIdNacion();
        this.ideologiaId = personaje.getIdeologia().getIdIdeologia();
        this.claseId = personaje.getClase().getIdClase();
        // Subclase puede ser null
        this.subclaseId = personaje.getSubclase() != null ? personaje.getSubclase().getIdSubclase() : null;
        this.estadoJson = personaje.getEstadoJson();
        this.oro = personaje.getOro();
        this.experiencia = personaje.getExperiencia();
        this.nivel = personaje.getNivel();

        // Inicializar listas para evitar NullPointerException
        this.talentoIds = new ArrayList<>();
        this.donIds = new ArrayList<>();
        this.hechizoIds = new ArrayList<>();
        this.ventajaIds = new ArrayList<>();
        this.rasgoIds = new ArrayList<>();

        this.idEquipos = new ArrayList<>();
        this.ranuras = new ArrayList<>();

        this.idObjetos = new ArrayList<>();
        this.cantidadObjetos = new ArrayList<>();

        // Talentos
        if (personaje.getListaTalentos() != null) {
            for (Talento talento : personaje.getListaTalentos()) {
                this.talentoIds.add(talento.getHabilidad().getIdHabilidad());
            }
        }

        // Dones
        if (personaje.getListaDones() != null) {
            for (Don don : personaje.getListaDones()) {
                this.donIds.add(don.getHabilidad().getIdHabilidad());
            }
        }

        // Hechizos
        if (personaje.getListaHechizos() != null) {
            for (Hechizo hechizo : personaje.getListaHechizos()) {
                this.hechizoIds.add(hechizo.getHabilidad().getIdHabilidad());
            }
        }

        // Ventajas
        if (personaje.getListaVentajas() != null) {
            for (Ventaja ventaja : personaje.getListaVentajas()) {
                this.ventajaIds.add(ventaja.getHabilidad().getIdHabilidad());
            }
        }

        // Rasgos
        if (personaje.getListaRasgos() != null) {
            for (Rasgo rasgo : personaje.getListaRasgos()) {
                this.rasgoIds.add(rasgo.getHabilidad().getIdHabilidad());
            }
        }

        // Equipación
        if (personaje.getEquipacion() != null) {
            for (EquipoPersonaje ep : personaje.getEquipacion()) {
                this.idEquipos.add(ep.getObjeto().getIdObjeto());
                this.ranuras.add(ep.getRanura());
            }
        }

        // Inventario con cantidad
        if (personaje.getObjetosConCantidad() != null) {
            for (InventarioPersonaje obPj : personaje.getObjetosConCantidad()) {
                this.idObjetos.add(obPj.getObjeto().getIdObjeto());
                this.cantidadObjetos.add(obPj.getCantidad());
            }
        }
    }
}

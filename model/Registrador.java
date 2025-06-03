package model;

/**
 * Clase que representa un registrador de consumo de energía.
 * Cada registrador tiene una identificación, dirección, ciudad
 * y una instancia de consumo que representa los datos energéticos diarios.
 */
public class Registrador {

    /** Número de identificación del registrador. */
    private String numeroIdentificacion;

    /** Dirección física donde está ubicado el registrador. */
    private String direccion;

    /** Ciudad donde se encuentra el registrador. */
    private String ciudad;

    /** Objeto que contiene los datos de consumo de energía del registrador. */
    private Consumo consumo;

    /**
     * Constructor para crear un nuevo registrador.
     *
     * @param numeroIdentificacion Número de identificación del registrador.
     * @param direccion            Dirección del registrador.
     * @param ciudad               Ciudad donde se encuentra el registrador.
     * @param dias                 Número de días para inicializar el consumo.
     */
    public Registrador(String numeroIdentificacion, String direccion, String ciudad, int dias) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.consumo = new Consumo(dias);
    }

    /**
     * @return El número de identificación del registrador.
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * @return La dirección del registrador.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece una nueva dirección para el registrador.
     *
     * @param direccion Nueva dirección.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return La ciudad donde se encuentra el registrador.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece una nueva ciudad para el registrador.
     *
     * @param ciudad Nueva ciudad.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return El objeto de consumo asociado al registrador.
     */
    public Consumo getConsumo() {
        return consumo;
    }

    /**
     * Establece un nuevo objeto de consumo para el registrador.
     *
     * @param consumo Objeto Consumo que se asignará al registrador.
     */
    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }
}

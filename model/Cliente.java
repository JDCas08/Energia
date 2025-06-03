package model;

import java.util.ArrayList;

/**
 * Representa un cliente no regulado del servicio eléctrico.
 * Contiene información personal del cliente y sus registradores asociados.
 */
public class Cliente {
    private String numeroIdentificacion;
    private String tipoIdentificacion;
    private String correoElectronico;
    private String direccionFisica;
    private ArrayList<Registrador> registradores;

    /**
     * Constructor para crear un nuevo cliente.
     *
     * @param numeroIdentificacion Número de identificación del cliente.
     * @param tipoIdentificacion Tipo de identificación del cliente.
     * @param correoElectronico Correo electrónico del cliente.
     * @param direccionFisica Dirección física del cliente.
     */
    public Cliente(String numeroIdentificacion, String tipoIdentificacion, String correoElectronico, String direccionFisica) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.tipoIdentificacion = tipoIdentificacion;
        this.correoElectronico = correoElectronico;
        this.direccionFisica = direccionFisica;
        this.registradores = new ArrayList<>();
    }

    // Getters y setters

    /**
     * Obtiene el número de identificación del cliente.
     *
     * @return Número de identificación.
     */
    public String getNumeroIdentificacion() { return numeroIdentificacion; }

    /**
     * Obtiene el tipo de identificación del cliente.
     *
     * @return Tipo de identificación.
     */
    public String getTipoIdentificacion() { return tipoIdentificacion; }

    /**
     * Establece el tipo de identificación del cliente.
     *
     * @param tipoIdentificacion Nuevo tipo de identificación.
     */
    public void setTipoIdentificacion(String tipoIdentificacion) { this.tipoIdentificacion = tipoIdentificacion; }

    /**
     * Obtiene el correo electrónico del cliente.
     *
     * @return Correo electrónico.
     */
    public String getCorreoElectronico() { return correoElectronico; }

    /**
     * Establece el correo electrónico del cliente.
     *
     * @param correoElectronico Nuevo correo electrónico.
     */
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    /**
     * Obtiene la dirección física del cliente.
     *
     * @return Dirección física.
     */
    public String getDireccionFisica() { return direccionFisica; }

    /**
     * Establece la dirección física del cliente.
     *
     * @param direccionFisica Nueva dirección física.
     */
    public void setDireccionFisica(String direccionFisica) { this.direccionFisica = direccionFisica; }

    /**
     * Agrega un registrador al cliente.
     *
     * @param r Objeto Registrador a agregar.
     */
    public void agregarRegistrador(Registrador r) {
        this.registradores.add(r);
    }

    /**
     * Busca un registrador por su número de identificación.
     *
     * @param id Número de identificación del registrador.
     * @return Registrador encontrado o null si no existe.
     */
    public Registrador buscarRegistradorPorId(String id) {
        for (Registrador r : registradores) {
            if (r.getNumeroIdentificacion().equals(id)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Obtiene la lista de registradores asociados al cliente.
     *
     * @return Lista de registradores.
     */
    public ArrayList<Registrador> getRegistradores() {
        return registradores;
    }

    /**
     * Método no implementado. Debe eliminarse o desarrollarse.
     *
     * @param correo Correo electrónico (no utilizado).
     */
    public void setCorreo(String correo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCorreo'");
    }
}


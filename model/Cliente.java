package model;

import java.util.ArrayList;

public class Cliente {
    private String numeroIdentificacion;
    private String tipoIdentificacion;
    private String correoElectronico;
    private String direccionFisica;
    private ArrayList<Registrador> registradores;

    public Cliente(String numeroIdentificacion, String tipoIdentificacion, String correoElectronico, String direccionFisica) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.tipoIdentificacion = tipoIdentificacion;
        this.correoElectronico = correoElectronico;
        this.direccionFisica = direccionFisica;
        this.registradores = new ArrayList<>();
    }

    // Getters y setters
    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public String getTipoIdentificacion() { return tipoIdentificacion; }
    public void setTipoIdentificacion(String tipoIdentificacion) { this.tipoIdentificacion = tipoIdentificacion; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public String getDireccionFisica() { return direccionFisica; }
    public void setDireccionFisica(String direccionFisica) { this.direccionFisica = direccionFisica; }

    public void agregarRegistrador(Registrador r) {
        this.registradores.add(r);
    }

    public Registrador buscarRegistradorPorId(String id) {
        for (Registrador r : registradores) {
            if (r.getNumeroIdentificacion().equals(id)) {
                return r;
            }
        }
        return null;
    }

    public ArrayList<Registrador> getRegistradores() {
        return registradores;
    }
}

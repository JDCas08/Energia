package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String numeroIdentificacion;
    private String tipoIdentificacion;
    private String correo;
    private String direccion;
    private List<Registrador> registradores;

    public Cliente(String numeroIdentificacion, String tipoIdentificacion, String correo, String direccion) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.tipoIdentificacion = tipoIdentificacion;
        this.correo = correo;
        this.direccion = direccion;
        this.registradores = new ArrayList<>();
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Registrador> getRegistradores() {
        return registradores;
    }

    public void agregarRegistrador(Registrador registrador) {
        registradores.add(registrador);
    }

    // Método para generar consumos automáticos en todos los registradores del cliente
    public void generarConsumosAutomaticos(int dias) {
        for (Registrador r : registradores) {
            r.generarConsumos(dias);
        }
    }

    // Mostrar consumos para todos los registradores
    public void mostrarConsumos() {
        for (Registrador r : registradores) {
            System.out.println("Registrador ID: " + r.getId());
            r.mostrarConsumos();
            System.out.println();
        }
    }
}


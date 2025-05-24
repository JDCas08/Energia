package model;

import java.util.ArrayList;

public class Cliente {
    private String id;
    private String tipoId;
    private String correo;
    private String direccion;
    private ArrayList<Registrador> registradores;

    // Constructor que obliga a tener al menos un registrador
    public Cliente(String id, String tipoId, String correo, String direccion, Registrador registradorInicial) {
        this.id = id;
        this.tipoId = tipoId;
        this.correo = correo;
        this.direccion = direccion;
        this.registradores = new ArrayList<>();
        this.registradores.add(registradorInicial);
    }

    public void agregarRegistrador(Registrador r) {
        registradores.add(r);
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTipoId() { return tipoId; }
    public void setTipoId(String tipoId) { this.tipoId = tipoId; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public ArrayList<Registrador> getRegistradores() { return registradores; }
    public void setRegistradores(ArrayList<Registrador> registradores) { this.registradores = registradores; }
}

package model;

public class Registrador {
    private String id;
    private String direccion;
    private String ciudad;

    public Registrador(String id, String direccion, String ciudad) {
        this.id = id;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public String getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
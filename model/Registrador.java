package model;

public class Registrador {
    private String numeroIdentificacion;
    private String direccion;
    private String ciudad;
    private Consumo consumo;

    public Registrador(String numeroIdentificacion, String direccion, String ciudad, int dias) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.consumo = new Consumo(dias);
    }

    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public Consumo getConsumo() { return consumo; }
}


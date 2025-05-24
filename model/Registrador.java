package model;

public class Registrador {
    private String id;
    private String direccion;
    private String ciudad;
    private Consumo consumo;

    public Registrador(String id, String direccion, String ciudad) {
        this.id = id;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.consumo = null;
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

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    public void generarConsumos(int dias) {
        this.consumo = new Consumo(dias);
        this.consumo.generarConsumosAutomaticos();
    }

    public void mostrarConsumos() {
        if (consumo == null) {
            System.out.println("No hay consumos generados.");
            return;
        }
        consumo.mostrarConsumos();
    }
}

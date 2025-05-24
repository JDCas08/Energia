package model;

public class Consumo {
    private int hora;
    private double kwh;

    public Consumo(int hora, double kwh) {
        this.hora = hora;
        this.kwh = kwh;
    }

    // Getters y setters
    public int getHora() { return hora; }
    public void setHora(int hora) { this.hora = hora; }

    public double getKwh() { return kwh; }
    public void setKwh(double kwh) { this.kwh = kwh; }
}


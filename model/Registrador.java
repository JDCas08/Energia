package model;

import java.util.ArrayList;
import java.util.Random;

public class Registrador {
    private String id;
    private String direccion;
    private String ciudad;
    private ArrayList<Consumo> consumos;

    public Registrador(String id, String direccion, String ciudad) {
        this.id = id;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.consumos = new ArrayList<>();
    }

    // Generar consumos automáticos según franjas horarias para un número de días
    public void generarConsumosAutomaticos(int dias) {
        Random random = new Random();
        consumos.clear();

        for (int dia = 0; dia < dias; dia++) {
            for (int hora = 0; hora < 24; hora++) {
                double kwh = 0;

                if (hora >= 0 && hora <= 6) {
                    kwh = 100 + (200 * random.nextDouble()); // 100 a 300
                } else if (hora >= 7 && hora <= 17) {
                    kwh = 300 + (300 * random.nextDouble()); // 300 a 600
                } else if (hora >= 18 && hora <= 23) {
                    kwh = 600 + (400 * random.nextDouble()); // 600 a 1000
                } else {
                    kwh = 50 + (50 * random.nextDouble()); // Para cualquier hora no cubierta (si aplica)
                }

                consumos.add(new Consumo(hora, kwh));
            }
        }
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public ArrayList<Consumo> getConsumos() { return consumos; }
    public void setConsumos(ArrayList<Consumo> consumos) { this.consumos = consumos; }
}





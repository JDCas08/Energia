package model;

import java.util.Random;

public class Consumo {
    private int[][] consumos;  // [dias][horas]
    private int dias;

    public Consumo(int dias) {
        this.dias = dias;
        consumos = new int[dias][24];
    }

    public int getDias() {
        return dias;
    }

    public int getConsumo(int dia, int hora) {
        return consumos[dia][hora];
    }

    public void setConsumo(int dia, int hora, int valor) {
        consumos[dia][hora] = valor;
    }

    public void generarConsumosAutomaticos() {
        Random rand = new Random();
        for (int d = 0; d < dias; d++) {
            for (int h = 0; h < 24; h++) {
                int valor = 0;
                if (h >= 0 && h <= 6) {
                    // Franja 1: 100 - 300
                    valor = 100 + rand.nextInt(201);
                } else if (h >= 7 && h <= 17) {
                    // Franja 2: 301 - 600
                    valor = 301 + rand.nextInt(300);
                } else if (h >= 18 && h <= 23) {
                    // Franja 3: 601 - 999
                    valor = 601 + rand.nextInt(399);
                }
                consumos[d][h] = valor;
            }
        }
    }

    public void mostrarConsumos() {
        System.out.println("--- MATRIZ DE CONSUMO DE ENERGÍA (kW/h) ---");
        System.out.print("Día\\Hora ");
        for (int h = 0; h < 24; h++) {
            System.out.printf("%4d", h);
        }
        System.out.println();
        for (int d = 0; d < dias; d++) {
            System.out.printf("Día %2d: ", d + 1);
            for (int h = 0; h < 24; h++) {
                System.out.printf("%4d", consumos[d][h]);
            }
            System.out.println();
        }
    }
}



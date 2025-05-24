package model;

import java.util.Random;

public class Consumo {
    private int[][] consumos; // [día][hora]

    public void generarConsumos(int diasDelMes) {
        consumos = new int[diasDelMes][24];
        Random rand = new Random();

        for (int dia = 0; dia < diasDelMes; dia++) {
            for (int hora = 0; hora < 24; hora++) {
                if (hora >= 0 && hora <= 6) {
                    consumos[dia][hora] = rand.nextInt(201) + 100; // 100-300
                } else if (hora >= 7 && hora <= 17) {
                    consumos[dia][hora] = rand.nextInt(301) + 300; // 300-600
                } else {
                    consumos[dia][hora] = rand.nextInt(400) + 600; // 600-999
                }
            }
        }
    }

    public void mostrarConsumos() {
        if (consumos == null || consumos.length == 0) {
            System.out.println("No hay consumos registrados.");
            return;
        }

        System.out.println("\n--- MATRIZ DE CONSUMO DE ENERGÍA (kW/h) ---");
        System.out.print("Día\\Hora ");
        for (int h = 0; h < 24; h++) {
            System.out.printf("%5d", h);
        }
        System.out.println();

        for (int d = 0; d < consumos.length; d++) {
            System.out.printf("Día %-2d: ", d + 1);
            for (int h = 0; h < 24; h++) {
                System.out.printf("%5d", consumos[d][h]);
            }
            System.out.println();
        }
    }

    public int[][] getConsumos() {
        return consumos;
    }
}


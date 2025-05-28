package model;

import java.util.Random;

public class Consumo {
    private int dias;
    private int[][] consumos; // [dias][24 horas]

    public Consumo(int dias) {
        this.dias = dias;
        consumos = new int[dias][24];
    }

    public int getDias() {
        return dias;
    }

    public int[][] getConsumos() {
        return consumos;
    }

    public void setConsumo(int dia, int hora, int valor) {
        consumos[dia][hora] = valor;
    }

    public int getConsumoHora(int dia, int hora) {
        return consumos[dia][hora];
    }

    public void cargarConsumosAutomaticos() {
        Random random = new Random();
        for (int d = 0; d < dias; d++) {
            for (int h = 0; h < 24; h++) {
                if (h >= 0 && h <= 6) {
                    consumos[d][h] = 100 + random.nextInt(201); // 100-300
                } else if (h >= 7 && h <= 17) {
                    consumos[d][h] = 301 + random.nextInt(300); // 301-600
                } else {
                    consumos[d][h] = 601 + random.nextInt(399); // 601-999
                }
            }
        }
    }

    public int consumoMinimo() {
        int min = Integer.MAX_VALUE;
        for (int[] dia : consumos) {
            for (int c : dia) {
                if (c < min) min = c;
            }
        }
        return min;
    }

    public int consumoMaximo() {
        int max = Integer.MIN_VALUE;
        for (int[] dia : consumos) {
            for (int c : dia) {
                if (c > max) max = c;
            }
        }
        return max;
    }

    public int[] consumoPorFranjas() {
        int[] franjas = new int[3]; // 0:franja1, 1:franja2, 2:franja3
        for (int d = 0; d < dias; d++) {
            for (int h = 0; h < 24; h++) {
                int c = consumos[d][h];
                if (h >= 0 && h <= 6) franjas[0] += c;
                else if (h >= 7 && h <= 17) franjas[1] += c;
                else franjas[2] += c;
            }
        }
        return franjas;
    }

    public int[] consumoPorDias() {
        int[] porDias = new int[dias];
        for (int d = 0; d < dias; d++) {
            int suma = 0;
            for (int h = 0; h < 24; h++) {
                suma += consumos[d][h];
            }
            porDias[d] = suma;
        }
        return porDias;
    }

    public int calcularFactura() {
        int total = 0;
        for (int d = 0; d < dias; d++) {
            for (int h = 0; h < 24; h++) {
                int c = consumos[d][h];
                if (h >= 0 && h <= 6) total += c * 200;
                else if (h >= 7 && h <= 17) total += c * 300;
                else total += c * 500;
            }
        }
        return total;
    }
}



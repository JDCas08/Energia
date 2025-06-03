package model;

import java.util.Random;

/**
 * Representa el consumo energético diario y por hora de un cliente.
 * Permite generar datos automáticos, calcular estadísticas y costos.
 */
public class Consumo {
    private int dias;
    private int[][] consumos; // [dias][24 horas]

    /**
     * Constructor que inicializa el consumo para un número de días determinado.
     *
     * @param dias Número de días a registrar.
     */
    public Consumo(int dias) {
        this.dias = dias;
        consumos = new int[dias][24];
    }

    /**
     * Retorna la cantidad de días registrados.
     *
     * @return Número de días.
     */
    public int getDias() {
        return dias;
    }

    /**
     * Retorna la matriz de consumos por día y hora.
     *
     * @return Matriz de consumos.
     */
    public int[][] getConsumos() {
        return consumos;
    }

    /**
     * Establece el consumo para una hora específica de un día.
     *
     * @param dia Día específico (0-based).
     * @param hora Hora específica (0-23).
     * @param valor Valor del consumo a establecer.
     */
    public void setConsumo(int dia, int hora, int valor) {
        consumos[dia][hora] = valor;
    }

    /**
     * Obtiene el consumo en una hora específica de un día.
     *
     * @param dia Día (0-based).
     * @param hora Hora (0-23).
     * @return Consumo registrado.
     */
    public int getConsumoHora(int dia, int hora) {
        return consumos[dia][hora];
    }

    /**
     * Carga automáticamente valores de consumo simulados para cada hora de cada día.
     * Se usan rangos diferenciados según la franja horaria.
     */
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

    /**
     * Retorna el valor mínimo de consumo registrado.
     *
     * @return Consumo mínimo.
     */
    public int consumoMinimo() {
        int min = Integer.MAX_VALUE;
        for (int[] dia : consumos) {
            for (int c : dia) {
                if (c < min) min = c;
            }
        }
        return min;
    }

    /**
     * Retorna el valor máximo de consumo registrado.
     *
     * @return Consumo máximo.
     */
    public int consumoMaximo() {
        int max = Integer.MIN_VALUE;
        for (int[] dia : consumos) {
            for (int c : dia) {
                if (c > max) max = c;
            }
        }
        return max;
    }

    /**
     * Calcula el consumo total por franja horaria.
     *
     * @return Arreglo de 3 posiciones: [franja1, franja2, franja3].
     */
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

    /**
     * Calcula el consumo total por día.
     *
     * @return Arreglo con el consumo total de cada día.
     */
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

    /**
     * Calcula el costo total de la factura según tarifas por franja horaria:
     * <ul>
     *   <li>Franja 1 (0-6h): 200</li>
     *   <li>Franja 2 (7-17h): 300</li>
     *   <li>Franja 3 (18-23h): 500</li>
     * </ul>
     *
     * @return Total a pagar.
     */
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

    /**
     * Genera una matriz de consumos simulados de acuerdo a un mes y año dados.
     * Considera años bisiestos.
     *
     * @param mes Mes (1-12).
     * @param anio Año.
     * @throws IllegalArgumentException Si el mes es inválido.
     */
    public void generarConsumoAleatorio(int mes, int anio) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes inválido: " + mes);
        }

        int[] diasPorMes = {
            31, esBisiesto(anio) ? 29 : 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };
        this.dias = diasPorMes[mes - 1];
        this.consumos = new int[dias][24];
        cargarConsumosAutomaticos();
    }

    /**
     * Determina si un año es bisiesto.
     *
     * @param anio Año a verificar.
     * @return true si es bisiesto, false de lo contrario.
     */
    private boolean esBisiesto(int anio) {
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
    }
}




package view;

import java.util.Scanner;

public class Vista {
    private Scanner scanner;

    public Vista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Crear cliente");
        System.out.println("2. Editar cliente");
        System.out.println("3. Crear registrador");
        System.out.println("4. Editar registrador");
        System.out.println("5. Cargar consumos automáticos para todos los clientes");
        System.out.println("6. Cargar consumos automáticos para un cliente");
        System.out.println("7. Cambiar consumo de una hora de un mes de un registrador");
        System.out.println("8. Mostrar consumo mínimo de un cliente");
        System.out.println("9. Mostrar consumo máximo de un cliente");
        System.out.println("10. Mostrar consumo por franjas de un cliente");
        System.out.println("11. Mostrar consumo por días de un cliente");
        System.out.println("12. Calcular factura de un cliente");
        System.out.println("0. Salir");
        System.out.print("Seleccione opción: ");
    }

    public int leerEntero() {
        int opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        return opcion;
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarMatrizConsumos(int[][] matriz) {
        System.out.println("--- MATRIZ DE CONSUMO DE ENERGÍA (kW/h) ---");
        System.out.print("Día\\Hora\t");
        for (int h = 0; h < 24; h++) {
            System.out.print(h + "\t");
        }
        System.out.println();
        for (int d = 0; d < matriz.length; d++) {
            System.out.print("Día " + (d + 1) + ":\t");
            for (int h = 0; h < 24; h++) {
                System.out.print(matriz[d][h] + "\t");
            }
            System.out.println();
        }
    }
}


package view;

import java.util.Scanner;

public class Vista {
    private Scanner scanner;

    public Vista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Crear cliente");
        System.out.println("2. Editar cliente");
        System.out.println("3. Crear registrador");
        System.out.println("4. Editar registrador");
        System.out.println("5. Cargar consumos automáticamente para todos los clientes");
        System.out.println("6. Cargar consumos automáticamente para un cliente");
        System.out.println("7. Cambiar consumo de una hora específica");
        System.out.println("8. Mostrar matriz de consumo");
        System.out.println("9. Mostrar consumo mínimo de un cliente");
        System.out.println("10. Mostrar consumo máximo de un cliente");
        System.out.println("11. Mostrar consumo por franjas");
        System.out.println("12. Mostrar consumo por días");
        System.out.println("13. Calcular valor factura del cliente");
        System.out.println("14. Generar factura en PDF");
        System.out.println("15. Eliminar cliente");
        System.out.println("0. Salir");
    }

    public int leerOpcion() {
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(scanner.nextLine());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarMatrizConsumo(int[][] matriz) {
        System.out.println("\n--- MATRIZ DE CONSUMO DE ENERGÍA (kW/h) ---");
        System.out.print("Día\\Hora\t");
        for (int h = 0; h < 24; h++) {
            System.out.print(h + "\t");
        }
        System.out.println();

        for (int dia = 0; dia < matriz.length; dia++) {
            System.out.print("Día " + (dia + 1) + ":\t");
            for (int hora = 0; hora < 24; hora++) {
                System.out.print(matriz[dia][hora] + "\t");
            }
            System.out.println();
        }
    }
}



package view;

import java.util.Scanner;

/**
 * Clase Vista que gestiona la interacción con el usuario mediante consola.
 * Proporciona métodos para mostrar menús, leer entradas y mostrar resultados.
 */
public class Vista {
    /** Objeto Scanner para leer entradas desde la consola. */
    private Scanner scanner;

    /**
     * Constructor que inicializa el Scanner para entrada por consola.
     */
    public Vista() {
        scanner = new Scanner(System.in);
    }

    /**
     * Muestra el menú principal con las opciones disponibles para el usuario.
     */
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

    /**
     * Lee la opción seleccionada por el usuario.
     *
     * @return Opción seleccionada como número entero.
     */
    public int leerOpcion() {
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Lee una entrada de texto del usuario.
     *
     * @param mensaje Mensaje que se muestra antes de la entrada.
     * @return Cadena de texto ingresada por el usuario.
     */
    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    /**
     * Lee una entrada numérica entera del usuario.
     *
     * @param mensaje Mensaje que se muestra antes de la entrada.
     * @return Número entero ingresado por el usuario.
     */
    public int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Muestra un mensaje al usuario en la consola.
     *
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Muestra una matriz de consumo energético con formato de tabla.
     *
     * @param matriz Matriz de consumos en kW/h, donde cada fila representa un día
     *               y cada columna una hora.
     */
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

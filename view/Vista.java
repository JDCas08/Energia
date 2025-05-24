package view;

import java.util.Scanner;

public class Vista {
    private Scanner scanner;

    public Vista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String leerCadena(String mensaje) {
        System.out.print(mensaje + " ");
        return scanner.nextLine();
    }

    public int menuOpciones() {
        System.out.println("\nSeleccione una opción:");
        System.out.println("1. Crear cliente");
        System.out.println("2. Editar cliente");
        System.out.println("3. Crear registrador");
        System.out.println("4. Editar registrador");
        System.out.println("5. Salir");
        System.out.print("Opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Opción inválida
        }
    }
}

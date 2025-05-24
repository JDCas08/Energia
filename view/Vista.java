package view;

import java.util.Scanner;
import model.Cliente;
import model.Registrador;

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
        System.out.println("5. Cargar consumos para todos los clientes");
        System.out.println("6. Cargar consumos para un cliente");
        System.out.println("7. Cambiar consumo de una hora específica");
        System.out.println("8. Salir");
    }

    public int pedirOpcion() {
        System.out.print("Ingrese opción: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int pedirAnio() {
        System.out.print("Ingrese el año (ejemplo: 2025): ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int pedirMes() {
        System.out.print("Ingrese el mes (1-12): ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String pedirDato(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public Cliente crearCliente() {
        System.out.println("Crear nuevo cliente:");
        String numId = pedirDato("Número único de identificación: ");
        String tipoId = pedirDato("Tipo de identificación: ");
        String correo = pedirDato("Correo electrónico: ");
        String direccion = pedirDato("Dirección física: ");
        return new Cliente(numId, tipoId, correo, direccion);
    }

    public void editarCliente(Cliente cliente) {
        System.out.println("Editar cliente (no se puede cambiar número único de identificación):");
        String tipoId = pedirDato("Nuevo tipo de identificación (" + cliente.getTipoIdentificacion() + "): ");
        String correo = pedirDato("Nuevo correo electrónico (" + cliente.getCorreo() + "): ");
        String direccion = pedirDato("Nueva dirección física (" + cliente.getDireccion() + "): ");

        if (!tipoId.isBlank()) cliente.setTipoIdentificacion(tipoId);
        if (!correo.isBlank()) cliente.setCorreo(correo);
        if (!direccion.isBlank()) cliente.setDireccion(direccion);
    }

    public Registrador crearRegistrador() {
        System.out.println("Crear nuevo registrador:");
        String numId = pedirDato("Número de identificación: ");
        String direccion = pedirDato("Dirección: ");
        String ciudad = pedirDato("Ciudad: ");
        return new Registrador(numId, direccion, ciudad);
    }

    public void editarRegistrador(Registrador registrador) {
        System.out.println("Editar registrador (no se puede cambiar número de identificación):");
        String direccion = pedirDato("Nueva dirección (" + registrador.getDireccion() + "): ");
        String ciudad = pedirDato("Nueva ciudad (" + registrador.getCiudad() + "): ");

        if (!direccion.isBlank()) registrador.setDireccion(direccion);
        if (!ciudad.isBlank()) registrador.setCiudad(ciudad);
    }
}

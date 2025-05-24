package controller;

import java.util.ArrayList;
import model.Cliente;
import model.Registrador;   
import java.util.List;
import java.util.Scanner;

public class Controlador {
    private List<Cliente> clientes;
    private Scanner scanner;

    public Controlador() {
        clientes = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> editarCliente();
                case 3 -> crearRegistrador();
                case 4 -> editarRegistrador();
                case 5 -> cargarConsumosTodos();
                case 6 -> cargarConsumoCliente();
                case 7 -> mostrarConsumosCliente();
                case 8 -> cambiarConsumoHora();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n--- MENÚ ---");
        System.out.println("1. Crear cliente");
        System.out.println("2. Editar cliente");
        System.out.println("3. Crear registrador");
        System.out.println("4. Editar registrador");
        System.out.println("5. Cargar consumos de todos los clientes");
        System.out.println("6. Cargar consumos de un cliente");
        System.out.println("7. Mostrar consumos de un cliente");
        System.out.println("8. Cambiar consumo de una hora específica");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void crearCliente() {
        System.out.print("Número de identificación: ");
        String id = scanner.nextLine();
        System.out.print("Tipo de identificación: ");
        String tipoId = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        clientes.add(new Cliente(id, tipoId, correo, direccion));
        System.out.println("Cliente creado.");
    }

    private void editarCliente() {
        Cliente c = buscarClientePorId();
        if (c != null) {
            System.out.print("Nuevo tipo de identificación: ");
            c.setTipoIdentificacion(scanner.nextLine());
            System.out.print("Nuevo correo: ");
            c.setCorreo(scanner.nextLine());
            System.out.print("Nueva dirección: ");
            c.setDireccion(scanner.nextLine());
            System.out.println("Cliente actualizado.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void crearRegistrador() {
        Cliente c = buscarClientePorId();
        if (c != null) {
            System.out.print("ID del registrador: ");
            String id = scanner.nextLine();
            System.out.print("Dirección del registrador: ");
            String direccion = scanner.nextLine();
            System.out.print("Ciudad: ");
            String ciudad = scanner.nextLine();
            c.agregarRegistrador(new Registrador(id, direccion, ciudad));
            System.out.println("Registrador agregado.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void editarRegistrador() {
        Cliente c = buscarClientePorId();
        if (c != null && !c.getRegistradores().isEmpty()) {
            System.out.print("ID del registrador a editar: ");
            String id = scanner.nextLine();
            for (Registrador r : c.getRegistradores()) {
                if (r.getId().equals(id)) {
                    System.out.print("Nueva dirección: ");
                    r.setDireccion(scanner.nextLine());
                    System.out.print("Nueva ciudad: ");
                    r.setCiudad(scanner.nextLine());
                    System.out.println("Registrador actualizado.");
                    return;
                }
            }
            System.out.println("Registrador no encontrado.");
        } else {
            System.out.println("Cliente no encontrado o sin registradores.");
        }
    }

    private void cargarConsumosTodos() {
        System.out.print("Año: ");
        int anio = scanner.nextInt();
        System.out.print("Mes (1-12): ");
        int mes = scanner.nextInt();
        scanner.nextLine();
        int dias = obtenerDiasDelMes(mes, anio);
        for (Cliente c : clientes) {
            c.generarConsumosAutomaticos(dias);
        }
        System.out.println("Consumos generados para todos los clientes.");
    }

    private void cargarConsumoCliente() {
        Cliente c = buscarClientePorId();
        if (c != null) {
            System.out.print("Año: ");
            int anio = scanner.nextInt();
            System.out.print("Mes (1-12): ");
            int mes = scanner.nextInt();
            scanner.nextLine();
            int dias = obtenerDiasDelMes(mes, anio);
            c.generarConsumosAutomaticos(dias);
            System.out.println("Consumos generados para el cliente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void mostrarConsumosCliente() {
        Cliente c = buscarClientePorId();
        if (c != null) {
            c.mostrarConsumos();
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void cambiarConsumoHora() {
        Cliente c = buscarClientePorId();
        if (c != null && !c.getRegistradores().isEmpty()) {
            System.out.print("ID del registrador: ");
            String id = scanner.nextLine();
            Registrador registrador = null;
            for (Registrador r : c.getRegistradores()) {
                if (r.getId().equals(id)) {
                    registrador = r;
                    break;
                }
            }
            if (registrador != null && registrador.getConsumo() != null) {
                System.out.print("Día (1-" + registrador.getConsumo().getDias() + "): ");
                int dia = scanner.nextInt() - 1;
                System.out.print("Hora (0-23): ");
                int hora = scanner.nextInt();
                System.out.print("Nuevo consumo (kW/h): ");
                int nuevoConsumo = scanner.nextInt();
                scanner.nextLine();

                registrador.getConsumo().setConsumo(dia, hora, nuevoConsumo);
                System.out.println("Consumo actualizado.");
            } else {
                System.out.println("Registrador no encontrado o sin consumos generados.");
            }
        } else {
            System.out.println("Cliente no encontrado o sin registradores.");
        }
    }

    private Cliente buscarClientePorId() {
        System.out.print("Número de identificación del cliente: ");
        String id = scanner.nextLine();
        for (Cliente c : clientes) {
            if (c.getNumeroIdentificacion().equals(id)) {
                return c;
            }
        }
        return null;
    }

    private int obtenerDiasDelMes(int mes, int anio) {
        switch (mes) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                return 31;
            }
            case 4, 6, 9, 11 -> {
                return 30;
            }
            case 2 -> {
                if (esBisiesto(anio)) return 29;
                else return 28;
            }
            default -> {
                System.out.println("Mes inválido, se asigna 30 días por defecto");
                return 30;
            }
        }
    }

    private boolean esBisiesto(int anio) {
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
    }
}


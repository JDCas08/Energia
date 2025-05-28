package controller;

import java.util.ArrayList;
import model.Cliente;
import model.Registrador;   
import view.Vista;

public class Controlador {
    private ArrayList<Cliente> clientes;
    private Vista vista;

    public Controlador() {
        clientes = new ArrayList<>();
        vista = new Vista();
    }

    public void iniciar() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerEntero();
            switch (opcion) {
                case 1: crearCliente(); break;
                case 2: editarCliente(); break;
                case 3: crearRegistrador(); break;
                case 4: editarRegistrador(); break;
                case 5: cargarConsumosTodosClientes(); break;
                case 6: cargarConsumosCliente(); break;
                case 7: cambiarConsumoHora(); break;
                case 8: mostrarConsumoMinimo(); break;
                case 9: mostrarConsumoMaximo(); break;
                case 10: mostrarConsumoPorFranjas(); break;
                case 11: mostrarConsumoPorDias(); break;
                case 12: calcularFacturaCliente(); break;
                case 0: vista.mostrarMensaje("Saliendo..."); break;
                default: vista.mostrarMensaje("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void crearCliente() {
        String id = vista.leerTexto("Número de identificación: ");
        String tipoId = vista.leerTexto("Tipo de identificación: ");
        String correo = vista.leerTexto("Correo electrónico: ");
        String direccion = vista.leerTexto("Dirección física: ");
        Cliente c = new Cliente(id, tipoId, correo, direccion);
        clientes.add(c);
        vista.mostrarMensaje("Cliente creado.");
    }

    private Cliente buscarClientePorId(String id) {
        for (Cliente c : clientes) {
            if (c.getNumeroIdentificacion().equals(id)) return c;
        }
        return null;
    }

    private void editarCliente() {
        String id = vista.leerTexto("Ingrese número de identificación del cliente a editar: ");
        Cliente c = buscarClientePorId(id);
        if (c == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }
        c.setTipoIdentificacion(vista.leerTexto("Nuevo tipo de identificación: "));
        c.setCorreoElectronico(vista.leerTexto("Nuevo correo electrónico: "));
        c.setDireccionFisica(vista.leerTexto("Nueva dirección física: "));
        vista.mostrarMensaje("Cliente actualizado.");
    }

    private void crearRegistrador() {
        String clienteId = vista.leerTexto("Ingrese número de identificación del cliente: ");
        Cliente c = buscarClientePorId(clienteId);
        if (c == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }
        String regId = vista.leerTexto("Número de identificacion del registrador: ");
        String dir = vista.leerTexto("Dirección del registrador: ");
        String ciudad = vista.leerTexto("Ciudad del registrador: ");
        int dias = Integer.parseInt(vista.leerTexto("Ingrese número de días del mes para crear consumo: "));

        Registrador r = new Registrador(regId, dir, ciudad, dias);
        c.agregarRegistrador(r);
        vista.mostrarMensaje("Registrador agregado al cliente.");
    }

    private void editarRegistrador() {
        String clienteId = vista.leerTexto("Ingrese número de identificación del cliente: ");
        Cliente c = buscarClientePorId(clienteId);
        if (c == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }
        String regId = vista.leerTexto("Ingrese número de identificación del registrador a editar: ");
        Registrador r = c.buscarRegistradorPorId(regId);
        if (r == null) {
            vista.mostrarMensaje("Registrador no encontrado.");
            return;
        }
        r.setDireccion(vista.leerTexto("Nueva dirección del registrador: "));
        r.setCiudad(vista.leerTexto("Nueva ciudad del registrador: "));
        vista.mostrarMensaje("Registrador actualizado.");
    }

    private void cargarConsumosTodosClientes() {
        for (Cliente c : clientes) {
            for (Registrador r : c.getRegistradores()) {
                r.getConsumo().cargarConsumosAutomaticos();
            }
        }
        vista.mostrarMensaje("Consumos cargados automáticamente para todos los clientes.");
    }

    private void cargarConsumosCliente() {
        String id = vista.leerTexto("Ingrese número de identificación del cliente: ");
        Cliente c = buscarClientePorId(id);
        if (c == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }
        for (Registrador r : c.getRegistradores()) {
            r.getConsumo().cargarConsumosAutomaticos();
        }
        vista.mostrarMensaje("Consumos cargados automáticamente para el cliente.");
    }

    private void cambiarConsumoHora() {
        String clienteId = vista.leerTexto("Ingrese número de identificación del cliente: ");
        Cliente c = buscarClientePorId(clienteId);
        if (c == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }
        String regId = vista.leerTexto("Ingrese número de identificación del registrador: ");
        Registrador r = c.buscarRegistradorPorId(regId);
        if (r == null) {
            vista.mostrarMensaje("Registrador no encontrado.");
            return;
        }
        int dia = Integer.parseInt(vista.leerTexto("Ingrese día (1-" + r.getConsumo().getDias() + "): ")) - 1;
        int hora = Integer.parseInt(vista.leerTexto("Ingrese hora (0-23): "));
        int nuevoConsumo = Integer.parseInt(vista.leerTexto("Ingrese nuevo consumo (kWh): "));

        if (dia < 0 || dia >= r.getConsumo().getDias() || hora < 0 || hora > 23) {
            vista.mostrarMensaje("Día u hora inválidos.");
            return;
        }

        r.getConsumo().setConsumo(dia, hora, nuevoConsumo);
        vista.mostrarMensaje("Consumo actualizado.");
    }

    private void mostrarConsumoMinimo() {
        String clienteId = vista.leerTexto("Ingrese número de identificación del cliente: ");
        Cliente c = buscarClientePorId(clienteId);
        if (c == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }
        for (Registrador r : c.getRegistradores()) {
            int min = r.getConsumo().consumoMinimo();
            vista.mostrarMensaje("Registrador " + r.getNumeroIdentificacion() + " consumo mínimo: " + min);
            vista.mostrarMatrizConsumos(r.getConsumo().getConsumos());
        }
    }

    private void mostrarConsumoMaximo() {
        String clienteId = vista.leerTexto("Ingrese número de identificación del cliente: ");
        Cliente c = buscarClientePorId(clienteId);
        if (c == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }
        for (Registrador r : c.getRegistradores()) {
            int max = r.getConsumo().consumoMaximo();
            vista.mostrarMensaje("Registrador " + r.getNumeroIdentificacion() + " consumo máximo: " + max);
            vista.mostrarMatrizConsumos(r.getConsumo().getConsumos());
        }
    }

    private void mostrarConsumoPorFranjas() {
        String clienteId = vista.leerTexto("Ingrese número de identificación del cliente: ");
        Cliente c = buscarClientePorId(clienteId);
        if (c == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }
        for (Registrador r : c.getRegistradores()) {
            int[] franjas = r.getConsumo().consumoPorFranjas();
            vista.mostrarMensaje("Registrador " + r.getNumeroIdentificacion() + " consumo por franjas:");
            vista.mostrarMensaje("Franja 0-6h: " + franjas[0]);
            vista.mostrarMensaje("Franja 7-17h: " + franjas[1]);
            vista.mostrarMensaje("Franja 18-23h: " + franjas[2]);
        }
    }

    private void mostrarConsumoPorDias() {
        String clienteId = vista.leerTexto("Ingrese número de identificación del cliente: ");
        Cliente c = buscarClientePorId(clienteId);
        if (c == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }
        for (Registrador r : c.getRegistradores()) {
            int[] porDias = r.getConsumo().consumoPorDias();
            vista.mostrarMensaje("Registrador " + r.getNumeroIdentificacion() + " consumo por días:");
            for (int i = 0; i < porDias.length; i++) {
                vista.mostrarMensaje("Día " + (i + 1) + ": " + porDias[i]);
            }
        }
    }

    private void calcularFacturaCliente() {
        String clienteId = vista.leerTexto("Ingrese número de identificación del cliente: ");
        Cliente c = buscarClientePorId(clienteId);
        if (c == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }
        for (Registrador r : c.getRegistradores()) {
            int factura = r.getConsumo().calcularFactura();
            vista.mostrarMensaje("Registrador " + r.getNumeroIdentificacion() + " factura total: $" + factura);
        }
    }
}

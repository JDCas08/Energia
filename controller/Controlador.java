package controller;

import java.util.ArrayList;
import model.Cliente;
import model.Consumo;
import model.Factura;
import model.Registrador;   
import view.Vista;

/**
 * Clase Controlador que maneja la lógica principal de la aplicación de gestión de clientes de energía.
 * Coordina la interacción entre la vista y los modelos (Cliente, Registrador, Consumo, Factura).
 */
public class Controlador {
    private ArrayList<Cliente> clientes;
    private Vista vista;

    /**
     * Constructor de la clase Controlador.
     * Inicializa la lista de clientes y la vista.
     */
    public Controlador() {
        clientes = new ArrayList<>();
        vista = new Vista();
    }

    /**
     * Inicia el menú principal de la aplicación y gestiona la selección de opciones del usuario.
     */
    public void iniciar() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();
            switch (opcion) {
                case 1: crearCliente(); break;
                case 2: editarCliente(); break;
                case 3: crearRegistrador(); break;
                case 4: editarRegistrador(); break;
                case 5: cargarConsumosTodos(); break;
                case 6: cargarConsumosCliente(); break;
                case 7: cambiarConsumoHora(); break;
                case 8: mostrarMatrizConsumo(); break;
                case 9: mostrarConsumoMinimo(); break;
                case 10: mostrarConsumoMaximo(); break;
                case 11: mostrarConsumoPorFranjas(); break;
                case 12: mostrarConsumoPorDias(); break;
                case 13: calcularFacturaCliente(); break;
                case 14: generarFacturaPDF(); break;
                case 15: eliminarCliente(); break;
                case 0: vista.mostrarMensaje("Saliendo..."); break;
                default: vista.mostrarMensaje("Opción inválida.");
            }
        } while (opcion != 0);
    }

    /** Crea un nuevo cliente con los datos ingresados desde la vista. */
    private void crearCliente() {
        String id = vista.leerTexto("Número de identificación:");
        String tipo = vista.leerTexto("Tipo de identificación:");
        String correo = vista.leerTexto("Correo electrónico:");
        String direccion = vista.leerTexto("Dirección física:");
        clientes.add(new Cliente(id, tipo, correo, direccion));
        vista.mostrarMensaje("Cliente creado correctamente.");
    }

    /** Edita los datos de un cliente existente. */
    private void editarCliente() {
        Cliente cliente = buscarCliente();
        if (cliente != null) {
            String tipo = vista.leerTexto("Nuevo tipo de identificación:");
            String correo = vista.leerTexto("Nuevo correo electrónico:");
            String direccion = vista.leerTexto("Nueva dirección física:");
            cliente.setTipoIdentificacion(tipo);
            cliente.setCorreo(correo);
            cliente.setDireccionFisica(direccion);
            vista.mostrarMensaje("Cliente actualizado.");
        }
    }

    /** Crea un nuevo registrador para un cliente existente. */
    private void crearRegistrador() {
        Cliente cliente = buscarCliente();
        if (cliente != null) {
            String id = vista.leerTexto("ID del registrador:");
            String direccion = vista.leerTexto("Dirección:");
            String ciudad = vista.leerTexto("Ciudad:");
            cliente.agregarRegistrador(new Registrador(id, direccion, ciudad, 0));
            vista.mostrarMensaje("Registrador creado.");
        }
    }

    /** Edita los datos de un registrador existente. */
    private void editarRegistrador() {
        Cliente cliente = buscarCliente();
        if (cliente != null) {
            Registrador reg = buscarRegistrador(cliente);
            if (reg != null) {
                String direccion = vista.leerTexto("Nueva dirección:");
                String ciudad = vista.leerTexto("Nueva ciudad:");
                reg.setDireccion(direccion);
                reg.setCiudad(ciudad);
                vista.mostrarMensaje("Registrador actualizado.");
            }
        }
    }

    /** Genera consumos aleatorios para todos los clientes y registradores en un mes específico. */
    private void cargarConsumosTodos() {
        int anio = vista.leerEntero("Ingrese el año:");
        int mes = vista.leerEntero("Ingrese el mes:");
        for (Cliente cliente : clientes) {
            for (Registrador reg : cliente.getRegistradores()) {
                Consumo consumo = new Consumo(obtenerDiasDelMes(mes, anio));
                consumo.generarConsumoAleatorio(mes, anio);
                reg.setConsumo(consumo);
            }
        }
        vista.mostrarMensaje("Consumos generados para todos los clientes.");
    }

    /** Genera consumo para un cliente y registrador específicos. */
    private void cargarConsumosCliente() {
        Cliente cliente = buscarCliente();
        if (cliente != null) {
            Registrador reg = buscarRegistrador(cliente);
            if (reg != null) {
                int anio = vista.leerEntero("Ingrese el año:");
                int mes = vista.leerEntero("Ingrese el mes:");
                Consumo consumo = new Consumo(obtenerDiasDelMes(mes, anio));
                consumo.generarConsumoAleatorio(mes, anio);
                reg.setConsumo(consumo);
                vista.mostrarMensaje("Consumo generado para el cliente.");
            }
        }
    }

    /**
     * Calcula la cantidad de días de un mes, considerando años bisiestos.
     * @param mes Mes del año (1-12)
     * @param anio Año
     * @return Número de días del mes
     */
    private int obtenerDiasDelMes(int mes, int anio) {
        return switch (mes) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> (anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0)) ? 29 : 28;
            default -> throw new IllegalArgumentException("Mes inválido: " + mes);
        };
    }

    /** Permite modificar el consumo de una hora específica para un cliente. */
    private void cambiarConsumoHora() {
        Cliente cliente = buscarCliente();
        if (cliente != null) {
            Registrador reg = buscarRegistrador(cliente);
            if (reg != null) {
                int dia = vista.leerEntero("Día:") - 1;
                int hora = vista.leerEntero("Hora:");
                int nuevo = vista.leerEntero("Nuevo consumo (kW/h):");
                reg.getConsumo().setConsumo(dia, hora, nuevo);
                vista.mostrarMensaje("Consumo actualizado.");
            }
        }
    }

    /** Muestra la matriz de consumo del registrador de un cliente. */
    private void mostrarMatrizConsumo() {
        Cliente cliente = buscarCliente();
        if (cliente == null) return;
        Registrador reg = buscarRegistrador(cliente);
        if (reg == null) return;
        vista.mostrarMatrizConsumo(reg.getConsumo().getConsumos());
    }

    /** Muestra el consumo mínimo del mes para un cliente. */
    private void mostrarConsumoMinimo() {
        Cliente cliente = buscarCliente();
        if (cliente == null) return;
        Registrador reg = buscarRegistrador(cliente);
        if (reg == null) return;
        int minimo = reg.getConsumo().consumoMinimo();
        vista.mostrarMensaje("Consumo mínimo del mes: " + minimo + " kW/h");
    }

    /** Muestra el consumo máximo del mes para un cliente. */
    private void mostrarConsumoMaximo() {
        Cliente cliente = buscarCliente();
        if (cliente == null) return;
        Registrador reg = buscarRegistrador(cliente);
        if (reg == null) return;
        int maximo = reg.getConsumo().consumoMaximo();
        vista.mostrarMensaje("Consumo máximo del mes: " + maximo + " kW/h");
    }

    /** Muestra el consumo de energía dividido por franjas horarias. */
    private void mostrarConsumoPorFranjas() {
        Cliente cliente = buscarCliente();
        if (cliente == null) return;
        Registrador reg = buscarRegistrador(cliente);
        if (reg == null) return;
        int[] franjas = reg.getConsumo().consumoPorFranjas();
        for (int i = 0; i < franjas.length; i++) {
            vista.mostrarMensaje("Franja " + (i + 1) + ": " + franjas[i] + " kW/h");
        }
    }

    /** Muestra el consumo diario del mes para un cliente. */
    private void mostrarConsumoPorDias() {
        Cliente cliente = buscarCliente();
        if (cliente == null) return;
        Registrador reg = buscarRegistrador(cliente);
        if (reg == null) return;
        int[] dias = reg.getConsumo().consumoPorDias();
        for (int i = 0; i < dias.length; i++) {
            vista.mostrarMensaje("Día " + (i + 1) + ": " + dias[i] + " kW/h");
        }
    }

    /** Calcula el valor total de la factura de energía para un cliente. */
    private void calcularFacturaCliente() {
        Cliente cliente = buscarCliente();
        if (cliente == null) return;
        Registrador reg = buscarRegistrador(cliente);
        if (reg == null) return;
        int total = reg.getConsumo().calcularFactura();
        vista.mostrarMensaje("Valor total de la factura: $" + total);
    }

    /** Genera una factura en PDF para un cliente. */
    private void generarFacturaPDF() {
        Cliente cliente = buscarCliente();
        if (cliente == null) return;
        Registrador reg = buscarRegistrador(cliente);
        if (reg == null) return;
        int mes = vista.leerEntero("Ingrese el mes (1-12): ");
        String archivo = "factura_" + cliente.getNumeroIdentificacion() + "_mes" + mes + ".pdf";
        Factura factura = new Factura();
        factura.generarFacturaPDF(cliente, reg, mes, archivo);
    }

    /** Elimina un cliente del sistema a partir de su número de identificación. */
    private void eliminarCliente() {
        String id = vista.leerTexto("Ingrese número de identificación del cliente a eliminar: ");
        Cliente clienteAEliminar = null;
        for (Cliente c : clientes) {
            if (c.getNumeroIdentificacion().equals(id)) {
                clienteAEliminar = c;
                break;
            }
        }
        if (clienteAEliminar != null) {
            clientes.remove(clienteAEliminar);
            vista.mostrarMensaje("Cliente eliminado correctamente.");
        } else {
            vista.mostrarMensaje("Cliente no encontrado.");
        }
    }

    /**
     * Busca un cliente a partir de su número de identificación.
     * @return El cliente encontrado o null si no existe.
     */
    private Cliente buscarCliente() {
        String id = vista.leerTexto("Ingrese número de identificación del cliente: ");
        for (Cliente c : clientes) {
            if (c.getNumeroIdentificacion().equals(id)) return c;
        }
        vista.mostrarMensaje("Cliente no encontrado.");
        return null;
    }

    /**
     * Busca un registrador por ID para un cliente dado.
     * @param cliente Cliente al que pertenece el registrador.
     * @return El registrador encontrado o null si no existe.
     */
    private Registrador buscarRegistrador(Cliente cliente) {
        String id = vista.leerTexto("Ingrese número de identificación del registrador: ");
        Registrador reg = cliente.buscarRegistradorPorId(id);
        if (reg == null) vista.mostrarMensaje("Registrador no encontrado.");
        return reg;
    }
}

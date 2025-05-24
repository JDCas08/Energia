package controller;

import java.util.ArrayList;
import model.Cliente;
import view.Vista;
import model.Registrador;   

public class Controlador {
    private Vista vista;
    private ArrayList<Cliente> clientes;

    public Controlador(Vista vista) {
        this.vista = vista;
        this.clientes = new ArrayList<>();
    }

    // 1. Crear cliente
    public void crearCliente() {
        vista.mostrarMensaje("Creando cliente nuevo:");
        String id = vista.leerCadena("Número único de identificación:");
        
        if (buscarClientePorId(id) != null) {
            vista.mostrarMensaje("Error: Ya existe un cliente con ese número de identificación.");
            return;
        }

        String tipoId = vista.leerCadena("Tipo de identificación:");
        String correo = vista.leerCadena("Correo electrónico:");
        String direccion = vista.leerCadena("Dirección física:");
        
        vista.mostrarMensaje("Datos del registrador inicial:");
        Registrador registradorInicial = crearRegistradorDesdeVista();

        Cliente cliente = new Cliente(id, tipoId, correo, direccion, registradorInicial);
        clientes.add(cliente);
        vista.mostrarMensaje("Cliente creado con éxito.");
    }

    // 2. Editar cliente (excepto id)
    public void editarCliente() {
        String id = vista.leerCadena("Número único de identificación del cliente a editar:");
        Cliente cliente = buscarClientePorId(id);
        if (cliente == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }
        String tipoId = vista.leerCadena("Nuevo tipo de identificación:");
        String correo = vista.leerCadena("Nuevo correo electrónico:");
        String direccion = vista.leerCadena("Nueva dirección física:");

        cliente.setTipoId(tipoId);
        cliente.setCorreo(correo);
        cliente.setDireccion(direccion);
        vista.mostrarMensaje("Cliente editado con éxito.");
    }

    // 3. Crear registrador y agregar a cliente
    public void crearRegistrador() {
        String idCliente = vista.leerCadena("Número de identificación del cliente para agregar registrador:");
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }
        Registrador nuevoRegistrador = crearRegistradorDesdeVista();
        cliente.agregarRegistrador(nuevoRegistrador);
        vista.mostrarMensaje("Registrador agregado con éxito.");
    }

    // 4. Editar registrador (excepto id)
    public void editarRegistrador() {
        String idCliente = vista.leerCadena("Número de identificación del cliente:");
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }
        String idRegistrador = vista.leerCadena("Número de identificación del registrador a editar:");
        Registrador registrador = buscarRegistradorPorId(cliente, idRegistrador);
        if (registrador == null) {
            vista.mostrarMensaje("Registrador no encontrado.");
            return;
        }
        String direccion = vista.leerCadena("Nueva dirección del registrador:");
        String ciudad = vista.leerCadena("Nueva ciudad del registrador:");
        registrador.setDireccion(direccion);
        registrador.setCiudad(ciudad);
        vista.mostrarMensaje("Registrador editado con éxito.");
    }

    // Métodos auxiliares para búsqueda
    private Cliente buscarClientePorId(String id) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    private Registrador buscarRegistradorPorId(Cliente cliente, String idReg) {
        for (Registrador r : cliente.getRegistradores()) {
            if (r.getId().equals(idReg)) {
                return r;
            }
        }
        return null;
    }

    // Método para crear registrador desde la vista
    private Registrador crearRegistradorDesdeVista() {
        String idReg = vista.leerCadena("Número de identificación del registrador:");
        String direccion = vista.leerCadena("Dirección del registrador:");
        String ciudad = vista.leerCadena("Ciudad del registrador:");
        return new Registrador(idReg, direccion, ciudad);
    }

    // Método para iniciar menú básico
    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            int opcion = vista.menuOpciones();
            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> editarCliente();
                case 3 -> crearRegistrador();
                case 4 -> editarRegistrador();
                case 5 -> continuar = false;
                default -> vista.mostrarMensaje("Opción inválida.");
            }
        }
        vista.mostrarMensaje("Aplicación terminada.");
    }
}

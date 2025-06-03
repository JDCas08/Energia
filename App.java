import controller.Controlador;

/**
 * Clase principal que contiene el método main para iniciar la aplicación.
 * Esta clase instancia el controlador y delega la ejecución del programa.
 */
public class App {

    /**
     * Método principal que lanza la ejecución de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        controlador.iniciar();
    }
}

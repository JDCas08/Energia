package model;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.TextAlignment;
import java.io.FileNotFoundException;
import java.time.Month;
import java.time.YearMonth;
import java.util.Locale;

/**
 * Clase encargada de generar una factura en formato PDF para un cliente,
 * con base en el consumo de energía registrado por un registrador.
 */
public class Factura {

    /**
     * Genera un archivo PDF que contiene la factura de consumo de energía
     * para un cliente específico durante un mes determinado.
     *
     * @param cliente      Cliente al que pertenece la factura.
     * @param registrador  Registrador que contiene los datos de consumo.
     * @param mesNumero    Número del mes (1-12).
     * @param rutaArchivo  Ruta donde se guardará el archivo PDF generado.
     */
    public void generarFacturaPDF(Cliente cliente, Registrador registrador, int mesNumero, String rutaArchivo) {
        try {
            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Título de la factura
            Paragraph titulo = new Paragraph("FACTURA DE CONSUMO DE ENERGÍA")
                    .setFontSize(16)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(titulo);

            // Información del cliente
            document.add(new Paragraph("\nInformación del Cliente:"));
            document.add(new Paragraph("Número de Identificación: " + cliente.getNumeroIdentificacion()));
            document.add(new Paragraph("Tipo de Identificación: " + cliente.getTipoIdentificacion()));
            document.add(new Paragraph("Correo Electrónico: " + cliente.getCorreoElectronico()));
            document.add(new Paragraph("Dirección: " + cliente.getDireccionFisica()));

            // Información del registrador
            document.add(new Paragraph("\nInformación del Registrador:"));
            document.add(new Paragraph("ID del Registrador: " + registrador.getNumeroIdentificacion()));
            document.add(new Paragraph("Dirección del Registrador: " + registrador.getDireccion()));
            document.add(new Paragraph("Ciudad del Registrador: " + registrador.getCiudad()));

            // Nombre del mes en español
            document.add(new Paragraph("\nDetalles del Consumo - Mes de " +
                    Month.of(mesNumero).getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "CO")) + ":"));

            // Tabla de consumo por día y hora
            int[][] consumos = registrador.getConsumo().getConsumos();
            int diasDelMes = YearMonth.of(java.time.Year.now().getValue(), mesNumero).lengthOfMonth();

            Table tablaConsumo = new Table(new float[25]).setWidth(100);

            // Encabezado de la tabla
            tablaConsumo.addCell(new Cell().add(new Paragraph("Día/Hora")).setBold());
            for (int i = 0; i < 24; i++) {
                tablaConsumo.addCell(new Cell().add(new Paragraph(String.valueOf(i))).setBold());
            }

            // Celdas de datos por día y hora
            for (int i = 0; i < diasDelMes; i++) {
                tablaConsumo.addCell(new Cell().add(new Paragraph(String.valueOf(i + 1))));
                for (int j = 0; j < 24; j++) {
                    tablaConsumo.addCell(new Cell().add(new Paragraph(String.valueOf(consumos[i][j]))));
                }
            }
            document.add(tablaConsumo);

            // Cálculo del total a pagar
            int totalAPagar = registrador.getConsumo().calcularFactura();
            document.add(new Paragraph("\nValor Total a Pagar: $" + totalAPagar)
                    .setFontSize(14)
                    .setBold()
                    .setTextAlignment(TextAlignment.RIGHT));

            // Cierre del documento
            document.close();
            System.out.println("Factura generada en: " + rutaArchivo);

        } catch (FileNotFoundException e) {
            System.err.println("Error al crear el archivo PDF: " + e.getMessage());
        }
    }
}


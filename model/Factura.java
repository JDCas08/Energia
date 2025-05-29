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

public class Factura {

    public void generarFacturaPDF(Cliente cliente, Registrador registrador, int mesNumero, String rutaArchivo) {
        try {
            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Título
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

            // Detalles del consumo
            document.add(new Paragraph("\nDetalles del Consumo - Mes de " +
                    Month.of(mesNumero).getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "CO")) + ":"));

            int[][] consumos = registrador.getConsumo().getConsumos();
            int diasDelMes = YearMonth.of(java.time.Year.now().getValue(), mesNumero).lengthOfMonth();

            Table tablaConsumo = new Table(new float[25]).setWidth(100);

            // Encabezado de la tabla
            tablaConsumo.addCell(new Cell().add(new Paragraph("Día/Hora")).setBold());
            for (int i = 0; i < 24; i++) {
                tablaConsumo.addCell(new Cell().add(new Paragraph(String.valueOf(i))).setBold());
            }

            // Datos de consumo
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

            document.close();
            System.out.println("Factura generada en: " + rutaArchivo);

        } catch (FileNotFoundException e) {
            System.err.println("Error al crear el archivo PDF: " + e.getMessage());
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import util.QrGenerator;
import domen.Racun;
import domen.StavkaRacuna;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import konfiguracija.Konfiguracija;

/**
 *
 * @author marko
 */
public class PdfGenerator {
    
    private static Paragraph centar(String tekst, Font font) {
        Paragraph p = new Paragraph(tekst, font);
        p.setAlignment(Element.ALIGN_CENTER);
        //p.setSpacingBefore(4f);
        //p.setSpacingAfter(4f);
        return p;
    }

    private static Paragraph naslov(String tekst, Font font) {
        Paragraph p = new Paragraph(tekst, font);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(6f);
        p.setSpacingAfter(6f);
        return p;
    }
    
    public static File generisiUlaznicu(StavkaRacuna sr) throws Exception {

    File pdf = new File("Ulaznica_" + sr.getKarta().getKartaID() + ".pdf");

    // Horizontalna karta
    
    
    Document document = new Document(new Rectangle(720, 240));
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf));

    document.open();
    

    // ===== FONTOVI =====
    Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
    Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);
    Font boldFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);

    // ===== GLAVNA TABELA SA 3 KOLONE =====
    PdfPTable table = new PdfPTable(3);
    table.setWidthPercentage(100);
    table.setWidths(new float[]{2f, 5f, 3f});

    // ===== LEVO: GRB PARTIZANA =====
    Image grb = Image.getInstance("src/slike/KK_Partizan_logo.svg.png");

    grb.scaleToFit(150, 150);

    PdfPCell leftCell = new PdfPCell(grb);
    leftCell.setBorder(Rectangle.NO_BORDER);
    leftCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    leftCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    table.addCell(leftCell);

    // ===== CENTAR: TEKST =====
    PdfPCell centerCell = new PdfPCell();
    centerCell.setBorder(Rectangle.NO_BORDER);

    // LIGA
    centerCell.addElement(centar(
        "TURKISH AIRLINES EuroLeague 2025/2026", normalFont
    ));

    
    // UTAKMICA
    centerCell.addElement(naslov(
        "KK Partizan - " +
        sr.getKarta().getUtakmica().getGostujuciTim(),
        titleFont
    ));
    

    // HALA
    centerCell.addElement(centar(
        sr.getKarta().getUtakmica().getHala().toString(),
        normalFont
    ));

    // DATUM
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    centerCell.addElement(centar(
        sdf.format(sr.getKarta().getUtakmica().getDatum()) + "h",
        normalFont
    ));

    // SEKTOR
    centerCell.addElement(centar(
        sr.getKarta().getSektor().getNaziv() +
        " - " +
        sr.getKarta().getSektor().getBroj(),
        boldFont
    ));

    // RED / SEDIŠTE
    centerCell.addElement(centar(
        "Red: " + sr.getKarta().getRed() +
        "   Sedište: " + sr.getKarta().getBrojSedista(),
        normalFont
    ));

    // CENA
    NumberFormat nf = NumberFormat.getInstance(new Locale("sr", "RS"));
    DecimalFormat df = (DecimalFormat) nf;
    df.applyPattern("#,###");

    centerCell.addElement(centar(
        df.format(sr.getCena()) + " RSD",
        boldFont
    ));

    table.addCell(centerCell);


    // ===== QR DESNO =====
    
    String url = Konfiguracija.getInstanca().getProperty("baseURL") + sr.getKarta().getKod();

    
    BufferedImage qrImg = QrGenerator.generateQRCodeImage(url, 250);
    
    ByteArrayOutputStream qrBytes = new ByteArrayOutputStream();
    ImageIO.write(qrImg, "png", qrBytes);

    Image qr = Image.getInstance(qrBytes.toByteArray());
    qr.scaleToFit(150, 150);

    PdfPCell qrCell = new PdfPCell(qr);
    qrCell.setBorder(Rectangle.NO_BORDER);
    qrCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    qrCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    table.addCell(qrCell);

    document.add(table);

    // ===== DODAJ TANAK BORDER OKO CELE KARTE =====
    PdfContentByte canvas = writer.getDirectContent();
    Rectangle rect = new Rectangle(20, 20, 700, 220);
    rect.setBorder(Rectangle.BOX);
    rect.setBorderWidth(1f);
    canvas.rectangle(rect);

   
    
    document.close();
    return pdf;
}

}



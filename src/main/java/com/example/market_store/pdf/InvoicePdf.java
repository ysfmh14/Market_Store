package com.example.market_store.pdf;

import com.example.market_store.dto.responseDto.ResponseInvoiceDto;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;

public class InvoicePdf {
    public void generatePdf(HttpServletResponse response)throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(16);
        font.setColor(Color.BLACK);

        com.lowagie.text.Font font1= FontFactory.getFont(FontFactory.HELVETICA);
        font1.setSize(14);
        com.lowagie.text.Font font2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font2.setSize(11);
        Paragraph p1 = new Paragraph("Invoice to : Youssef MAHDOUBI                                                            Invoice code : INV1526798JBJ ", font2);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p2 = new Paragraph("                                                                                                                              Payment date : 12-12-12", font2);
        p2.setAlignment(Paragraph.ALIGN_LEFT);


        document.add(p1);
        document.add(p2);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f,1.5f,1.5f,1.5f});
        table.setSpacingBefore(60);
        table.setSpacingAfter(40);
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(5);
        FontFactory.getFont(FontFactory.HELVETICA);
        font2.setColor(Color.BLACK);
//Remplire les tablehead
        cell.setPhrase(new Phrase("Items", font2));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price", font2));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Quantity", font2));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total", font2));
        table.addCell(cell);
//Ajouter les info concernons chaque colonne
        table.addCell("T-shirt");
        table.addCell("500.00 DH");
        table.addCell("2");
        table.addCell("1000.00 DH");
        //Ajouter les info concernons chaque colonne
        table.addCell("T-shirt");
        table.addCell("500.00 DH");
        table.addCell("2");
        table.addCell("1000.00 DH");
        //Ajouter les info concernons chaque colonne
        table.addCell("T-shirt");
        table.addCell("500.00 DH");
        table.addCell("2");
        table.addCell("1000.00 DH");
        LineSeparator line = new LineSeparator();
        line.setLineWidth(1); // Épaisseur de la ligne en points
        line.setLineColor(Color.BLACK);
        Paragraph p3 = new Paragraph("                                                                                                                                                                                                                                      Sub Total: 2000 dhs", font1);
        p3.setAlignment(Paragraph.ALIGN_LEFT);
        p3.setSpacingBefore(10);
        Paragraph p4 = new Paragraph("                                                                                                                                                                                                                                      Total: 2000 dhs", font1);
        p4.setAlignment(Paragraph.ALIGN_LEFT);
        p4.setSpacingAfter(10);
        LineSeparator line2 = new LineSeparator();
        line2.setLineWidth(1);
        line2.setPercentage(29);
        line2.setLineColor(Color.BLACK);
        line2.setAlignment(LineSeparator.ALIGN_RIGHT);
        Paragraph p5 = new Paragraph("                                                                                                                                                                                                                                      Tax: 0 dhs", font1);
        p5.setAlignment(Paragraph.ALIGN_LEFT);
        p5.setSpacingBefore(3);

////creation de la table 0
//        PdfPTable table1 = new PdfPTable(1);
//        table1.setWidthPercentage(100f);
//        table1.setWidths(new float[] {10f});
//        table1.setSpacingBefore(10);
//        cell.setBackgroundColor(Color.WHITE);
//        cell.setPadding(10);
//        cell.setPaddingLeft(180);
//        cell.setPhrase(new Phrase("Consistance des travaux:", F1));
//        table1.addCell(cell);
//        table1.addCell("\n"+"     "+Consistance+"\n  ");
//
//
//
//        //creation de la table 2
//        PdfPTable table2 = new PdfPTable(2);
//        table2.setWidthPercentage(100f);
//        table2.setWidths(new float[] {3.5f,3.5f});
//        table2.setSpacingBefore(10);
//        cell.setBackgroundColor(Color.WHITE);
//        cell.setPadding(10);
//        cell.setPhrase(new Phrase("Agent ONEE-BE habilité", F1));
//        table2.addCell(cell);
//        table2.addCell( "\n   "+Agent);
//        cell.setPhrase(new Phrase("Entreprise", F1));
//        table2.addCell(cell);
//        table2.addCell("\n   "+Entr);
//
//
//
//        //creation de la table 3
//        PdfPTable table3 = new PdfPTable(1);
//        table3.setWidthPercentage(100f);
//        table3.setWidths(new float[] {10f});
//        table3.setSpacingBefore(10);
//        cell.setBackgroundColor(Color.WHITE);
//        cell.setPadding(10);
//        cell.setPaddingLeft(170);
//        cell.setPhrase(new Phrase("Manoeuvres et Observation:\n\n\n"+""+"", F1));
//        table3.addCell(cell);
//
//
//
//        //creation de la table 4
//        PdfPTable table4 = new PdfPTable(   2);
//        table4.setWidthPercentage(100f);
//        table4.setWidths(new float[] {3.5f,3.5f});
//        table4.setSpacingBefore(10);
//        cell.setBackgroundColor(Color.WHITE);
//        cell.setPadding(10);
//        Font F2= FontFactory.getFont(FontFactory.HELVETICA);
//        font.setSize(14);
//
//        cell.setPhrase(new Phrase( "Etablie et validé par:  Chef de section Postes Sources",F2));
//        table4.addCell(cell);
//        cell.setPhrase(new Phrase("Vu par:  Chef SED Béni Mellal PI", F2));
//        table4.addCell(cell);
//        table4.addCell(" Nom et Prenom: "+chefsec);
//        table4.addCell("Nom et Prenom:"+chefsed);
//        table4.addCell("visa:\n\n\n\n"+"");
//        table4.addCell("visa:\n\n\n\n"+"");
//        table4.addCell("Transmise au SCR le:  22/01/2022\n  ");
//        table4.addCell("Pèces Jointes:\n  ");
//
//
//
//        //creation de la table 0
//        PdfPTable table5 = new PdfPTable(   1);
//        table5.setWidthPercentage(100f);
//        table5.setWidths(new float[] {10f});
//        table5.setSpacingBefore(10);
//        cell.setBackgroundColor(Color.WHITE);
//        cell.setPadding(10);
//        cell.setPaddingLeft(200);
//        cell.setPhrase(new Phrase("Réservé au S.C.R", F1));
//        table5.addCell(cell);
//        table5.addCell("Retrait d'exploitation effectif:  Le.............................. à ..............................\n\n Retour d'exploitation effectif: Le.............................. à ..............................\n\n Observations du S.C.R :\n ..................................................................................................................................................................................................................................................................................................................................................");
//
//        //L'ajout des tableaux au pdf
//
        document.add(table);
        document.add(line);
        document.add(p3);
        document.add(p4);
        document.add(line2);
        document.add(p5);
//        document.add(table1);
//        document.add(table2);
//        document.add(table3);
//        document.add(table4);
//        document.add(table5);
        document.close();
    }
}

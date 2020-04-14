package presentation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import main.Main;
import model.Client;
import model.Order;
import model.OrderDetail;
import model.Product;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.stream.Stream;
/**
 * is the main class for giving the output as pdf or in console
 * @author Mihali Vlad
 * @version 1.0
 * @since 1.0
 */
public class Presentation {
    /**
     * prints and manage an error for application input.
     * Also exit the application
     */
    public void errorInput() {
        System.out.println("Invalid input");
        System.exit(0);
    }
    /**
     * prints and manage an error for names.
     * Also exit the application
     */
    public void errorName() {
        System.out.println("Invalid input");
        System.exit(1);
    }

    /**
     * prints and manage an error for unsigned integers.
     * Also exit the application
     */
    public void errorUInt() {
        System.out.println("Invalid input");
        System.exit(2);
    }

    /**
     * prints and manage an error for positive float numbers.
     * Also exit the application
     */
    public void errorFloat() {
        System.out.println("Invalid input");
        System.exit(3);
    }

    /**
     * prints as pdf a bill for the client given as parameter
     * @param client represents the person that order
     * @param product represents the product that is order
     * @param orderDetail represents the detail of the order
     */
    public void pdfBill(Client client, Product product, OrderDetail orderDetail) {
        Document document = new Document();
        try{
        PdfWriter.getInstance(document, new FileOutputStream("pdfBill"+orderDetail.getId()+".pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        document.add( new Paragraph(  "Bill"));
        document.add( new Paragraph( "Name: "+client.getName()));
        document.add( new Paragraph( "Product: "+product.getName()));
        document.add( new Paragraph( "Quantity: "+orderDetail.getQuantity()));
        document.add( new Paragraph( "Sum: "+orderDetail.getQuantity()*product.getPrice()));
        document.close();
        } catch(DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * prints as pdf if the product from the order is under stock
     */
    public void pdfUnderStock() {
        Document document = new Document();
        try{
            PdfWriter.getInstance(document, new FileOutputStream("pdfUnderStock"+ (Main.INDEX_FILE++)+".pdf"));
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            document.add( new Paragraph(  "Under Stock"));
            document.close();
        } catch(DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * prints and manage an error for the order without a client or product.
     * Also exit the application
     */
    public void invalidCP() {
        System.out.println("Invalid input");
        System.exit(4);
    }

    /**
     * prints and manage an error for the order table if it is empty.
     * Also exit the application
     */
    public void errorArrayOrder() {
        System.out.println("Invalid input");
        System.exit(5);
    }

    /**
     * prints as pdf the table order combine with table orderDetail
     * @param arrayOrder is an array of order that we want to print
     * @param arrayOrderDetail is an array of orderDetail that we want to print
     */
    public void pdfReportOrder(ArrayList<Order> arrayOrder, ArrayList<OrderDetail> arrayOrderDetail) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("pdfReportOrder"+ (Main.INDEX_FILE++)+".pdf"));
            document.open();
            PdfPTable table = new PdfPTable(4);
            Stream.of("id", "idCient", "idProduct", "quantity")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle));
                        table.addCell(header);
                    });
            for (int itr=0; itr< arrayOrder.size(); itr++) {
                if(!arrayOrderDetail.get(itr).isDelete()) {
                    table.addCell(arrayOrder.get(itr).getId() + "");
                    table.addCell(arrayOrder.get(itr).getIdclient()+"");
                    table.addCell(arrayOrder.get(itr).getIdproduct() + "");
                    table.addCell(arrayOrderDetail.get(itr).getQuantity() + "");
                }
            }
            document.add(table);
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }
    /**
     * prints and manage an error for the product table if it is empty.
     * Also exit the application
     */
    public void errorArrayProduct() {
        System.out.println("Invalid input");
        System.exit(6);
    }

    /**
     * prints as pdf the table product
     * @param arrayProduct is an array of products that we want to print
     */
    public void pdfReportProduct(ArrayList<Product> arrayProduct) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("pdfReportProduct"+ (Main.INDEX_FILE++)+".pdf"));
            document.open();
            PdfPTable table = new PdfPTable(4);
            Stream.of("id", "name", "quantity", "price")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle));
                        table.addCell(header);
                    });
            for (Product p : arrayProduct) {
                if(!p.isDelete()) {
                    table.addCell(p.getId() + "");
                    table.addCell(p.getName());
                    table.addCell(p.getQuantity() + "");
                    table.addCell(p.getPrice() + "");
                }
            }
            document.add(table);
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }
    /**
     * prints and manage an error for the client.
     * Also exit the application
     */
    public void errorClient() {
        System.out.println("Invalid input");
        System.exit(7);
    }

    /**
     * prints and manage an error for the client table if it is empty.
     * Also exit the application
     */
    public void errorArrayClient() {
        System.out.println("Invalid input");
        System.exit(8);
    }

    /**
     * prints as pdf the table client prints
     * @param arrayClient is an array of client that we want to print
     */
    public void pdfReportClient(ArrayList<Client> arrayClient) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("pdfReportClient"+ (Main.INDEX_FILE++)+".pdf"));
            document.open();
            PdfPTable table = new PdfPTable(3);
            Stream.of("id", "name", "address")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle));
                        table.addCell(header);
                    });
            for (Client c : arrayClient) {
                if(!c.isDelete()) {
                    table.addCell(c.getId() + "");
                    table.addCell(c.getName());
                    table.addCell(c.getAddress());
                }
            }
            document.add(table);
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }
}

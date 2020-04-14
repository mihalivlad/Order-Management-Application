package businessLayer;

import dataAccessLayer.OrderDAO;
import dataAccessLayer.ProductDAO;
import model.Product;
import presentation.Presentation;
import java.util.ArrayList;

/**
 * validate and manage a command for the client
 * @author Mihali Vlad
 * @version 1.0
 * @since 1.0
 */
public class ValidProduct {
    /**
     * provides data access for the product table
     */
    private Product product;
    /**
     * provides data access for the product table
     */
    private ProductDAO productDAO = new ProductDAO();
    /**
     * provides data access for the order table
     */
    private OrderDAO orderDAO = new OrderDAO();
    /**
     * is a private presentation which will help the class to output
     * @since 1.0
     */
    private Presentation output = new Presentation();
    /**
     * is an empty constructor of ValidProduct class
     * @since 1.0
     */
    public ValidProduct() {
    }
    /**
     * is a constructor of the ValidProduct class
     * @param product is used to initialize product attribute
     * @since 1.0
     */
    public ValidProduct(Product product) {
        this.product = product;
    }

    /**
     * try to insert the client in the client table from database
     */
    public void insert() {
        Product p = productDAO.find(product);
        if(p == null){
            productDAO.insert(product, true);
        }else{
            p.setPrice(product.getPrice());
            p.setQuantity(p.getQuantity() + product.getQuantity());
            productDAO.update(p);
        }
    }
    /**
     * try to update "delete" row with 1(true) in the product table from database
     * and "deletes" all the orders associated with it
     */
    public void delete() {
        Product p = productDAO.find(product);
        if(p != null) {
            p.setDelete(true);
            productDAO.update(p);
            orderDAO.deleteByProduct(p);
        }
    }
    /**
     * try to select all products and output them in pdf
     */
    public void report() {
        ArrayList arrayProduct = productDAO.getTable();
        if(arrayProduct.isEmpty()) {
            output.errorArrayProduct();
        }else{
            output.pdfReportProduct(arrayProduct);
        }
    }
}

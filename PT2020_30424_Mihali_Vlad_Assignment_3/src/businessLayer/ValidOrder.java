package businessLayer;
import dataAccessLayer.ClientDAO;
import dataAccessLayer.OrderDAO;
import dataAccessLayer.OrderDetailDAO;
import dataAccessLayer.ProductDAO;
import model.Client;
import model.Order;
import model.OrderDetail;
import model.Product;
import presentation.Presentation;
import java.util.ArrayList;

/**
 * validate and manage a command for the client
 * @author Mihali Vlad
 * @version 1.0
 * @since 1.0
 */
public class ValidOrder {
    /**
     * is private field for the client of the command
     */
    private Client client;
    /**
     * is private field for the product of the command
     */
    private Product product;
    /**
     * is private field for the orderDetail of the command
     */
    private OrderDetail orderDetail;
    /**
     * is private field for the order of the command
     */
    private Order order;
    /**
     * provides data access for the client table
     */
    private ClientDAO clientDAO = new ClientDAO();
    /**
     * provides data access for the product table
     */
    private ProductDAO productDAO = new ProductDAO();
    /**
     * provides data access for the order table
     */
    private OrderDAO orderDAO = new OrderDAO();
    /**
     * provides data access for the orderDetail table
     */
    private OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
    /**
     * is a private presentation which will help the class to output
     * @since 1.0
     */
    private Presentation output = new Presentation();
    /**
     * is an empty constructor of ValidOrder class
     * @since 1.0
     */
    public ValidOrder() {
    }
    /**
     * is a constructor of the ValidOrder class
     * @param client is used to initialize client attribute
     * @param product is used to initialize product attribute
     * @param orderDetail is used to initialize orderDetail attribute
     * @since 1.0
     */
    public ValidOrder(Client client, Product product, OrderDetail orderDetail) {
        this.client = client;
        this.product = product;
        this.orderDetail = orderDetail;
    }
    /**
     * try to insert the order in the order table from database
     * and update product stock
     */
    public void insert() {
        Client c = clientDAO.find(client);
        Product p = productDAO.find(product);
        if(c != null && p!= null) {
            if (p.getQuantity() - orderDetail.getQuantity() > 0) {
                p.setQuantity(p.getQuantity() - orderDetail.getQuantity());
                order = new Order(c.getId(), p.getId());
                orderDAO.insert(order, true);
                order = orderDAO.find(order);
                orderDetail.setId(order.getId());
                orderDetailDAO.insert(orderDetail, false);
                productDAO.update(p);
                output.pdfBill(c, p, orderDetail);

            }else if(p.getQuantity() - orderDetail.getQuantity()== 0) {
                new ValidProduct().delete();
            }else {
                output.pdfUnderStock();
            }
        }else{
            output.invalidCP();
        }
    }
    /**
     * selects all orders and all the orderDetails and output them in pdf
     */
    public void report() {
        ArrayList arrayOrder = (ArrayList) orderDAO.findAll();
        ArrayList arrayOrderDetail = (ArrayList) orderDetailDAO.findAll();
        if(arrayOrder == null || arrayOrderDetail == null) {
            output.errorArrayOrder();
        }else{
            output.pdfReportOrder(arrayOrder, arrayOrderDetail);
        }
    }
}

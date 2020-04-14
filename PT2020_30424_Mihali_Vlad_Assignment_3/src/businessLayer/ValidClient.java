package businessLayer;
import dataAccessLayer.ClientDAO;
import dataAccessLayer.OrderDAO;
import model.Client;
import presentation.Presentation;
import java.util.ArrayList;

/**
 * validate and manage a command for the client
 * @author Mihali Vlad
 * @version 1.0
 * @since 1.0
 */
public class ValidClient {
    /**
     * is private field for the client of the command
     */
    private Client client;
    /**
     * provides data access for the client table
     */
    private ClientDAO clientDAO = new ClientDAO();
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
     * is an empty constructor of ValidClient class
     * @since 1.0
     */
    public ValidClient() {
    }
    /**
     * is a constructor of the ValidClient class
     * @param client is used to initialize client attribute
     * @since 1.0
     */
    public ValidClient(Client client) {
        this.client = client;
    }

    /**
     * try to insert the client in the client table from database
     */
    public void insert() {
        if(clientDAO.find(client) == null){
            clientDAO.insert(client, true);
        }else{
            output.errorClient();
        }
    }
    /**
     * try to update "delete" row with 1(true) in the client table from database
     * and "deletes" all the orders associated with it
     */
    public void delete() {
        Client c = clientDAO.find(client);
        if(c != null) {
            c.setDelete(true);
            clientDAO.update(c);
            orderDAO.deleteByClient(c);

        }
    }
    /**
     * try to select all clients and output them in pdf
     */
    public void report() {
        ArrayList arrayClient = clientDAO.getTable();
        if(arrayClient.isEmpty()) {
            output.errorArrayClient();
        }else{
            output.pdfReportClient(arrayClient);
        }
    }
}

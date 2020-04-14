package dataAccessLayer;

import connection.ConnectionFactory;
import model.Client;
import model.Order;
import model.OrderDetail;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * is a class that inheritance GenericDAO and access the table order in database
 * @author Mihali Vlad
 * @version 1.0
 * @since 1.0
 */
public class OrderDAO extends GenericDAO<Order> {
    /**
     * access the order table to find the row by its client and product
     * @param order is the order which are searching for
     * @return the find order if it is else null
     */
    public Order find(Order order) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM `order` WHERE idclient = "+order.getIdclient()+" AND idproduct = "+ order.getIdproduct();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * is a method that updates delete entry with true all the rows that have the same
     * id product equal with the parameter
     * @param p is the product that we want to delete its orders
     */
    public void deleteByProduct(Product p) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM `order` WHERE idproduct = "+ p.getId();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

             List<Order> arrayOrder = createObjects(resultSet);
            if(!arrayOrder.isEmpty()) {
                 for (Order o : arrayOrder) {
                     OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                     OrderDetail od = orderDetailDAO.findById(o.getId());
                     od.setDelete(true);
                     orderDetailDAO.update(od);
                 }
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NullPointerException e) {
            return;
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * is a method that updates delete entry with true all the rows that have the same
     * id client equal with the parameter
     * @param c is the client that we want to delete its orders
     */
    public void deleteByClient(Client c) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM `order` WHERE idclient = "+ c.getId();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            ArrayList<Order> arrayOrder = (ArrayList<Order>) createObjects(resultSet);
            if(arrayOrder == null){
                return;
            }
            for (Order o : arrayOrder) {
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                OrderDetail od = orderDetailDAO.findById(o.getId());
                od.setDelete(true);
                orderDetailDAO.update(od);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}

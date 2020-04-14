package dataAccessLayer;

import connection.ConnectionFactory;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * is a class that inheritance GenericDAO and access the table product in database
 * @author Mihali Vlad
 * @version 1.0
 * @since 1.0
 */
public class ProductDAO extends GenericDAO<Product> {
    /**
     * access the product table to find the row by its name
     * @param product is the product witch are searching for
     * @return the find product if it is else null
     */
    public Product find(Product product) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM product WHERE `name`='"+product.getName()+"' AND `delete`=0";
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NullPointerException e) {
            return null;
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * gets the product table with "delete" = 0
     * @return array of all products available
     */
    public ArrayList getTable() {
        return (ArrayList) findAll();
    }
}

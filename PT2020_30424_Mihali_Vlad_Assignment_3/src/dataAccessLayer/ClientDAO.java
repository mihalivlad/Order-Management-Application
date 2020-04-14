package dataAccessLayer;

import connection.ConnectionFactory;
import model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * is a class that inheritance GenericDAO and access the table client in database
 * @author Mihali Vlad
 * @version 1.0
 * @since 1.0
 */
public class ClientDAO extends GenericDAO<Client> {
    /**
     * access the client table to find the row by its name
     * @param client is the client witch are searching for
     * @return the find client if it is else null
     */
    public Client find(Client client) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM `client` WHERE `name`='"+client.getName()+"' AND `delete`=0";
            System.out.println(query);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NullPointerException e) {
            return null;
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * gets the client table with "delete" = 0
     * @return array of all clients available
     */
    public ArrayList getTable() {
        return (ArrayList) findAll();
    }
}

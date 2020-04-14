package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that connects the app and the database.
 * @author Mihali Vlad
 * @version 1.0
 * @since 1.0
 */
public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/orderdb?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "admin";
    /**
     * connection is being created only once (single connection)
     * @since 1.0
     */
    private static ConnectionFactory singleInstance = new ConnectionFactory();
    /**
     * The constructor is private since the object is created once (singleInstance)
     * @since 1.0
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**it is private and used by the getConnection method
     * @return a new connection
     * @since 1.0
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * attribute singleInstance will create a connection
     * @return a new connection
     * @since 1.0
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }
    /**
     * trying to lose the connection given as parameter
     * @param connection which it will be close
     * @since 1.0
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }
    /**
     * trying to close the statement given as parameter
     * @param statement which it will be close
     * @since 1.0
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }
    /**
     * trying to close the resultSet given as parameter
     * @param resultSet which will be close
     * @since 1.0
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}

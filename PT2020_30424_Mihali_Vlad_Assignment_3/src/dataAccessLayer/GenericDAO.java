package dataAccessLayer;

import connection.ConnectionFactory;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * access database using general commands
 * @author Mihali Vlad
 * @version 1.0
 * @since 1.0
 */
public class GenericDAO<T>{
    protected static final Logger LOGGER = Logger.getLogger(GenericDAO.class.getName());
    /**
     * is a generic private field
     */
        private final Class<T> type;
    /**
     * is an empty constructor of GenericDAO that extract the general type
     * @since 1.0
     */
        public GenericDAO() {
            this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        }

    /**
     * automatically create a select query by a condition
     * @param field that is used to select
     * @return a string with a select query template
     */
    public String createSelectQuery(String field) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            sb.append(" * ");
            sb.append(" FROM `");
            sb.append(type.getSimpleName());
            sb.append("` WHERE " + field + " =?");
            return sb.toString();
        }

    /**
     * try to connect to database and finds all generic object in the table
     * @return list of all generic object in the generic table
     */
        public List<T> findAll() {
            // TODO:
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            String query = "SELECT * FROM `"+ type.getSimpleName()+"`";
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();

                return createObjects(resultSet);
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
            }
            return null;
        }

    /**
     * try to connect to database and find the row by its unique identifier
     * @param id is the primary key we are searching for
     * @return the object searched if it is possible else null
     */
        public T findById(int id) {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            String query = createSelectQuery("id");
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();

                return createObjects(resultSet).get(0);
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
            } finally {
                ConnectionFactory.close(resultSet);
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
            return null;
        }

    /**
     *try transform the result of a query into list of objects
     * @param resultSet is a result of a query
     * @return a list of generic objects
     */
        public List<T> createObjects(ResultSet resultSet) {
            List<T> list = new ArrayList<T>();

            try {
                if(resultSet.next() == false) {
                    return null;
                }else {
                    do {
                        T instance = type.newInstance();
                        for (Field field : type.getDeclaredFields()) {
                            Object value = resultSet.getObject(field.getName());
                            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                            Method method = propertyDescriptor.getWriteMethod();
                            if (field.getName().equals("delete")) {
                                if (value.equals(0)) {
                                    method.invoke(instance, false);
                                } else if (value.equals(1))
                                    method.invoke(instance, true);
                            } else {
                                method.invoke(instance, value);
                            }
                        }
                        list.add(instance);
                    } while (resultSet.next());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

    /**
     * gets all the fields of the generic object but skip first if it is true
     * @param skip it is set true if you want also the id else false
     * @return string format of the fields name
     */
        public String getFields(boolean skip){
            String str = "";
            for (Field field : type.getDeclaredFields()) {
                if(!skip) {
                    str += "`"+field.getName() + "`, ";
                }else{
                    skip = false;
                }
            }
            str = str.substring(0,str.length()-2);
            return str;
        }

    /**
     * calls getter of the field from the generic class
     * @param field is the field from we are getting the getter
     * @param t is the object from we are getting the getter
     * @return a string format of the return from the getter
     */
        public String invokeGetter(Field field, T t){
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                if(method.invoke(t).equals(false)){
                    return "0, ";
                }else if(method.invoke(t).equals(true)){
                    return "1, ";
                }else if(field.getType().getSimpleName().equals("String")){
                    return "\'" + method.invoke(t) + "\', ";
                }else{
                    return method.invoke(t)+", ";
                }
            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return "";
        }

    /**
     * try to connect to database and update by the parameter into the generic table
     * @param t is the object that will be update in the database
     * @return object that is update
     */
    public T update(T t) {
            Connection connection = null;
            PreparedStatement statement = null;
            String str="";
            String str2="UPDATE `"+type.getSimpleName()+"` SET ";
            boolean skip = true;
            for (Field field : type.getDeclaredFields()) {
                if(!skip) {
                    str2+="`" + field.getName() + "`= ";
                    str2+=invokeGetter(field, t);
                }else{
                    str=" WHERE `" + field.getName() + "`= "+invokeGetter(field, t).substring(0, invokeGetter(field, t).length()-2);
                    skip=false;
                }
            }
            str2=str2.substring(0, str2.length()-2);
            str2+=str;
            System.out.println(str2);
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(str2);
                statement.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }finally {
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
                return t;
            }
        }

    /**
     * try to connect to database and insert by the parameter into the generic table
     * @param t is the object that will be update in the database
     * @param skip is set true if you want to insert also the id else false
     * @return object that is inserted
     */
        public T insert(T t, boolean skip) {
            Connection connection = null;
            PreparedStatement statement = null;
            StringBuilder sb = new StringBuilder("INSERT INTO `"+type.getSimpleName()+"` (");
            sb.append(getFields(skip));
            sb.append(") VALUES (");
            for (Field field : type.getDeclaredFields()) {
                if(!skip) {
                    sb.append(invokeGetter(field, t));
                }else{
                    skip = false;
                }
            }
            sb.delete(sb.length()-2, sb.length());
            sb.append(")");
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(sb.toString());
                statement.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            } finally {
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
        return t;
        }
        }
}

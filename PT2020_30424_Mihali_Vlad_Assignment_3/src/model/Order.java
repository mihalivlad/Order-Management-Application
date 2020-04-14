package model;
/**
 * is a public reflation class for the order table in the database
 * @author Mihali Vlad
 * @since 1.0
 * @version 1.0
 */
public class Order {
    /**
     * is a private attribute that identifies unique record from Order (primary key)
     * @since 1.0
     */
    private int id;
    /**
     * is a private attribute that identifies the client (foreign key)
     * @since 1.0
     */
    private int idclient;
    /**
     * is a private attribute that identifies the product (foreign key)
     * @since 1.0
     */
    private int idproduct;

    /**
     * is an empty constructor of order class
     * @since 1.0
     */
    public Order() {
    }
    /**
     * is a constructor of order class
     * @param idclient is used to initialize idclient attribute
     * @param idproduct is used to initialize idproduct attribute
     * @since 1.0
     */
    public Order(int idclient, int idproduct) {
        this.idclient = idclient;
        this.idproduct = idproduct;
    }
    /**
     * is a constructor of order class
     * @param id is used to initialize id attribute
     * @param idclient is used to initialize idclient attribute
     * @param idproduct is used to initialize idproduct attribute
     * @since 1.0
     */
    public Order(int id, int idclient, int idproduct) {
        this.id = id;
        this.idclient = idclient;
        this.idproduct = idproduct;
    }
    /**
     * is the getter method for id attribute
     * @since 1.0
     * @return id attribute
     */
    public int getId() {
        return id;
    }
    /**
     * is the setter method for id attribute
     * @since 1.0
     * @param id is used to assign id attribute
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * is the getter method for idclient attribute
     * @since 1.0
     * @return idclient attribute
     */
    public int getIdclient() {
        return idclient;
    }
    /**
     * is the setter method for idclient attribute
     * @since 1.0
     * @param idClient is used to assign idclient attribute
     */
    public void setIdclient(int idClient) {
        this.idclient = idClient;
    }
    /**
     * is the getter method for idproduct attribute
     * @since 1.0
     * @return idproduct attribute
     */
    public int getIdproduct() {
        return idproduct;
    }
    /**
     * is the setter method for idproduct attribute
     * @since 1.0
     * @param idProduct is used to assign idproduct attribute
     */
    public void setIdproduct(int idProduct) {
        this.idproduct = idProduct;
    }
}

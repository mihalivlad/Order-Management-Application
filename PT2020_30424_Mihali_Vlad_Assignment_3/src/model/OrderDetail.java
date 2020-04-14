package model;
/**
 * is a public reflation class for the orderDetail table in the database
 * @author Mihali Vlad
 * @since 1.0
 * @version 1.0
 */
public class OrderDetail {
    /**
     * is a private attribute that identifies unique record from OrderDetail(primary key)
     * @since 1.0
     */
    private int id;

    /**
     * is a private attribute that
     * @since 1.0
     */
    private int quantity;
    /**
     * is a private attribute that takes true if the record client is "deleted"
     * @since 1.0
     */
    private boolean delete;
    /**
     * is an empty constructor of client class
     * @since 1.0
     */
    public OrderDetail() {
    }
    /**
     * is a constructor of OrderDetail class
     * @param id is used to initialize id attribute
     * @param quantity is used to initialize quantity attribute
     * @since 1.0
     */
    public OrderDetail(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.delete = false;
    }
    /**
     * is a constructor of OrderDetailclass
     * @param quantity is used to initialize quantity attribute
     * @since 1.0
     */
    public OrderDetail(int quantity) {
        this.quantity = quantity;
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
     * is the getter method for quantity attribute
     * @since 1.0
     * @return quantity attribute
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * is the setter method for quantity attribute
     * @since 1.0
     * @param quantity is used to assign quantity attribute
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * is the getter method for delete attribute
     * @since 1.0
     * @return delete attribute
     */
    public boolean isDelete() {
        return delete;
    }
    /**
     * is the setter method for delete attribute
     * @since 1.0
     * @param delete is used to assign delete attribute
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}

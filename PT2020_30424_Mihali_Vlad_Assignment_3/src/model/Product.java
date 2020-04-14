package model;
/**
 * is a public reflation class for the product table in the database
 * @author Mihali Vlad
 * @since 1.0
 * @version 1.0
 */
public class Product {
    /**
     * is a private attribute that identifies unique record from Product (primary key)
     * @since 1.0
     */
    private int id;
    /**
     * is a private attribute that is the name of the product
     * @since 1.0
     */
    private String name;
    /**
     * is a private attribute that represents stock of the product
     * @since 1.0
     */
    private int quantity;
    /**
     * is a private attribute that represents the price of the product
     * @since 1.0
     */
    private float price;
    /**
     * is a private attribute that takes true if the record client is "deleted"
     * @since 1.0
     */
    private boolean delete;
    /**
     * is an empty constructor of product class
     * @since 1.0
     */
    public Product() {
    }
    /**
     * is a constructor of Product class
     * @param name is used to initialize name attribute
     * @since 1.0
     */
    public Product(String name) {
        this.name = name;
        delete = true;
    }
    /**
     * is a constructor of Product class
     * @param name is used to initialize name attribute
     * @param quantity is used to initialize quantity attribute
     * @param price is used to initialize price attribute
     * @since 1.0
     */
    public Product(String name, int quantity, float price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    /**
     * is a constructor of Product class
     * @param id is used to initialize id attribute
     * @param name is used to initialize name attribute
     * @param quantity is used to initialize quantity attribute
     * @param price is used to initialize price attribute
     * @since 1.0
     */
    public Product(int id, String name, int quantity, float price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.delete = false;
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
     * is the getter method for name attribute
     * @since 1.0
     * @return name attribute
     */
    public String getName() {
        return name;
    }
    /**
     * is the setter method for name attribute
     * @since 1.0
     * @param name is used to assign name attribute
     */
    public void setName(String name) {
        this.name = name;
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
     * is the getter method for price attribute
     * @since 1.0
     * @return price attribute
     */
    public float getPrice() {
        return price;
    }
    /**
     * is the setter method for price attribute
     * @since 1.0
     * @param price is used to assign price attribute
     */
    public void setPrice(float price) {
        this.price = price;
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

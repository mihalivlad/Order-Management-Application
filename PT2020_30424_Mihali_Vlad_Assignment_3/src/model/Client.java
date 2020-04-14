package model;

/**
 * is a public reflation class for the client table in the database
 * @author Mihali Vlad
 * @since 1.0
 * @version 1.0
 */
public class Client {
    /**
     * is a private attribute that identifies unique record from Client (primary key)
     * @since 1.0
     */
    private int id;
    /**
     * is the first name and last name of the client
     * @since 1.0
     */
    private String name;
    /**
     * is the address of the client
     * @since 1.0
     */
    private String address;
    /**
     * is a private attribute that takes true if the record client is "deleted"
     * @since 1.0
     */
    private boolean delete;

    /**
     * is an empty constructor of client class
     * @since 1.0
     */
    public Client() {
    }

    /**
     * is a constructor of client class
     * @param name is used to initialize name attribute
     * @since 1.0
     */
    public Client(String name) {
        this.name = name;
        delete = true;
    }

    /**
     * is a constructor of client class
     * @param name is used to initialize name attribute
     * @param address is used to initialize address attribute
     * @since 1.0
     */
    public Client(String name, String address) {
        this.name = name;
        this.address = address;
        this.delete = false;
    }

    /**
     * is a constructor of client class
     * @param id is used to initialize id attribute
     * @param name is used to initialize name attribute
     * @param address is used to initialize address attribute
     * @since 1.0
     */
    public Client(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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
     * is the getter method for address attribute
     * @since 1.0
     * @return address attribute
     */
    public String getAddress() {
        return address;
    }

    /**
     * is the setter method for address attribute
     * @since 1.0
     * @param address is used to assign address attribute
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * is the getter method for delete attribute
     * @since 1.0
     * @return address delete
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * is the setter method for delete attribute
     * @since 1.0
     * @param delete is used to assign address attribute
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}

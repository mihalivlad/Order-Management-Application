package presentation;

import businessLayer.ValidClient;
import businessLayer.ValidOrder;
import businessLayer.ValidProduct;
import model.Client;
import model.OrderDetail;
import model.Product;
import java.io.File;
import java.util.Scanner;

/**
 * it will parse the input given by argument as String and pass param to other classes
 * @author Mihali Vlad
 * @version 1.0
 * @since 1.0
 */
public class ParseInput {

    /**
     * is a private presentation which will help the class to output
     * @since 1.0
     */
    private Presentation output = new Presentation();

    /**
     * is a constructor of ParseInput class
     * @param fileName is the name of the input file
     * @since 1.0
     */
    public ParseInput(String fileName) {
        try {
            File file = new File(fileName);
            Scanner in = new Scanner(file);
            String str;
            while(in.hasNextLine()) {
                str = in.nextLine();
                System.out.println(str);
                String[] arrOfStr = str.split("[,: ]+");
                if (arrOfStr[1].equals("client") || arrOfStr[1].equals("Client")) {
                    commandClient(arrOfStr);
                } else if (arrOfStr[1].equals("product") || arrOfStr[1].equals("Product")) {
                    commandProduct(arrOfStr);
                } else if (arrOfStr[1].equals("order") || arrOfStr[1].equals("Order") || arrOfStr[0].equals("order") || arrOfStr[0].equals("Order")) {
                    commandOrder(arrOfStr);
                } else {
                    output.errorInput();
                }
            }
            in.close();
        } catch (Exception e) {
            output.errorInput();
            e.printStackTrace();
        }
    }

    /**
     * is a private method that will validate a string as a name
     * @param name that will be validate
     * @return true if it is valid
     * @since 1.0
     */
    private boolean validateName(String name) {
        if (name.matches("[ a-zA-Z]+") == false) {
            output.errorName();
            return false;
        }
        if (name == "") {
            output.errorName();
            return false;
        }
        return true;
    }

    /**
     * is a private method that validates a string as unsigned integer and returns it
     * @param str that will be validate
     * @return extract positive integer if it is posible else -1
     */
    private int validateUInt(String str){
        int myint = -1;
        try {
            myint = Integer.parseUnsignedInt(str);
        }catch (Exception e) {
            output.errorUInt();
        }
        return myint;
    }

    /**
     * is a private method that is used by the constructor and it will process a command
     * for table client such as insert, delete and report
     * @param client is an array of date representing clients attributes
     */
    private void commandClient(String[] client){

        if(client[0].equals("Insert")) {
            if(validateName(client[2]+client[3])) {
                Client c = new Client(client[2]+" "+client[3], client[4]);
                ValidClient validClient = new ValidClient(c);
                validClient.insert();
            }
        } else if(client[0].equals("Delete")){
            if(validateName(client[2] + client[3])) {
                Client c = new Client(client[2] +" "+ client[3]);
                ValidClient validClient = new ValidClient(c);
                validClient.delete();
            }
        } else if(client[0].equals("Report")){
            ValidClient validClient = new ValidClient();
            validClient.report();
        }
    }

    /**
     * is a private method that validates a string as positive float number and returns it
     * @param str that will be validate
     * @return extract positive float number if it is posible else -1
     */
    private float validateFloat(String str) {
        float myfloat = -1;
        try {
            myfloat = Float.parseFloat(str);
        }catch (Exception e) {
            output.errorFloat();
        }
        return myfloat;
    }
    /**
     * is a private method that is used by the constructor and it will process a command
     * for table product such as insert, delete and report
     * @param product is an array of date representing products attributes
     */
    private void commandProduct(String[] product){
        if(product[0].equals("Insert")) {
            int quantity = validateUInt(product[3]);
            float price = validateFloat(product[4]);
            if(validateName(product[2]) && quantity != -1 && price != -1) {
                Product p = new Product(product[2], quantity, price);
                ValidProduct validProduct = new ValidProduct(p);
                validProduct.insert();
            }
        } else if(product[0].equals("Delete")){
            Product p = new Product(product[2]);
            ValidProduct validProduct = new ValidProduct(p);
            validProduct.delete();
        }else if(product[0].equals("Report")){
            ValidProduct validProduct = new ValidProduct();
            validProduct.report();
        }

    }
    /**
     * is a private method that is used by the constructor and it will process a command
     * for table order such as insert, delete and report
     * @param order is an array of date representing orders attributes
     */
    private void commandOrder(String[] order){
        if(order[0].equals("Report")) {
            ValidOrder validOrder = new ValidOrder();
            validOrder.report();
            return;
        }
        int quantity = validateUInt(order[4]);
        if(validateName(order[1]+" "+order[2]) && validateName(order[3]) && quantity != -1) {
            Client c = new Client(order[1] +" "+ order[2]);
            Product p = new Product(order[3]);
            OrderDetail o = new OrderDetail(quantity);
            ValidOrder validOrder = new ValidOrder(c, p, o);
            validOrder.insert();
        }
    }
}

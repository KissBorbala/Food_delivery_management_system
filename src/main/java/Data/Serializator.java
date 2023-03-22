package Data;

import Business.DeliveryService;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Serializator {

    private static final String PATH = "deliveryService.txt";

    public static void serializeDeliveryService(DeliveryService ds){
        try {
            FileOutputStream file = new FileOutputStream(PATH);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(ds);
            out.close();
            file.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }



































    public static DeliveryService deserializeDeliveryService(){
        DeliveryService ds = null;
        try {
            System.out.println("Searching for serialized data");
            FileInputStream file = new FileInputStream(PATH);
            ObjectInputStream in = new ObjectInputStream(file);
            ds = (DeliveryService) in.readObject();
            System.out.println("success");
            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File not found, creating new class");
            ds = new DeliveryService();
        }

        return ds;
    }

}

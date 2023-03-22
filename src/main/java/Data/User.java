package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    public static Object Status;

    enum Status {
        ADMINISTRATOR,
        EMPLOYEE,
        CLIENT
    }

    private String username;
    private String password;
    private int status;
    private int id;
    private int idCount = 0;
    private List<Integer> bills;

    public User(String username, String password, int status) {

        this.username = username;
        this.password = password;
        this.status = status;
        id = idCount++;
        if (status == 2) {
            bills = new ArrayList<Integer>();
        }
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int getStatus() {
        return status;
    }
    public int getId() {return id; }

    public void addBill(int price) {
        bills.add(price);
    }

    public int getBillsGreater(int price) {
        int result = 0;
        for (Integer n: bills) {
            if (n > price) {
                result++;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return 31 * username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return ((User) obj).getUsername().equals(username);
    }
}

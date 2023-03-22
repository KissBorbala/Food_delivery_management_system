package Business;

import java.io.Serializable;

public class Order implements Serializable {

    private int orderId;
    private int clientId;
    private String date;
    private String time;

    public Order(int clientId, int orderId, String date, String time) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return orderId;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf("" + orderId + clientId);
    }

    public String getTime() {
        return time;
    }
    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Order) obj).getId() == orderId;
    }
}

package Presentation;

import Business.Order;

public interface Observer {

    void update(Order order);

}

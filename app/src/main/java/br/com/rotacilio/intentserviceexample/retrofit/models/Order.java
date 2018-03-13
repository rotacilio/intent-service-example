package br.com.rotacilio.intentserviceexample.retrofit.models;

import java.util.List;

/**
 * Created by rodrigo.lins on 13/03/2018.
 */

public class Order {

    private List<OrderItem> orders;

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orders=" + orders.size() +
                '}';
    }
}

package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<OrderItem> orderItems;

    public Order() {
        this.orderItems = new ArrayList<>();
    }
}

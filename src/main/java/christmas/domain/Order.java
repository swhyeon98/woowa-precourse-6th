package christmas.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {

    private final List<OrderItem> orderItems;

    public Order() {
        this.orderItems = new ArrayList<>();
    }

    public void addOrderItems(List<OrderItem> newOrderItems) {
        validateNoDuplicate(newOrderItems);
        orderItems.addAll(newOrderItems);
    }

    private void validateNoDuplicate(List<OrderItem> newOrderItems) {
        long uniqueCount = newOrderItems.stream()
                .map(orderItem -> orderItem.getMenu().getName())
                .distinct()
                .count();
        if (uniqueCount != newOrderItems.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}

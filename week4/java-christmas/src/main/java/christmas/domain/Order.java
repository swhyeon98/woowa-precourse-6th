package christmas.domain;

import christmas.repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<OrderItem> orderItems;

    public Order() {
        this.orderItems = new ArrayList<>();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(OrderItem newOrderItem) {
        validateNoDuplicate(newOrderItem);
        this.orderItems.add(newOrderItem);
    }

    public int calculateTotalBeforeDiscount(MenuRepository menuRepository) {
        return orderItems.stream()
                .mapToInt(item -> item.calculateItemPrice(menuRepository))
                .sum();
    }

    private void validateNoDuplicate(OrderItem newOrderItem) {
        if (isDuplicate(newOrderItem)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isDuplicate(OrderItem newOrderItem) {
        return orderItems.stream()
                .anyMatch(existingItem -> existingItem.getMenuName()
                        .equals(newOrderItem.getMenuName()));
    }
}

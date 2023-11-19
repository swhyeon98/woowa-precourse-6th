package christmas.service;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.repository.MenuRepository;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OrderService {

    private static final Pattern ORDER_ITEM_PATTERN = Pattern.compile("^(.+)\\-(\\d+)$");

    private final MenuRepository menuRepository;

    public OrderService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Order createOrder(String input) {
        Order order = new Order();
        List<OrderItem> orderItems = splitAndToOrderList(input);
        for (OrderItem item : orderItems) {
            order.addOrderItem(item);
        }
        return order;
    }

    public int calculateTotalBeforeDiscount(Order order) {
        return order.calculateTotalBeforeDiscount(menuRepository);
    }

    public String determineGiftItem(Order order) {
        int totalAmount = calculateTotalBeforeDiscount(order);
        String giftItem = "없음";
        if (totalAmount >= 120000) {
            giftItem = "샴페인";
        }
        return giftItem;
    }

    private List<OrderItem> splitAndToOrderList(String input) {
        return Arrays.stream(input.split(","))
                .map(this::toOrderItem)
                .collect(Collectors.toList());
    }

    private OrderItem toOrderItem(String itemInput) {
        Matcher matcher = ORDER_ITEM_PATTERN.matcher(itemInput.trim());

        if (!matcher.matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        String menuName = matcher.group(1).trim();
        int quantity = parseQuantity(matcher.group(2).trim());

        return new OrderItem(menuName, quantity);
    }

    private int parseQuantity(String quantityStr) {
        try {
            return Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 수량은 숫자여야 합니다. 다시 입력해 주세요.");
        }
    }
}

package christmas.domain;

import christmas.repository.MenuRepository;

public class OrderItem {

    private final String menuName;
    private final int quantity;


    public OrderItem(String menuName, int quantity) {
        this.menuName = menuName;
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int calculateItemPrice(MenuRepository menuRepository) {
        return menuRepository.findPriceByName(menuName) * quantity;
    }

    private void validateQuantity(int quantity) {
        isQuantityOverLimit(quantity);
        isQuantityUnderLimit(quantity);
    }

    private void isQuantityOverLimit(int quantity) {
        if (quantity > 20) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
        }
    }

    private void isQuantityUnderLimit(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}

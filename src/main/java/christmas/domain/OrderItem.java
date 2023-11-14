package christmas.domain;

public class OrderItem {

    private final Menu menu;
    private final int quantity;


    public OrderItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public int calculateAmount() {
        return menu.getPrice() * quantity;
    }
}

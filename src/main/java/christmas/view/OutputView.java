package christmas.view;

import christmas.domain.Category;
import christmas.domain.Menu;
import christmas.domain.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printMenu(List<Menu> menus) {
        System.out.println("<<메뉴>>\n");

        for (Category category : Category.values()) {
            printCategoryMenu(menus, category);
        }
    }

    private void printCategoryMenu(List<Menu> menus, Category category) {
        List<Menu> filteredMenu = menus.stream()
                .filter(menu -> menu.getCategory() == category)
                .collect(Collectors.toList());

        if (!filteredMenu.isEmpty()) {
            System.out.println("<" + category.getDisplayName() + ">");
            for (Menu menu : filteredMenu) {
                System.out.println(menu.getName() + " (" + formatPrice(menu.getPrice()) + ")");
            }
            System.out.println();
        }
    }

    private String formatPrice(int price) {
        return String.format("%,d원", price);
    }

    public void printOrderItems(List<OrderItem> orderedItems) {
        System.out.println("<주문 메뉴>");
        orderedItems.forEach(System.out::println);
    }

    public void printTotalBeforeDiscount(int totalBeforeDiscount) {
        System.out.println("\n<할인 전 총주문 금액>\n" + formatPrice(totalBeforeDiscount));
    }

    public void printGiftItem(String giftItem) {
        System.out.println("\n<증정 메뉴>\n" + giftItem);
    }

    public void printBenefits(String benefits) {
        System.out.println("\n<혜택 내역>\n" + benefits);
    }

    public void printTotalDiscount(int totalDiscount) {
        System.out.println("\n<총혜택 금액>\n" + formatPrice(-totalDiscount));
    }

    public void printTotalAfterDiscount(int totalAfterDiscount) {
        System.out.println("\n<할인 후 예상 결제 금액>\n" + formatPrice(totalAfterDiscount));
    }

    public void printEventBadge(String eventBadge) {
        System.out.println("\n<12월 이벤트 배지>\n" + eventBadge);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}

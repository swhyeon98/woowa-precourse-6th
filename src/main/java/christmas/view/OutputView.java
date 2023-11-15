package christmas.view;

import christmas.domain.Category;
import christmas.domain.Menu;

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
}

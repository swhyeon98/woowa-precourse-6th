package christmas.domain.discount;

import christmas.domain.Category;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.repository.MenuRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscountPolicy implements DiscountPolicy {

    private final MenuRepository menuRepository;

    public WeekdayDiscountPolicy(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public int discount(Order order, LocalDate date) {
        if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
            return calculateDiscount(order, Category.DESSERT);
        }
        return 0;
    }

    private int calculateDiscount(Order order, Category category) {
        return order.getOrderItems().stream()
                .filter(item -> {
                    Menu menu = menuRepository.findByName(item.getMenuName()).orElse(null);
                    return menu != null && menu.getCategory() == category;
                })
                .mapToInt(item -> 2023 * item.getQuantity())
                .sum();
    }
}

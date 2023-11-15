package christmas.domain.discount;

import christmas.domain.Order;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private final Set<Integer> specialDays;

    public SpecialDiscountPolicy() {
        this.specialDays = new HashSet<>();
        specialDays.add(3);
        specialDays.add(10);
        specialDays.add(17);
        specialDays.add(24);
        specialDays.add(25);
        specialDays.add(31);
    }

    @Override
    public int discount(Order order, LocalDate date) {
        if (specialDays.contains(date.getDayOfMonth())) {
            return 1000;
        }
        return 0;
    }

    @Override
    public String getDescription() {
        return "특별 할인";
    }
}

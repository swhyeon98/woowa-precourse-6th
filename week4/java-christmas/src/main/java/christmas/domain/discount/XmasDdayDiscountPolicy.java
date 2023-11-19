package christmas.domain.discount;

import christmas.domain.Order;

import java.time.LocalDate;
import java.time.Month;

public class XmasDdayDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount(Order order, LocalDate date) {
        if (date.getMonth() == Month.DECEMBER && date.getDayOfMonth() <= 25) {
            return 1000 + (date.getDayOfMonth() - 1) * 100;
        }
        return 0;
    }

    @Override
    public String getDescription() {
        return "크리스마스 디데이 할인";
    }
}

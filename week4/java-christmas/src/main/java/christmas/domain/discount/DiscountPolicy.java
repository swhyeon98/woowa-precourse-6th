package christmas.domain.discount;

import christmas.domain.Order;

import java.time.LocalDate;

public interface DiscountPolicy {

    int discount(Order order, LocalDate date);
    String getDescription();
}

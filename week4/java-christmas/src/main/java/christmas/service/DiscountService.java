package christmas.service;

import christmas.domain.Order;
import christmas.domain.discount.DiscountPolicy;

import java.time.LocalDate;
import java.util.List;

public class DiscountService {

    private final List<DiscountPolicy> discountPolicies;

    public DiscountService(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public int calculateTotalDiscount(Order order, LocalDate date) {
        return discountPolicies.stream()
                .mapToInt(policy -> policy.discount(order, date))
                .sum();
    }

    public String calculateBenefits(Order order, LocalDate date) {
        StringBuilder benefits = new StringBuilder();

        for (DiscountPolicy policy : discountPolicies) {
            int discountAmount = policy.discount(order, date);
            if (discountAmount > 0) {
                benefits.append(policy.getDescription()).append(": -").append(discountAmount).append("원\n");
            }
        }

        if (benefits.length() == 0) {
            benefits.append("없음");
        }

        return benefits.toString();
    }

    public String calculateEventBadge(Order order, LocalDate date) {
        int totalBenefit = calculateTotalDiscount(order, date);

        if (totalBenefit >= 20000) return "산타";
        if (totalBenefit >= 10000) return "트리";
        if (totalBenefit >= 5000) return "별";

        return "없음";
    }
}

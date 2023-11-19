package christmas.domain.discount;

import christmas.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountPolicyTest {

    private DiscountPolicy discountPolicy;
    private Order order;

    @BeforeEach
    void setUp() {
        discountPolicy = new SpecialDiscountPolicy();
        order = new Order();
    }

    @Test
    @DisplayName("특별 할인 적용")
    void specialDayDiscount() {
        //given
        LocalDate specialDate = LocalDate.of(2023, Month.DECEMBER, 25);

        //when
        int discount = discountPolicy.discount(order, specialDate);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("특별 할인 미적용")
    void notSpecialDayNoDiscount(){
        //given
        LocalDate specialDate = LocalDate.of(2023, Month.DECEMBER, 1);

        //when
        int discount = discountPolicy.discount(order, specialDate);

        //then
        assertThat(discount).isEqualTo(0);
    }

}
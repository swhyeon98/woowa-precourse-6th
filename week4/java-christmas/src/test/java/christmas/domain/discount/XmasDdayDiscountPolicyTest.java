package christmas.domain.discount;

import christmas.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

class XmasDdayDiscountPolicyTest {

    private DiscountPolicy discountPolicy;
    private Order order;

    //시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)

    @BeforeEach
    void setUp() {
        discountPolicy = new XmasDdayDiscountPolicy();
        order = new Order();
    }

    @Test
    @DisplayName("12월 1일 할인 금액 테스트")
    void discountDecemberFirst(){
        //given
        LocalDate date = LocalDate.of(2023, Month.DECEMBER, 1);

        //when
        int discount = discountPolicy.discount(order, date);

        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("12월 2일 할인 금액 테스트")
    void discountDecemberSecond(){
        //given
        LocalDate date = LocalDate.of(2023, Month.DECEMBER, 2);

        //when
        int discount = discountPolicy.discount(order, date);

        //then
        Assertions.assertThat(discount).isEqualTo(1100);
    }

    @Test
    @DisplayName("12월 25일 할인 금액 테스트")
    void discountDDay(){
        //given
        LocalDate date = LocalDate.of(2023, Month.DECEMBER, 25);

        //when
        int discount = discountPolicy.discount(order, date);

        //then
        Assertions.assertThat(discount).isEqualTo(3400);
    }

    @Test
    @DisplayName("12월 26일 할인 금액 테스트")
    void discountOverDDay(){
        //given
        LocalDate date = LocalDate.of(2023, Month.DECEMBER, 26);

        //when
        int discount = discountPolicy.discount(order, date);

        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }

}
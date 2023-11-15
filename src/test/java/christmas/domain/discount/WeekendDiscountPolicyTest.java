package christmas.domain.discount;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class WeekendDiscountPolicyTest {

    private MenuRepository menuRepository;
    private DiscountPolicy discountPolicy;

    @BeforeEach
    void setUp() {
        menuRepository = new MenuRepository();
        discountPolicy = new WeekendDiscountPolicy(menuRepository);
    }

    @Test
    @DisplayName("주말일 때, 메인 메뉴 할인 적용")
    public void weekendMainMenuDiscount() {
        //given
        Order order = new Order();
        order.addOrderItem(new OrderItem("티본스테이크", 1));
        LocalDate friday = LocalDate.of(2023, Month.DECEMBER, 1);

        //when
        int discount = discountPolicy.discount(order, friday);

        //then
        assertThat(discount).isEqualTo(2023);
    }

    @Test
    @DisplayName("주말이지만 디저트 메뉴일 때, 할인 미적용")
    public void weekendDessertMenuNoDiscount() {
        //given
        Order order = new Order();
        order.addOrderItem(new OrderItem("초코케이크", 1));
        LocalDate friday = LocalDate.of(2023, Month.DECEMBER, 1);

        //when
        int discount = discountPolicy.discount(order, friday);

        //then
        assertThat(discount).isEqualTo(0);
    }

    @Test
    @DisplayName("주말이 아닐 때, 할인 미적용")
    public void notWeekDay() {
        //given
        Order order = new Order();
        order.addOrderItem(new OrderItem("티본스테이크", 1));
        LocalDate monday = LocalDate.of(2023, Month.DECEMBER, 4);

        //when
        int discount = discountPolicy.discount(order, monday);

        //then
        assertThat(discount).isEqualTo(0);
    }

}
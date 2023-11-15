package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    private Order order;
    private OrderItem orderItem1;
    private OrderItem orderItem2;
    private OrderItem orderItem3;
    private OrderItem orderItem4;

    @BeforeEach
    void setUp() {
        order = new Order();
        orderItem1 = new OrderItem("티본스테이크", 1);
        orderItem2 = new OrderItem("바비큐립", 1);
        orderItem3 = new OrderItem("초코케이크", 2);
        orderItem4 = new OrderItem("제로콜라", 1);
    }

    @Test
    @DisplayName("정상 주문")
    public void order(){
        //when
        order.addOrderItem(orderItem1);
        order.addOrderItem(orderItem2);
        order.addOrderItem(orderItem3);
        order.addOrderItem(orderItem4);

        //then
        Assertions.assertThat(order.getOrderItems())
                .containsExactlyInAnyOrder(orderItem1, orderItem2, orderItem3, orderItem4);
    }
}
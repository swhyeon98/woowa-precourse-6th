package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderItemTest {

    @Test
    @DisplayName("정상적인 OrderItem 생성")
    public void createOrderItem() {
        //given
        Menu menu = new Menu("양송이수프", 8000);
        int quantity = 1;

        //when
        OrderItem orderItem = new OrderItem(menu, quantity);

        //then
        assertThat(orderItem.getMenu()).isEqualTo(menu);
        assertThat(orderItem.getQuantity()).isEqualTo(quantity);
    }

    @DisplayName("주문 수량 초과 대한 예외 처리")
    @ValueSource(strings = {"21", "200", "32"})
    @ParameterizedTest
    public void overQuantity(String input) {
        //given
        Menu menu = new Menu("양송이수프", 8000);
        int quantity = Integer.parseInt(input);

        //then
        assertThatThrownBy(() -> new OrderItem(menu, quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
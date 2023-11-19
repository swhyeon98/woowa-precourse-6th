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
        String menuName = "양송이수프";
        int quantity = 1;

        //when
        OrderItem orderItem = new OrderItem("양송이수프", quantity);

        //then
        assertThat(orderItem.getMenuName()).isEqualTo(menuName);
        assertThat(orderItem.getQuantity()).isEqualTo(quantity);
    }

    @DisplayName("주문 수량 초과 대한 예외 처리")
    @ValueSource(strings = {"21", "200", "32"})
    @ParameterizedTest
    public void overQuantity(String input) {
        //given
        String menuName = "양송이수프";
        int quantity = Integer.parseInt(input);

        //then
        assertThatThrownBy(() -> new OrderItem("양송이수프", quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 수량 미만 대한 예외 처리")
    @ValueSource(strings = {"0", "-1", "-200"})
    @ParameterizedTest
    public void underQuantity(String input) {
        //given
        String menuName = "양송이수프";
        int quantity = Integer.parseInt(input);

        //then
        assertThatThrownBy(() -> new OrderItem("양송이수프", quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
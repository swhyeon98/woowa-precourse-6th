package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderItemTest {

    @Test
    @DisplayName("정상적인 OrderItem 생성")
    public void createOrderItem(){
        //given
        Menu menu = new Menu("양송이수프", 8000);
        int quantity = 1;

        //when
        OrderItem orderItem = new OrderItem(menu, quantity);

        //then
        assertThat(orderItem.getMenu()).isEqualTo(menu);
        assertThat(orderItem.getQuantity()).isEqualTo(quantity);
    }
}
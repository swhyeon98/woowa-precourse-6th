package christmas;

import christmas.controller.ChristmasController;
import christmas.domain.discount.DiscountPolicy;
import christmas.domain.discount.SpecialDiscountPolicy;
import christmas.domain.discount.WeekdayDiscountPolicy;
import christmas.domain.discount.WeekendDiscountPolicy;
import christmas.domain.discount.XmasDdayDiscountPolicy;
import christmas.repository.MenuRepository;
import christmas.service.DiscountService;
import christmas.service.OrderService;
import christmas.view.ChristmasView;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        MenuRepository menuRepository = new MenuRepository();
        List<DiscountPolicy> discountPolicies = Arrays.asList(new XmasDdayDiscountPolicy(),
                new WeekdayDiscountPolicy(menuRepository),
                new WeekendDiscountPolicy(menuRepository),
                new SpecialDiscountPolicy());

        OrderService orderService = new OrderService(menuRepository);
        DiscountService discountService = new DiscountService(discountPolicies);

        ChristmasView christmasView = new ChristmasView(new InputView(), new OutputView());

        ChristmasController controller = new ChristmasController(orderService, discountService, christmasView);

        controller.start();
    }
}

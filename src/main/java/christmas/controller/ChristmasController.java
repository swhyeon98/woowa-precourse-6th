package christmas.controller;

import christmas.domain.Order;
import christmas.repository.MenuRepository;
import christmas.service.DiscountService;
import christmas.service.OrderService;
import christmas.view.ChristmasView;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;

public class ChristmasController {

    private final OrderService orderService;
    private final DiscountService discountService;
    private final ChristmasView christmasView;

    public ChristmasController(OrderService orderService, DiscountService discountService, ChristmasView christmasView) {
        this.orderService = orderService;
        this.discountService = discountService;
        this.christmasView = christmasView;
    }

    public void start() {
        christmasView.printWelcomeMessage();
        LocalDate orderDate = readValidDate();

        christmasView.printMenu(new MenuRepository().findAll());
        String menuInput = christmasView.readMenu();

        Order order = validate(menuInput);

        int totalBeforeDiscount = orderService.calculateTotalBeforeDiscount(order);
        String giftItem = orderService.determineGiftItem(order);
        int totalDiscount = discountService.calculateTotalDiscount(order, orderDate);
        String benefits = discountService.calculateBenefits(order, orderDate);
        String eventBadge = discountService.calculateEventBadge(order, orderDate);
        int totalAfterDiscount = totalBeforeDiscount - totalDiscount;

        christmasView.printOrderItems(order.getOrderItems());
        christmasView.printTotalBeforeDiscount(totalBeforeDiscount);
        christmasView.printGiftItem(giftItem);
        christmasView.printBenefits(benefits);
        christmasView.printTotalDiscount(totalDiscount);
        christmasView.printTotalAfterDiscount(totalAfterDiscount);
        christmasView.printEventBadge(eventBadge);
    }

    public LocalDate readValidDate() {
        while (true) {
            try {
                int date = christmasView.readDate();
                return LocalDate.of(2023, Month.DECEMBER, date);
            } catch (DateTimeParseException | NumberFormatException e) {
                christmasView.printMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }
    }

    public Order validate(String input) {
        while (true) {
            try {
                return orderService.createOrder(input);
            } catch (IllegalArgumentException e) {
                christmasView.printMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                return orderService.createOrder(christmasView.readMenu());
            }
        }
    }
}

package christmas.view;

import christmas.domain.Menu;

import java.util.List;

public class ChristmasView {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasView(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int readDate() {
        return inputView.readDate();
    }

    public String readMenu() {
        return inputView.readMenu();
    }

    public void printWelcomeMessage() {
        outputView.printWelcomeMessage();
    }

    public void printMenu(List<Menu> menus) {
        outputView.printMenu(menus);
    }

    public void printOrderItems(List<String> orderedItems) {
        outputView.printOrderItems(orderedItems);
    }

    public void printTotalBeforeDiscount(int totalBeforeDiscount) {
        outputView.printTotalBeforeDiscount(totalBeforeDiscount);
    }

    public void printGiftItem(String giftItem) {
        outputView.printGiftItem(giftItem);
    }

    public void printBenefits(String benefits) {
        outputView.printBenefits(benefits);
    }

    public void printTotalDiscount(int totalDiscount) {
        outputView.printTotalDiscount(totalDiscount);
    }

    public void printTotalAfterDiscount(int totalAfterDiscount) {
        outputView.printTotalAfterDiscount(totalAfterDiscount);
    }

    public void printEventBadge(String eventBadge) {
        outputView.printEventBadge(eventBadge);
    }
}

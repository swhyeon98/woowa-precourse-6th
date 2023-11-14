package christmas.domain;

import java.util.Map;

public class MenuBoard {

    private final Map<String, Menu> menus;

    public MenuBoard(Map<String, Menu> menus) {
        this.menus = menus;
    }

    private void init() {
        addMenu(new Menu("양송이수프", 6_000));
        addMenu(new Menu("타파스", 5_500));
        addMenu(new Menu("시저샐러드", 8_000));
        addMenu(new Menu("티본스테이크", 55_000));
        addMenu(new Menu("바비큐립", 54_000));
        addMenu(new Menu("해산물파스타", 35_000));
        addMenu(new Menu("크리스마스파스타", 25_000));
        addMenu(new Menu("초코케이크", 15_000));
        addMenu(new Menu("아이스크림", 5_000));
        addMenu(new Menu("제로콜라", 3_000));
        addMenu(new Menu("레드와인", 60_000));
        addMenu(new Menu("샴페인", 25_000));
    }

    private void addMenu(Menu menu) {
        menus.put(menu.getName(), menu);
    }
}

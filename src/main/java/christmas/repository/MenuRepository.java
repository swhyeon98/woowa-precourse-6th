package christmas.repository;

import christmas.domain.Category;
import christmas.domain.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MenuRepository {

    private final List<Menu> menus = new ArrayList<>();

    public MenuRepository() {
        init();
    }

    private void init() {
        menus.add(new Menu("양송이수프", 6_000, Category.APPETIZER));
        menus.add(new Menu("타파스", 5_500, Category.APPETIZER));
        menus.add(new Menu("시저샐러드", 8_000, Category.APPETIZER));
        menus.add(new Menu("티본스테이크", 55_000, Category.MAIN));
        menus.add(new Menu("바비큐립", 54_000, Category.MAIN));
        menus.add(new Menu("해산물파스타", 35_000, Category.MAIN));
        menus.add(new Menu("크리스마스파스타", 25_000, Category.MAIN));
        menus.add(new Menu("초코케이크", 15_000, Category.DESSERT));
        menus.add(new Menu("아이스크림", 5_000, Category.DESSERT));
        menus.add(new Menu("제로콜라", 3_000, Category.DRINK));
        menus.add(new Menu("레드와인", 60_000, Category.DRINK));
        menus.add(new Menu("샴페인", 25_000, Category.DRINK));
    }

    public List<Menu> findAll() {
        return Collections.unmodifiableList(menus);
    }

    public Optional<Menu> findByName(String name) {
        return menus.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}

package christmas.repository;

import christmas.domain.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MenuRepositoryTest {

    private MenuRepository menuRepository;

    @BeforeEach
    void setUp() {
        menuRepository = new MenuRepository();
    }

    @ParameterizedTest
    @DisplayName("메뉴 이름으로 메뉴 찾기")
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타",
            "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라", "레드와인", "샴페인"})
    void findByAllMenu(String menuNames) {
        //when
        Optional<Menu> result = menuRepository.findByName(menuNames);

        //then
        assertThat(result.get().getName()).isEqualTo(menuNames);
    }

    @Test
    @DisplayName("존재하지 않는 메뉴 이름으로 찾을 때 빈 결과 반환")
    void findByNameEmpty() {
        // given
        String menuName = "없는 메뉴";

        // when
        Optional<Menu> result = menuRepository.findByName(menuName);

        // then
        assertThat(result).isEmpty();
    }

}
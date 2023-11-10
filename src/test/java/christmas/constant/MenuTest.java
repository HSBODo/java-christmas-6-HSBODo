package christmas.constant;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class MenuTest {

    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크"})
    @DisplayName("메뉴이름을 입력받아 메뉴를 생성한다.")
    void getMenu(String menuName) {
        Menu menu = Menu.getMenu(menuName);

        assertThat(menu.getName()).isEqualTo(menuName);
        assertThat(menu.getPrice()).isEqualTo(Menu.T_BONE_STEAK.getPrice());
        assertThat(menu.getCategory()).isEqualTo(Menu.T_BONE_STEAK.getCategory());
    }
}
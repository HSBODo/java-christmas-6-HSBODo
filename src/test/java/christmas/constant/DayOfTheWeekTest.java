package christmas.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DayOfTheWeekTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,7})
    @DisplayName("DayOfWeek.getValue() 값 1~7 중 5,6은 주말 나머지 평일")
    void getDayOfTheWeek_평일(int dayOfWeek) {
        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.getDayOfTheWeek(dayOfWeek);
        assertThat(dayOfTheWeek.getCategory()).isEqualTo("평일");
    }

    @ParameterizedTest
    @ValueSource(ints = {5,6})
    @DisplayName("DayOfWeek.getValue() 값 1~7 중 5,6은 주말 나머지 평일")
    void getDayOfTheWeek_주말(int dayOfWeek) {
        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.getDayOfTheWeek(dayOfWeek);
        assertThat(dayOfTheWeek.getCategory()).isEqualTo("주말");
    }
}
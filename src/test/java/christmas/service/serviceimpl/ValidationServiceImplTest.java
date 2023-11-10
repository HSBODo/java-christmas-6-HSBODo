package christmas.service.serviceimpl;

import christmas.constant.ChristmasPromotionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceImplTest {

    @ParameterizedTest
    @ValueSource(strings = {"15"})
    @DisplayName("입렵 값이 숫자형인지 확인한다.")
    public void isDigit_정상케이스(String number) {
        Object result = null;
        Exception exception = new Exception();
        try {
            result = Integer.parseInt(number);
        }catch (NumberFormatException e){
            exception = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_DiGIT.getMessage());
        }

        assertThat(result).isEqualTo(Integer.parseInt(number));
        assertThat(exception).isNotInstanceOf(IllegalArgumentException.class)
                .hasMessageNotContaining("[ERROR] 숫자로 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"십오"})
    @DisplayName("입렵 값이 숫자형이 아니면 예외를 발생한다.")
    public void isDigit_예외케이스(String number) {
        Exception exception = new Exception();
        try {
            Integer.parseInt(number);
        }catch (NumberFormatException e){
            exception = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_DiGIT.getMessage());
        }
        assertThat(exception).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 숫자로 입력해 주세요.");
    }
}
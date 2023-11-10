package christmas.service.serviceimpl;

import christmas.constant.ChristmasPromotionException;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class ValidationServiceImplTest {

    @ParameterizedTest
    @ValueSource(strings = {"15"})
    @DisplayName("입렵 값이 숫자형이면 true를 반환한다")
    public void isDigit_정상케이스(String number) {
        boolean result = false;
        Exception exception = new Exception();

        try {
            Integer.parseInt(number);
            result = true;
        } catch (NumberFormatException e) {
            exception = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_DiGIT.getMessage());
        }

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"십오"})
    @DisplayName("입렵 값이 숫자형이 아니면 예외를 발생한다.")
    public void isDigit_예외케이스(String number) {
        boolean result = false;
        Exception exception = new Exception();

        try {
            Integer.parseInt(number);
            result = true;
        } catch (NumberFormatException e) {
            exception = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_DiGIT.getMessage());
        }

        assertThat(result).isFalse();
        assertThat(exception).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 숫자로 입력해 주세요.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,31", "5,1,31", "15,1,31", "20,1,31", "31,1,31"}, delimiter = ',')
    @DisplayName("입렵 값이 범위에 포함되면 true를 반환한다.")
    public void isRangeReservationDate_정상케이스(int reservationDate, int start, int end) {
        boolean result = false;
        Exception exception;

        if (start <= reservationDate && end >= reservationDate) {
            result = true;
        }
        exception = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_RANGE.getMessage());

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"32,1,31", "0,1,31", "33,1,31", "34,1,31", "100,1,31"}, delimiter = ',')
    @DisplayName("입렵 값이 숫자형인지 확인한다.")
    public void isRangeReservationDate_예외케이스(int reservationDate, int start, int end) {
        boolean result = false;
        Exception exception;

        if (start <= reservationDate && end >= reservationDate) {
            result = true;
        }
        exception = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_RANGE.getMessage());

        assertThat(result).isFalse();
        assertThat(exception).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ChristmasPromotionException.INPUT_NOT_VALID_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-2", "해산물파스타-2,레드와인-1,초코케이크-1"})
    @DisplayName("입렵 값이 형싱에 맞으면 true를 반환한다.")
    public void isFormat_정상케이스(String reservationMenuAndQuantity) {
        Object result;


        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");

        Stream<String> reservationMenuAndQuantityStream = Arrays.stream(reservationMenuAndQuantityCommaSplit).
                filter(s -> s.contains("-"));
        if(reservationMenuAndQuantityCommaSplit.length==reservationMenuAndQuantityStream.count()){
            result = true;
        }else {
            result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_FORMAT.getMessage());
        }
        assertThat(result).isEqualTo(true);
        assertThat(result).isNotInstanceOf(IllegalArgumentException.class);

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "해산물파스타2", "해산물파스타2,레드와인.1,초코케이크31"})
    @DisplayName("입렵 값이 형싱에 맞지 않으면 예외를 반환한다.")
    public void isFormat_예외케이스(String reservationMenuAndQuantity) {
        Object result ;

        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");

        Stream<String> reservationMenuAndQuantityStream = Arrays.stream(reservationMenuAndQuantityCommaSplit).
                filter(s -> s.contains("-"));
        if(reservationMenuAndQuantityCommaSplit.length==reservationMenuAndQuantityStream.count()){
            result = true;
        }else {
            result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_FORMAT.getMessage());
        }
        assertThat(result).isNotEqualTo(true);
        assertThat(result).isInstanceOf(IllegalArgumentException.class);

    }
}
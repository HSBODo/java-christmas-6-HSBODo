package christmas.service;

import christmas.constant.ChristmasPromotionException;
import christmas.service.serviceimpl.ValidationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {
    ValidationService validationService;

    @BeforeEach
    public void createValidationService(){
        validationService = new ValidationServiceImpl();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "15",
            "16",
            "17",
            "18",
            "21",
            "25",
            "30",
            "31"
    })
    @DisplayName("입력받은 값이 예약가능한 날짜이면 true를 반환한다.")
    void isReservationDate_정상케이스(String reservationDate) {
        boolean result = validationService.isReservationDate(reservationDate);
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0.05",
            "0",
            "십오",
            "이십오",
            "1팔",
            "15.5555",
            "32",
            "32.22"
    })
    @DisplayName("입력받은 값이 예약가능한 날짜가 아니면 false를 반환한다.")
    void isReservationDate_예외케이스(String reservationDate) {
        boolean result = validationService.isReservationDate(reservationDate);
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "해산물파스타-20",
            "해산물파스타-1,제로콜라-2,초코케이크-1",
            "레드와인-2,티본스테이크-1,초코케이크-1"
    })
    @DisplayName("입력받은 값의 메뉴와 수량이 옳바르면 true를 반환 한다")
    void isReservationMenuAndQuantity_정상케이스(String reservationMenuAndQuantity) {
        boolean result = validationService.isReservationMenuAndQuantity(reservationMenuAndQuantity);
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "해산물파스타1,해산물파스타2,초코케이크1",
            "해산물파스타.2,티본스테이크.1,티본스테이크.1",
            "해산물파스타:2,티본스테이크:1,티본스테이크:1",
            "해산물파스타=2,티본스테이크=1,티본스테이크=1",
            "아무말이나해봐",
            "test",
            "123456",
            "!@#!@%$#$%&%^"
    })
    @DisplayName("입력받은 값의 메뉴와 수량이 옳바르지 않으면 false를 반환 한다")
    void isReservationMenuAndQuantity_예외케이스_입력형식(String reservationMenuAndQuantity) {
        boolean result = validationService.isReservationMenuAndQuantity(reservationMenuAndQuantity);
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "제로콜라-1",
            "레드와인-2",
            "샴페인-0"
    })
    @DisplayName("입력받은 값의 메뉴와 수량이 옳바르지 않으면 false를 반환 한다")
    void isReservationMenuAndQuantity_예외케이스_음료만주문(String reservationMenuAndQuantity) {
        boolean result = validationService.isReservationMenuAndQuantity(reservationMenuAndQuantity);
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "짜장면-1",
            "짜장면-1,제로콜라-2,초코케이크-1",
            "레드와인-2,탕수육-1,초코케이크-1",
            "레드와인-2,초코케이크-1,짬뽕-1"
    })
    @DisplayName("입력받은 값의 메뉴와 수량이 옳바르지 않으면 false를 반환 한다")
    void isReservationMenuAndQuantity_예외케이스_없는메뉴주문(String reservationMenuAndQuantity) {
        boolean result = validationService.isReservationMenuAndQuantity(reservationMenuAndQuantity);
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "해산물파스타-1,해산물파스타-5",
            "해산물파스타-1,해산물파스타-2,초코케이크-1",
            "해산물파스타-2,티본스테이크-1,티본스테이크-1"
    })
    @DisplayName("입력받은 값의 메뉴와 수량이 옳바르지 않으면 false를 반환 한다")
    void isReservationMenuAndQuantity_예외케이스_중복메뉴주문(String reservationMenuAndQuantity) {
        boolean result = validationService.isReservationMenuAndQuantity(reservationMenuAndQuantity);
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "해산물파스타-0",
            "해산물파스타-1,초코케이크-0",
            "해산물파스타-0,티본스테이크-5,초코케이크-0",
            "해산물파스타-2,티본스테이크-0,양송이수프-1"
    })
    @DisplayName("입력받은 값의 메뉴와 수량이 옳바르지 않으면 false를 반환 한다")
    void isReservationMenuAndQuantity_예외케이스_수량1개미만(String reservationMenuAndQuantity) {
        boolean result = validationService.isReservationMenuAndQuantity(reservationMenuAndQuantity);
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "해산물파스타-21",
            "해산물파스타-6,초코케이크-18",
            "해산물파스타-5,티본스테이크-5,초코케이크-12",
            "해산물파스타-12,티본스테이크-6,양송이수프-18"
    })
    @DisplayName("입력받은 값의 메뉴와 수량이 옳바르지 않으면 false를 반환 한다")
    void isReservationMenuAndQuantity_예외케이스_총수량20개초과(String reservationMenuAndQuantity) {
        boolean result = validationService.isReservationMenuAndQuantity(reservationMenuAndQuantity);
        assertThat(result).isFalse();
    }


}
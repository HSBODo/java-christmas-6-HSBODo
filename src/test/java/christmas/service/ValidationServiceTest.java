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
    @ValueSource(strings = {"1","15","16","17","18","31"})
    @DisplayName("입력받은 값이 예약가능한 날짜인지 검증한다.")
    void isReservationDate_정상케이스(String reservationDate) {
        boolean result = validationService.isReservationDate(reservationDate);
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0","십오","이십오","1팔","32"})
    @DisplayName("입력받은 값이 예약가능한 날짜인지 검증한다.")
    void isReservationDate_예외케이스(String reservationDate) {
        boolean result = validationService.isReservationDate(reservationDate);
        assertThat(result).isFalse();
    }
}
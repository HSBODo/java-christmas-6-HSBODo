package christmas.dto;

import christmas.constant.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReservationInfoDtoTest {

    @ParameterizedTest
    @CsvSource(value = {"15:해산물파스타-1,제로콜라-2,초코케이크-1", "7:해산물파스타-1,제로콜라-2,초코케이크-1", "30:해산물파스타-1,제로콜라-2,초코케이크-1"}, delimiter = ':')
    @DisplayName("입력받은 값이 예약가능한 날짜가 아니면 false를 반환한다.")
    void initReservationMenus(String reservationDate, String reservationMenuAndQuantity) {
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationDate,reservationMenuAndQuantity);
        List<Menu> result = reservationInfoDto.getReservationMenus();

        assertThat(result).contains(Menu.getMenu("해산물파스타"),Menu.getMenu("제로콜라"),Menu.getMenu("초코케이크"));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "15:해산물파스타-2,제로콜라-2,초코케이크-1",
            "7:해산물파스타-3,제로콜라-2,초코케이크-1",
            "30:해산물파스타-1,제로콜라-2,초코케이크-6"},
            delimiter = ':')
    @DisplayName("입력받은 값이 예약가능한 날짜가 아니면 false를 반환한다.")
    void initTotalPriceBeforeDiscount(String reservationDate, String reservationMenuAndQuantity) {
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationDate,reservationMenuAndQuantity);
        int result = reservationInfoDto.getTotalPriceBeforeDiscount();

        assertThat(result).isNotZero();
    }


}
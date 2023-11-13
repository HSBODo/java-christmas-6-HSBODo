package christmas.service;

import christmas.dto.ReservationInfoDto;
import christmas.service.serviceimpl.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class EventServiceTest {

    EventService eventService;

    @BeforeEach
    public void initEventService(){
        eventService = new EventServiceImpl();
    }


    @ParameterizedTest
    @CsvSource(value = {
            "10:해산물파스타-2,제로콜라-2,초코케이크-3",
            "7:해산물파스타-5,제로콜라-2,초코케이크-1",
            "30:해산물파스타-8,제로콜라-2,초코케이크-4"
    }, delimiter = ':')
    @DisplayName("입력받은 값이 예약가능한 날짜가 아니면 false를 반환한다.")
    void applyDecemberEvent(String reservationDay, String reservationMenuAndQuantity) {
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationDay,reservationMenuAndQuantity);
        reservationInfoDto = eventService.applyDecemberEvent(reservationInfoDto);

        assertThat(reservationInfoDto.getTotalPriceBeforeDiscount()).isNotZero();
        assertThat(reservationInfoDto.getTotalDiscountPrice()).isNotZero();
        assertThat(reservationInfoDto.getTotalPriceAfterDiscount()).isNotZero();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "25:제로콜라-2"
    }, delimiter = ':')
    @DisplayName("예약 금액이 10000원 미만이면 할인혜택을 받을 수 없다.")
    void applyDecemberEvent_금액10000만원미만(String reservationDay, String reservationMenuAndQuantity) {
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationDay,reservationMenuAndQuantity);
        reservationInfoDto = eventService.applyDecemberEvent(reservationInfoDto);

        assertThat(reservationInfoDto.getTotalPriceBeforeDiscount()).isEqualTo(6000);
        assertThat(reservationInfoDto.getTotalDiscountPrice()).isZero();
        assertThat(reservationInfoDto.getTotalPriceAfterDiscount()).isEqualTo(6000);

    }

    @ParameterizedTest
    @CsvSource(value = {
            "23:제로콜라-4"
    }, delimiter = ':')
    @DisplayName("예약날짜 1일을 1000원 기준으로 하여 1일 당 100원씩 증가하고, 마지막 25일날 최대 3400원을 할인받는다.")
    void applyDecemberEvent_크리스마스_디데이_할인(String reservationDay, String reservationMenuAndQuantity) {
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationDay,reservationMenuAndQuantity);
        reservationInfoDto = eventService.applyDecemberEvent(reservationInfoDto);

        assertThat(reservationInfoDto.getTotalPriceBeforeDiscount()).isEqualTo(12000);
        assertThat(reservationInfoDto.getTotalDiscountPrice()).isEqualTo(3200);
        assertThat(reservationInfoDto.getTotalPriceAfterDiscount()).isEqualTo(12000-3200);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "31:제로콜라-4"
    }, delimiter = ':')
    @DisplayName("특별할인은 할인적용날짜 3,10,17,24,25,31에 예약하면 1000원 할인")
    void applyDecemberEvent_특별할인(String reservationDay, String reservationMenuAndQuantity) {
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationDay,reservationMenuAndQuantity);
        reservationInfoDto = eventService.applyDecemberEvent(reservationInfoDto);

        assertThat(reservationInfoDto.getTotalPriceBeforeDiscount()).isEqualTo(12000);
        assertThat(reservationInfoDto.getTotalDiscountPrice()).isEqualTo(1000);
        assertThat(reservationInfoDto.getTotalPriceAfterDiscount()).isEqualTo(12000-1000);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "26:해산물파스타-2,제로콜라-1,초코케이크-3",
    }, delimiter = ':')
    @DisplayName("예약날짜가 평일이면 디저트 메뉴 1개당 2023원 할인한다.")
    void applyDecemberEvent_평일할인(String reservationDay, String reservationMenuAndQuantity) {
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationDay,reservationMenuAndQuantity);
        reservationInfoDto = eventService.applyDecemberEvent(reservationInfoDto);

        assertThat(reservationInfoDto.getTotalPriceBeforeDiscount()).isEqualTo((35000*2)+(3000*1)+(15000*3));
        assertThat(reservationInfoDto.getTotalDiscountPrice()).isEqualTo(2023*3);
        assertThat(reservationInfoDto.getTotalPriceAfterDiscount()).isEqualTo(((35000*2)+(3000*1)+(15000*3)) - (2023*3));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "30:해산물파스타-2,제로콜라-1,초코케이크-3",
    }, delimiter = ':')
    @DisplayName("예약날짜가 주말이면 메인 메뉴 1개당 2023원 할인한다.")
    void applyDecemberEvent_주말할인(String reservationDay, String reservationMenuAndQuantity) {
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationDay,reservationMenuAndQuantity);
        reservationInfoDto = eventService.applyDecemberEvent(reservationInfoDto);

        assertThat(reservationInfoDto.getTotalPriceBeforeDiscount()).isEqualTo((35000*2)+(3000*1)+(15000*3));
        assertThat(reservationInfoDto.getTotalDiscountPrice()).isEqualTo(2023*2);
        assertThat(reservationInfoDto.getTotalPriceAfterDiscount()).isEqualTo(((35000*2)+(3000*1)+(15000*3)) - (2023*2));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "30:해산물파스타-5,제로콜라-1,초코케이크-3",
    }, delimiter = ':')
    @DisplayName("할인 전 총주문 금액이 12만원 이상이면 샴페인 1개 증정")
    void applyDecemberEvent_증정품이벤트(String reservationDay, String reservationMenuAndQuantity) {
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationDay,reservationMenuAndQuantity);
        reservationInfoDto = eventService.applyDecemberEvent(reservationInfoDto);

        assertThat(reservationInfoDto.getTotalPriceBeforeDiscount()).isEqualTo((35000*5)+(3000*1)+(15000*3));
        assertThat(reservationInfoDto.getTotalDiscountPrice()).isEqualTo(2023*5);
        assertThat(reservationInfoDto.getTotalPriceAfterDiscount()).isEqualTo(((35000*5)+(3000*1)+(15000*3)) - (2023*5));
        assertThat(reservationInfoDto.getGiveaway()).isEqualTo("샴페인");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "30:해산물파스타-5,제로콜라-1,초코케이크-3",
    }, delimiter = ':')
    @DisplayName("총혜택 금엑에 따라 배지를 부여한다. 5천원이상=별, 1만원이상=트리, 2만원이상=산타")
    void applyDecemberEvent_배지이벤트(String reservationDay, String reservationMenuAndQuantity) {
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationDay,reservationMenuAndQuantity);
        reservationInfoDto = eventService.applyDecemberEvent(reservationInfoDto);

        assertThat(reservationInfoDto.getTotalPriceBeforeDiscount()).isEqualTo((35000*5)+(3000*1)+(15000*3));
        assertThat(reservationInfoDto.getTotalDiscountPrice()).isEqualTo(2023*5);
        assertThat(reservationInfoDto.getTotalPriceAfterDiscount()).isEqualTo(((35000*5)+(3000*1)+(15000*3)) - (2023*5));
        assertThat(reservationInfoDto.getGiveaway()).isEqualTo("샴페인");
        assertThat(reservationInfoDto.getBadge()).isEqualTo("트리");
    }



}
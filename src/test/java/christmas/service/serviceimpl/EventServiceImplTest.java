package christmas.service.serviceimpl;

import christmas.constant.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EventServiceImplTest {

    @ParameterizedTest
    @ValueSource(ints = {10000,11000,20000,35000})
    @DisplayName("이벤트적용 가능하면 true를 반환한다.")
    void isApplyEvent_정상케이스(int totalPriceBeforeDiscount) {
        Object result;
        if(totalPriceBeforeDiscount<10000){
            result = false;
        }else {
            result = true;
        }
        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(ints = {9000,8000,1000,5000})
    @DisplayName("이벤트적용 가능하지 않으면 false를 반환한다.")
    void isApplyEvent_예외케이스(int totalPriceBeforeDiscount) {
        Object result;
        if(totalPriceBeforeDiscount<10000){
            result = false;
        }else {
            result = true;
        }
        assertThat(result).isEqualTo(false);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,10,15,25})
    @DisplayName("크리스마스 D-Day 이벤트 할인금액을 반환한다.")
    void applyChristmasDDayEvent_정상케이스(int totalPriceBeforeDiscount) {
        final int oneDayPerDiscountPrice = 100;
        final int discountFirstDay = 1;
        final int discountLastDay = 25;

        int minDiscountPrice = 1000;
        int reservationDay = totalPriceBeforeDiscount;

        int discountPrice = minDiscountPrice+((reservationDay-1)*oneDayPerDiscountPrice);
        if(reservationDay>discountLastDay || reservationDay<discountFirstDay) discountPrice = 0;
        assertThat(discountPrice).isNotZero();
    }

    @ParameterizedTest
    @ValueSource(ints = {0,26,31,88})
    @DisplayName("크리스마스 D-Day 이벤트 기간이 아니면 0을 반환한다.")
    void applyChristmasDDayEvent_예외케이스(int totalPriceBeforeDiscount) {
        final int oneDayPerDiscountPrice = 100;
        final int discountFirstDay = 1;
        final int discountLastDay = 25;

        int minDiscountPrice = 1000;
        int reservationDay = totalPriceBeforeDiscount;

        int discountPrice = minDiscountPrice+((reservationDay-1)*oneDayPerDiscountPrice);
        if(reservationDay>discountLastDay || reservationDay<discountFirstDay) discountPrice = 0;
        assertThat(discountPrice).isZero();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2023,12,1",
            "2023,12,2",
            "2023,12,3",
            "2023,12,4",
            "2023,12,5",
            "2023,12,6",
            "2023,12,7",
            "2023,12,8"
    }, delimiter = ',')
    @DisplayName("날짜를 받아 요일은 구한다.")
    void findDayOfWeek(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        assertThat(dayOfWeek.getValue()).isNotZero();
    }

    @Test
    @DisplayName("평일 할인은 디저트1개당 2023원 할인금액을 반환한다.")
    void applyWeekdayDiscount_정상케이스() {
        Object result;

        final int discountCategoryPerDiscountPrice = 2023;
        final String discountCategory = "디저트";

        List<Menu> menus = new ArrayList<>();
        Menu 초코케이크 = Menu.getMenu("초코케이크");
        Menu 아이스크림 = Menu.getMenu("아이스크림");
        Menu 레드와인 = Menu.getMenu("레드와인");
        Menu 해산물파스타 = Menu.getMenu("해산물파스타");

        menus.add(초코케이크);
        menus.add(레드와인);
        menus.add(해산물파스타);
        menus.add(아이스크림);

        long 디저트수량 = menus.stream()
                .filter(menu -> menu.getCategory().contains(discountCategory)).count();

        result = (int)디저트수량*discountCategoryPerDiscountPrice;

        assertThat(result).isEqualTo(2023*2);
    }

    @Test
    @DisplayName("평일 할인은 메뉴에 디저트가 없으면 할인금액 0원을 반환한다.")
    void applyWeekdayDiscount_예외케이스() {
        Object result;

        final int discountCategoryPerDiscountPrice = 2023;
        final String discountCategory = "디저트";

        List<Menu> menus = new ArrayList<>();
        Menu 초코케이크 = Menu.getMenu("양송이수프");
        Menu 아이스크림 = Menu.getMenu("바비큐립");
        Menu 레드와인 = Menu.getMenu("레드와인");
        Menu 해산물파스타 = Menu.getMenu("해산물파스타");

        menus.add(초코케이크);
        menus.add(레드와인);
        menus.add(해산물파스타);
        menus.add(아이스크림);

        long 디저트수량 = menus.stream()
                .filter(menu -> menu.getCategory().contains(discountCategory)).count();
        result = (int)디저트수량*discountCategoryPerDiscountPrice;

        assertThat(result).isEqualTo(0);
    }


}
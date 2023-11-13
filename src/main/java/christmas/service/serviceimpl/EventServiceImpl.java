package christmas.service.serviceimpl;

import christmas.constant.Menu;
import christmas.dto.ReservationInfoDto;
import christmas.service.EventService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Stream;

public class EventServiceImpl implements EventService {
    private final int EVENT_APPLY_PRICE = 10000;
    private final int EVENT_APPLY_YEAR = 2023;
    private final int EVENT_APPLY_MONTH = 12;

    @Override
    public ReservationInfoDto applyDecemberEvent(ReservationInfoDto reservationInfoDto) {
        return null;
    }

    private boolean isApplyEvent(ReservationInfoDto reservationInfoDto){
        int totalPriceBeforeDiscount = reservationInfoDto.getTotalPriceBeforeDiscount();
        if(totalPriceBeforeDiscount<EVENT_APPLY_PRICE) return false;
        return true;
    }

    private int applyChristmasD_DayDiscount(ReservationInfoDto reservationInfoDto){
        final int oneDayPerDiscountPrice = 100;
        final int discountFirstDay = 1;
        final int discountLastDay = 25;

        int minDiscountPrice = 1000;
        int reservationDay = reservationInfoDto.getReservationDay();

        if(reservationDay > discountLastDay || reservationDay < discountFirstDay) return 0;

        int discountPrice = minDiscountPrice + ((reservationDay-1) * oneDayPerDiscountPrice);

        return discountPrice;
    }

    private int applyWeekdayDiscount(ReservationInfoDto reservationInfoDto){
        final int discountCategoryPerDiscountPrice = 2023;
        final String discountCategory = "디저트";

        long dessertQuantity = reservationInfoDto.getReservationMenus().stream()
                .filter(menu -> menu.getCategory().contains(discountCategory)).count();

        return (int)dessertQuantity * discountCategoryPerDiscountPrice;
    }

    private int applyWeekendDiscount(ReservationInfoDto reservationInfoDto){
        final int discountCategoryPerDiscountPrice = 2023;
        final String discountCategory = "메인";

        long dessertQuantity = reservationInfoDto.getReservationMenus().stream()
                .filter(menu -> menu.getCategory().contains(discountCategory)).count();

        return  (int)dessertQuantity * discountCategoryPerDiscountPrice;
    }

    private int findDayOfWeek(int year, int month, int day){
        //월요일(1)~일요일(7)
        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getValue();
    }

    private boolean isWeekday(int day){
        int dayOfWeek = findDayOfWeek(EVENT_APPLY_YEAR, EVENT_APPLY_MONTH, day);
        if(dayOfWeek == christmas.constant.DayOfWeek.토요일.getValue() || dayOfWeek ==  christmas.constant.DayOfWeek.일요일.getValue()){
            return false;
        }
        return true;
    }

    private boolean isWeekend(int day){
        int dayOfWeek = findDayOfWeek(EVENT_APPLY_YEAR, EVENT_APPLY_MONTH,day);
        if(dayOfWeek == christmas.constant.DayOfWeek.토요일.getValue() || dayOfWeek ==  christmas.constant.DayOfWeek.일요일.getValue()){
            return true;
        }
        return false;
    }


}

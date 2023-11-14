package christmas.service.serviceimpl;

import christmas.constant.BenefitsTitle;
import christmas.constant.Menu;
import christmas.dto.ReservationInfoDto;
import christmas.service.EventService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService {
    private final int EVENT_APPLY_NECESSARY_PRICE = 10000;
    private final int EVENT_APPLY_YEAR = 2023;
    private final int EVENT_APPLY_MONTH = 12;

    @Override
    public ReservationInfoDto applyDecemberEvent(ReservationInfoDto reservationInfoDto) {
        final int reservationDay = reservationInfoDto.getReservationDay();
        final int reservationPrice = reservationInfoDto.getTotalPriceBeforeDiscount();

        if(!isApplyEvent(reservationInfoDto)) return reservationInfoDto;

        int discount = applyChristmasD_DayDiscount(reservationInfoDto);
        reservationInfoDto.applyDiscountPrice(BenefitsTitle.크리스마스_디데이_할인, discount);

        if(isWeekday(reservationDay)) {
            int discountPrice = applyWeekdayDiscount(reservationInfoDto);
            reservationInfoDto.applyDiscountPrice(BenefitsTitle.평일_할인, discountPrice);
        }

        if(isWeekend(reservationDay)) {
            int discountPrice = applyWeekendDiscount(reservationInfoDto);
            reservationInfoDto.applyDiscountPrice(BenefitsTitle.주말_할인, discountPrice);
        }

        int discountPrice = specialDiscount(reservationInfoDto);
        reservationInfoDto.applyDiscountPrice(BenefitsTitle.특별_할인, discountPrice);

        if(reservationPrice>=120000) reservationInfoDto = giveawayEvent(reservationInfoDto);

        reservationInfoDto = badgeEvent(reservationInfoDto);

        return reservationInfoDto;
    }

    private boolean isApplyEvent(ReservationInfoDto reservationInfoDto){
        int totalPriceBeforeDiscount = reservationInfoDto.getTotalPriceBeforeDiscount();
        if(totalPriceBeforeDiscount<EVENT_APPLY_NECESSARY_PRICE) return false;
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
        int dessertQuantity = 0;

        List<Menu> desserts = reservationInfoDto.getReservationMenus().stream()
                .filter(menu -> menu.getCategory().contains(discountCategory))
                .collect(Collectors.toList());
        for(Menu desert : desserts){
            dessertQuantity += reservationInfoDto.getQuantityOf(desert.getName());
        }

        return dessertQuantity * discountCategoryPerDiscountPrice;
    }

    private int applyWeekendDiscount(ReservationInfoDto reservationInfoDto){
        final int discountCategoryPerDiscountPrice = 2023;
        final String discountCategory = "메인";
        int mainQuantity = 0;

        List<Menu> mains = reservationInfoDto.getReservationMenus().stream()
                .filter(menu -> menu.getCategory().contains(discountCategory))
                .collect(Collectors.toList());
        for(Menu main : mains){
            mainQuantity += reservationInfoDto.getQuantityOf(main.getName());
        }

        return mainQuantity * discountCategoryPerDiscountPrice;
    }

    private ReservationInfoDto giveawayEvent(ReservationInfoDto reservationInfoDto){
        reservationInfoDto.applyGiveaway(BenefitsTitle.증정_이벤트,Menu.CHAMPAGNE);
        return reservationInfoDto;
    }

    private int specialDiscount(ReservationInfoDto reservationInfoDto){
        final List<Integer> specialDay = new ArrayList<>(List.of(3,10,17,24,25,31));
        if(specialDay.contains(reservationInfoDto.getReservationDay())){
            return 1000;
        }
        return 0;
    }

    private ReservationInfoDto badgeEvent(ReservationInfoDto reservationInfoDto){
        int totalDiscountPrice = reservationInfoDto.getTotalDiscountPrice();

        if(totalDiscountPrice>=20000){
            reservationInfoDto.setBadge("산타");
            return reservationInfoDto;
        }
        if(totalDiscountPrice>=10000){
            reservationInfoDto.setBadge("트리");
            return reservationInfoDto;
        }
        if(totalDiscountPrice>=5000) {
            reservationInfoDto.setBadge("별");
            return reservationInfoDto;
        }
        reservationInfoDto.setBadge("없음");
        return reservationInfoDto;
    }

    private int findDayOfWeek(int year, int month, int day){
        //월요일(1)~일요일(7)
        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getValue();
    }

    private boolean isWeekday(int day){
        int dayOfWeek = findDayOfWeek(EVENT_APPLY_YEAR, EVENT_APPLY_MONTH, day);
        if(dayOfWeek == christmas.constant.DayOfWeek.금요일.getValue() || dayOfWeek ==  christmas.constant.DayOfWeek.토요일.getValue()){
            return false;
        }
        return true;
    }

    private boolean isWeekend(int day){
        int dayOfWeek = findDayOfWeek(EVENT_APPLY_YEAR, EVENT_APPLY_MONTH,day);
        if(dayOfWeek == christmas.constant.DayOfWeek.금요일.getValue() || dayOfWeek ==  christmas.constant.DayOfWeek.토요일.getValue()){
            return true;
        }
        return false;
    }


}

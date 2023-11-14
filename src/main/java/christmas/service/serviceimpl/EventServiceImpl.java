package christmas.service.serviceimpl;

import christmas.constant.BenefitsTitle;
import christmas.constant.Menu;
import christmas.dto.ReservationInfoDto;
import christmas.service.EventService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventServiceImpl implements EventService {
    private final int EVENT_APPLY_NECESSARY_PRICE = 10000;
    private final int EVENT_APPLY_YEAR = 2023;
    private final int EVENT_APPLY_MONTH = 12;

    @Override
    public ReservationInfoDto applyDecemberEvent(ReservationInfoDto reservationInfoDto) {
        if(!isApplyEvent(reservationInfoDto)) return reservationInfoDto;

        applyChristmasD_DayDiscount(reservationInfoDto);
        applyWeekdayDiscount(reservationInfoDto);
        applyWeekendDiscount(reservationInfoDto);
        specialDiscount(reservationInfoDto);
        giveawayEvent(reservationInfoDto);
        badgeEvent(reservationInfoDto);

        return reservationInfoDto;
    }

    private boolean isApplyEvent(ReservationInfoDto reservationInfoDto){
        int totalPriceBeforeDiscount = reservationInfoDto.getTotalPriceBeforeDiscount();
        if(totalPriceBeforeDiscount<EVENT_APPLY_NECESSARY_PRICE) return false;
        return true;
    }

    private ReservationInfoDto applyChristmasD_DayDiscount(ReservationInfoDto reservationInfoDto){
        final BenefitsTitle benefitsTitle = BenefitsTitle.크리스마스_디데이_할인;
        final int oneDayPerDiscountPrice = 100;
        final int discountFirstDay = 1;
        final int discountLastDay = 25;

        int minDiscountPrice = 1000;
        int reservationDay = reservationInfoDto.getReservationDay();

        if(reservationDay > discountLastDay || reservationDay < discountFirstDay) return reservationInfoDto;

        int discountPrice = minDiscountPrice + ((reservationDay-1) * oneDayPerDiscountPrice);
        reservationInfoDto.applyDiscountPrice(benefitsTitle, discountPrice);

        return reservationInfoDto;
    }

    private ReservationInfoDto applyWeekdayDiscount(ReservationInfoDto reservationInfoDto){
        final BenefitsTitle benefitsTitle = BenefitsTitle.평일_할인;
        final int discountCategoryPerDiscountPrice = 2023;
        final String discountCategory = "디저트";

        Map<Menu, Integer> reservationMenusQuantity = reservationInfoDto.getReservationMenusQuantity();
        int reservationDay = reservationInfoDto.getReservationDay();
        int dessertQuantity = 0;

        if(!isWeekday(reservationDay)) return reservationInfoDto;

        for (Menu menu: reservationMenusQuantity.keySet()){
            if (menu.getCategory()==discountCategory){
                dessertQuantity += reservationMenusQuantity.get(menu);
            }
        }
        int discountPrice = dessertQuantity * discountCategoryPerDiscountPrice;
        reservationInfoDto.applyDiscountPrice(benefitsTitle, discountPrice);
        return reservationInfoDto;
    }

    private ReservationInfoDto applyWeekendDiscount(ReservationInfoDto reservationInfoDto){
        final BenefitsTitle benefitsTitle = BenefitsTitle.주말_할인;
        final String discountCategory = "메인";
        final int discountCategoryPerDiscountPrice = 2023;

        Map<Menu, Integer> reservationMenusQuantity = reservationInfoDto.getReservationMenusQuantity();
        int reservationDay = reservationInfoDto.getReservationDay();
        int mainQuantity = 0;

        if(!isWeekend(reservationDay)) return reservationInfoDto;

        for (Menu menu: reservationMenusQuantity.keySet()){
            if (menu.getCategory()==discountCategory){
                mainQuantity += reservationMenusQuantity.get(menu);
            }
        }
        int discountPrice = mainQuantity * discountCategoryPerDiscountPrice;
        reservationInfoDto.applyDiscountPrice(benefitsTitle, discountPrice);
        return reservationInfoDto;
    }

    private ReservationInfoDto giveawayEvent(ReservationInfoDto reservationInfoDto){
        final BenefitsTitle benefitsTitle = BenefitsTitle.증정_이벤트;
        final Menu giveawayMenu = Menu.CHAMPAGNE;
        final int giveawayQuantity = 1;
        final int giveawayEventNecessaryPrice = 120000;

        int totalPriceBeforeDiscount = reservationInfoDto.getTotalPriceBeforeDiscount();

        if(totalPriceBeforeDiscount>=giveawayEventNecessaryPrice){
            reservationInfoDto.applyGiveaway(benefitsTitle,giveawayMenu,giveawayQuantity);
            return reservationInfoDto;
        }
        return reservationInfoDto;
    }

    private ReservationInfoDto specialDiscount(ReservationInfoDto reservationInfoDto){
        final BenefitsTitle benefitsTitle = BenefitsTitle.특별_할인;
        final List<Integer> specialDay = new ArrayList<>(List.of(3,10,17,24,25,31));
        final int discountPrice = 1000;

        int reservationDay = reservationInfoDto.getReservationDay();

        if(specialDay.contains(reservationDay)){
            reservationInfoDto.applyDiscountPrice(benefitsTitle, discountPrice);
            return reservationInfoDto;
        }
        return reservationInfoDto;
    }

    private ReservationInfoDto badgeEvent(ReservationInfoDto reservationInfoDto){
        int totalBenefitsPrice = reservationInfoDto.getTotalBenefitsPrice()*-1;
        System.out.println("totalBenefitsPrice = " + totalBenefitsPrice);
        if(totalBenefitsPrice>=20000){
            reservationInfoDto.setBadge("산타");
            return reservationInfoDto;
        }
        if(totalBenefitsPrice>=10000){
            reservationInfoDto.setBadge("트리");
            return reservationInfoDto;
        }
        if(totalBenefitsPrice>=5000) {
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

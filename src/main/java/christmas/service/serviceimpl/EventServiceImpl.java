package christmas.service.serviceimpl;

import christmas.constant.DayOfTheWeek;
import christmas.domain.event.*;
import christmas.dto.ReservationInfoDto;
import christmas.service.EventService;

import java.time.DayOfWeek;
import java.time.LocalDate;


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
        return totalPriceBeforeDiscount >= EVENT_APPLY_NECESSARY_PRICE;
    }

    private void applyChristmasD_DayDiscount(ReservationInfoDto reservationInfoDto){
        final int DISCOUNT_FIRST_DAY = 1;
        final int DISCOUNT_LAST_DAY = 25;

        int reservationDay = reservationInfoDto.getReservationDay();
        //적용조건
        if(reservationDay > DISCOUNT_LAST_DAY || reservationDay < DISCOUNT_FIRST_DAY) return;

        new ChristmasDdayDiscount().apply(reservationInfoDto);
    }

    private void applyWeekdayDiscount(ReservationInfoDto reservationInfoDto){
        int reservationDay = reservationInfoDto.getReservationDay();
        //적용조건
        if(isWeekday(reservationDay)) new WeekdayDiscount().apply(reservationInfoDto);
    }

    private void applyWeekendDiscount(ReservationInfoDto reservationInfoDto){
        int reservationDay = reservationInfoDto.getReservationDay();
        //적용조건
        if(isWeekend(reservationDay)) new WeekendDiscount().apply(reservationInfoDto);
    }

    private void giveawayEvent(ReservationInfoDto reservationInfoDto){
        final int GIVEAWAY_EVENT_NECESSARY_TOTAL_PRICE_BEFORE_DISCOUNT = 120000;

        int totalPriceBeforeDiscount = reservationInfoDto.getTotalPriceBeforeDiscount();
        //적용조건
        if(totalPriceBeforeDiscount>=GIVEAWAY_EVENT_NECESSARY_TOTAL_PRICE_BEFORE_DISCOUNT) new GiveawayEvent().apply(reservationInfoDto);
    }

    private void specialDiscount(ReservationInfoDto reservationInfoDto){
        SpecialDiscount specialDiscount = new SpecialDiscount();
        int reservationDay = reservationInfoDto.getReservationDay();
        //적용조건
        if(specialDiscount.isSpecialDay(reservationDay)) specialDiscount.apply(reservationInfoDto);
    }

    private void badgeEvent(ReservationInfoDto reservationInfoDto){
        new BadgeEvent().apply(reservationInfoDto);
    }

    private DayOfTheWeek findDayOfWeek(int year, int month, int day){
        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.getDayOfTheWeek(dayOfWeek.getValue());
        return dayOfTheWeek;
    }

    private boolean isWeekday(int day){
        DayOfTheWeek dayOfWeek = findDayOfWeek(EVENT_APPLY_YEAR, EVENT_APPLY_MONTH, day);
        return "평일".equals(dayOfWeek.getCategory());
    }

    private boolean isWeekend(int day){
        DayOfTheWeek dayOfWeek = findDayOfWeek(EVENT_APPLY_YEAR, EVENT_APPLY_MONTH, day);
        return "주말".equals(dayOfWeek.getCategory());
    }


}

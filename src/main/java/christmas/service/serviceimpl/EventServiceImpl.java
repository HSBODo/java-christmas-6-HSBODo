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

        if(totalPriceBeforeDiscount<EVENT_APPLY_NECESSARY_PRICE) return false;

        return true;
    }

    private void applyChristmasD_DayDiscount(ReservationInfoDto reservationInfoDto){
        final int discountFirstDay = 1;
        final int discountLastDay = 25;

        int reservationDay = reservationInfoDto.getReservationDay();
        //적용조건
        if(reservationDay > discountLastDay || reservationDay < discountFirstDay) return;

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
        final int giveawayEventNecessaryTotalPriceBeforeDiscount = 120000;
        int totalPriceBeforeDiscount = reservationInfoDto.getTotalPriceBeforeDiscount();
        //적용조건
        if(totalPriceBeforeDiscount>=giveawayEventNecessaryTotalPriceBeforeDiscount) new GiveawayEvent().apply(reservationInfoDto);
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
        if(dayOfWeek.getCategory() == "평일") return true;
        return false;
    }

    private boolean isWeekend(int day){
        DayOfTheWeek dayOfWeek = findDayOfWeek(EVENT_APPLY_YEAR, EVENT_APPLY_MONTH, day);
        if(dayOfWeek.getCategory() == "주말") return true;
        return false;
    }


}

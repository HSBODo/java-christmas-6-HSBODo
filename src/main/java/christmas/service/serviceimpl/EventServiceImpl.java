package christmas.service.serviceimpl;

import christmas.dto.ReservationInfoDto;
import christmas.service.EventService;

public class EventServiceImpl implements EventService {
    private final int EVENT_APPLY_PRICE = 10000;

    @Override
    public ReservationInfoDto applyDecemberEvent(ReservationInfoDto reservationInfoDto) {
        return null;
    }

    private boolean isApplyEvent(ReservationInfoDto reservationInfoDto){
        int totalPriceBeforeDiscount = reservationInfoDto.getTotalPriceBeforeDiscount();
        if(totalPriceBeforeDiscount<EVENT_APPLY_PRICE) return false;
        return true;
    }

    private int applyChristmasDDayEvent(ReservationInfoDto reservationInfoDto){
        final int oneDayPerDiscountPrice = 100;
        final int discountFirstDay = 1;
        final int discountLastDay = 25;

        int minDiscountPrice = 1000;
        int reservationDay = Integer.parseInt(reservationInfoDto.getReservationDate());

        if(reservationDay>discountLastDay || reservationDay<discountFirstDay) return 0;

        int discountPrice = minDiscountPrice+((reservationDay-1)*oneDayPerDiscountPrice);

        return discountPrice;
    }


}

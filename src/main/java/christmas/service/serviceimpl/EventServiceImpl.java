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

}

package christmas.domain.event;

import christmas.constant.BenefitsTitle;
import christmas.domain.Event;
import christmas.dto.ReservationInfoDto;

public class ChristmasDdayDiscount extends Event {
    private final int oneDayPerDiscountPrice;
    private int minDiscountPrice;

    public ChristmasDdayDiscount() {
        this.benefitsTitle = BenefitsTitle.크리스마스_디데이_할인;
        this.oneDayPerDiscountPrice = 100;
        this.minDiscountPrice = 1000;
    }

    @Override
    public void apply(ReservationInfoDto reservationInfoDto) {
        int reservationDay = reservationInfoDto.getReservationDay();
        int discountPrice = minDiscountPrice + ((reservationDay-1) * oneDayPerDiscountPrice);
        reservationInfoDto.applyDiscountPrice(benefitsTitle, discountPrice);
    }
}

package christmas.domain.event;

import christmas.constant.BenefitsTitle;
import christmas.domain.Event;
import christmas.dto.ReservationInfoDto;

public class ChristmasDdayDiscount extends Event {
    private final int ONE_DAY_PER_DISCOUNT_PRICE;
    private int minDiscountPrice;

    public ChristmasDdayDiscount() {
        this.benefitsTitle = BenefitsTitle.크리스마스_디데이_할인;
        this.ONE_DAY_PER_DISCOUNT_PRICE = 100;
        this.minDiscountPrice = 1000;
    }

    @Override
    public void apply(ReservationInfoDto reservationInfoDto) {
        int reservationDay = reservationInfoDto.getReservationDay();
        int discountPrice = minDiscountPrice + ((reservationDay - 1) * ONE_DAY_PER_DISCOUNT_PRICE);
        reservationInfoDto.applyDiscountPrice(benefitsTitle, discountPrice);
    }
}

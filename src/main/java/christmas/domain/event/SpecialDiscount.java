package christmas.domain.event;

import christmas.constant.BenefitsTitle;
import christmas.domain.Event;
import christmas.dto.ReservationInfoDto;

import java.util.ArrayList;
import java.util.List;

public class SpecialDiscount extends Event {
    private final List<Integer> SPECIAL_DAY;
    private final int DISCOUNT_PRICE;

    public SpecialDiscount() {
        this.benefitsTitle = BenefitsTitle.특별_할인;
        this.SPECIAL_DAY = new ArrayList<>(List.of(3, 10, 17, 24, 25, 31));
        this.DISCOUNT_PRICE = 1000;
    }

    @Override
    public void apply(ReservationInfoDto reservationInfoDto) {
        reservationInfoDto.applyDiscountPrice(benefitsTitle, DISCOUNT_PRICE);
    }

    public boolean isSpecialDay(int day) {
        if (SPECIAL_DAY.contains(day)) return true;
        return false;
    }
}

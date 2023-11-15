package christmas.domain.event;

import christmas.constant.BenefitsTitle;
import christmas.domain.Event;
import christmas.dto.ReservationInfoDto;

import java.util.ArrayList;
import java.util.List;

public class SpecialDiscount extends Event {
    private final List<Integer> specialDay;
    private final int discountPrice;

    public SpecialDiscount() {
        this.benefitsTitle = BenefitsTitle.특별_할인;
        this.specialDay =  new ArrayList<>(List.of(3,10,17,24,25,31));
        this.discountPrice = 1000;
    }

    @Override
    public void apply(ReservationInfoDto reservationInfoDto) {
        reservationInfoDto.applyDiscountPrice(benefitsTitle, discountPrice);
    }

    public boolean isSpecialDay(int day){
        if(specialDay.contains(day)) return true;
        return false;
    }
}

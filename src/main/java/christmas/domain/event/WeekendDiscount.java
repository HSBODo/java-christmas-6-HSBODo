package christmas.domain.event;

import christmas.constant.BenefitsTitle;
import christmas.constant.Menu;
import christmas.domain.Event;
import christmas.dto.ReservationInfoDto;

import java.util.Map;

public class WeekendDiscount extends Event {
    private final int discountCategoryPerDiscountPrice;
    private final String discountCategory;

    public WeekendDiscount() {
        this.benefitsTitle = BenefitsTitle.주말_할인;
        this.discountCategoryPerDiscountPrice = 2023;
        this.discountCategory = "메인";
    }

    @Override
    public void apply(ReservationInfoDto reservationInfoDto) {
        Map<Menu, Integer> reservationMenusQuantity = reservationInfoDto.getReservationMenusQuantity();
        int categoryMenuQuantity = 0;
        for (Menu menu: reservationMenusQuantity.keySet()){
            if (menu.getCategory()==discountCategory){
                categoryMenuQuantity += reservationMenusQuantity.get(menu);
            }
        }
        if(categoryMenuQuantity == 0) return;
        int discountPrice = categoryMenuQuantity * discountCategoryPerDiscountPrice;
        reservationInfoDto.applyDiscountPrice(benefitsTitle, discountPrice);
    }
}

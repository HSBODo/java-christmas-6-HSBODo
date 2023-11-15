package christmas.domain.event;

import christmas.constant.BenefitsTitle;
import christmas.constant.Menu;
import christmas.domain.Event;
import christmas.dto.ReservationInfoDto;

import java.util.Map;

public class WeekdayDiscount extends Event {
    private final int DISCOUNT_CATEGORY_PER_DISCOUNT_PRICE;
    private final String DISCOUNT_CATEGORY;

    public WeekdayDiscount() {
        this.benefitsTitle = BenefitsTitle.평일_할인;
        this.DISCOUNT_CATEGORY_PER_DISCOUNT_PRICE = 2023;
        this.DISCOUNT_CATEGORY = "디저트";
    }

    @Override
    public void apply(ReservationInfoDto reservationInfoDto) {
        Map<Menu, Integer> reservationMenusQuantity = reservationInfoDto.getReservationMenusQuantity();
        int categoryMenuQuantity = 0;
        for (Menu menu: reservationMenusQuantity.keySet()){
            if (menu.getCategory()==DISCOUNT_CATEGORY){
                categoryMenuQuantity += reservationMenusQuantity.get(menu);
            }
        }
        if(categoryMenuQuantity == 0) return;

        int discountPrice = categoryMenuQuantity * DISCOUNT_CATEGORY_PER_DISCOUNT_PRICE;
        reservationInfoDto.applyDiscountPrice(benefitsTitle, discountPrice);
    }
}

package christmas.domain.event;

import christmas.constant.BenefitsTitle;
import christmas.constant.Menu;
import christmas.domain.Event;
import christmas.dto.ReservationInfoDto;

public class GiveawayEvent extends Event {
    private final int GIVEAWAY_QUANTITY;
    private final Menu GIVEAWAY_MENU;

    public GiveawayEvent() {
        this.benefitsTitle = BenefitsTitle.증정_이벤트;
        this.GIVEAWAY_MENU = Menu.CHAMPAGNE;
        this.GIVEAWAY_QUANTITY = 1;
    }

    @Override
    public void apply(ReservationInfoDto reservationInfoDto) {
        reservationInfoDto.applyGiveaway(benefitsTitle, GIVEAWAY_MENU, GIVEAWAY_QUANTITY);
    }
}

package christmas.domain.event;

import christmas.constant.BenefitsTitle;
import christmas.constant.Menu;
import christmas.domain.Event;
import christmas.dto.ReservationInfoDto;

public class GiveawayEvent extends Event {
    private final int giveawayQuantity;
    private final Menu giveawayMenu;

    public GiveawayEvent() {
        this.benefitsTitle = BenefitsTitle.증정_이벤트;
        this.giveawayMenu = Menu.CHAMPAGNE;
        this.giveawayQuantity = 1;
    }

    @Override
    public void apply(ReservationInfoDto reservationInfoDto) {
        reservationInfoDto.applyGiveaway(benefitsTitle,giveawayMenu,giveawayQuantity);
    }
}

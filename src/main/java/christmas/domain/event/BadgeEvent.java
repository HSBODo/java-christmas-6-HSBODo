package christmas.domain.event;

import christmas.constant.Badge;
import christmas.constant.BenefitsTitle;
import christmas.domain.Event;
import christmas.dto.ReservationInfoDto;

public class BadgeEvent extends Event {
    public BadgeEvent() {
        this.benefitsTitle = BenefitsTitle.배지_이벤트;
    }

    @Override
    public void apply(ReservationInfoDto reservationInfoDto) {
        int totalBenefitsPrice = reservationInfoDto.getTotalBenefitsPrice();
        Badge badge = Badge.getBadge(totalBenefitsPrice);
        reservationInfoDto.setBadge(badge.getName());
    }
}

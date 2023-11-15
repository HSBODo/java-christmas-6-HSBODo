package christmas.domain;

import christmas.constant.BenefitsTitle;
import christmas.constant.Menu;
import christmas.dto.ReservationInfoDto;

import java.util.Map;

public abstract class Event {
    public BenefitsTitle benefitsTitle;

    public abstract void apply(ReservationInfoDto reservationInfoDto);
}

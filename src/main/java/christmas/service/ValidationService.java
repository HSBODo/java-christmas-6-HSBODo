package christmas.service;

import christmas.constant.ChristmasPromotionException;

public interface ValidationService {
    boolean isValidReservationDay(String reservationDate);

    boolean isValidReservationMenuAndQuantity(String reservationMenuAndQuantity);

}

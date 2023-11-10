package christmas.service;

import christmas.constant.ChristmasPromotionException;

public interface ValidationService {
    boolean isReservationDate(String reservationDate);

    boolean isReservationMenuAndQuantity(String reservationMenuAndQuantity);

}

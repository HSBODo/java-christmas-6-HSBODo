package christmas.service.serviceimpl;

import christmas.constant.ChristmasPromotionException;
import christmas.service.ValidationService;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean isReservationDate(String date){
       return false;
    }

    @Override
    public boolean isReservationMenuAndQuantity(String reservationMenuAndQuantity) {
        return false;
    }

    private boolean isDigit(String reservationDate){
        try {
            Integer.parseInt(reservationDate);
            return true;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_DiGIT.getMessage());
        }
    }
}


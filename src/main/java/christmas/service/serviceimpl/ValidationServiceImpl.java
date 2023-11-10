package christmas.service.serviceimpl;

import christmas.constant.ChristmasPromotionException;
import christmas.service.ValidationService;

public class ValidationServiceImpl implements ValidationService {
    private final int RESERVATION_DATE_RANGE_START = 1;
    private final int RESERVATION_DATE_RANGE_END = 31;

    @Override
    public boolean isReservationDate(String reservationDate){
        try {
            isDigit(reservationDate);
            isRangeReservationDate(Integer.parseInt(reservationDate),RESERVATION_DATE_RANGE_START,RESERVATION_DATE_RANGE_END);
            return true;
        }catch (Exception e){
            System.out.println(ChristmasPromotionException.INPUT_NOT_VALID_DATE.getMessage());
            return false;
        }
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

    private boolean isRangeReservationDate(int reservationDate,int start,int end){
        if(start <= reservationDate && end >= reservationDate){
            return true;
        }
        throw new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_RANGE.getMessage());
    }
}


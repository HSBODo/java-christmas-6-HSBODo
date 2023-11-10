package christmas.service.serviceimpl;

import christmas.constant.ChristmasPromotionException;
import christmas.service.ValidationService;

import java.util.Arrays;
import java.util.stream.Stream;

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

    private boolean isFormat(String reservationMenuAndQuantity){
        String[] reservationMenuAndQuantityCommaSplit = stringSplitComma(reservationMenuAndQuantity);

        Stream<String> reservationMenuAndQuantityStream = Arrays.stream(reservationMenuAndQuantityCommaSplit).
                filter(s -> s.contains("-"));
        if(reservationMenuAndQuantityCommaSplit.length==reservationMenuAndQuantityStream.count()){
            return true;
        }

        throw new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_FORMAT.getMessage());
    }

    public boolean isDigitQuantity(String reservationMenuAndQuantity){
        String[] reservationMenuAndQuantityCommaSplit = stringSplitComma(reservationMenuAndQuantity);
        for(String MenuAndQuantity:reservationMenuAndQuantityCommaSplit){
            String[] split = MenuAndQuantity.split("-");
            isDigit(split[1]);
        }
        return true;
    }

    private String[] stringSplitComma(String reservationMenuAndQuantity) {
        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
        return reservationMenuAndQuantityCommaSplit;
    }
}


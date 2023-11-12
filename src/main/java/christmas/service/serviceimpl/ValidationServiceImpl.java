package christmas.service.serviceimpl;

import christmas.constant.ChristmasPromotionException;
import christmas.constant.Menu;
import christmas.service.ValidationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ValidationServiceImpl implements ValidationService {
    private final int RESERVATION_DATE_RANGE_START = 1;
    private final int RESERVATION_DATE_RANGE_END = 31;
    private final int RESERVATION_QUANTITY_RANGE_START = 1;
    private final int RESERVATION_QUANTITY_RANGE_END = 20;
    private final String[] NECESSARY_INCLUDE_MENU={"디저트","메인","애피타이저"};


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
        try {
            //입력 형식 검증
            isFormat(reservationMenuAndQuantity);
            //입력 수량 검증
            isDigitQuantity(reservationMenuAndQuantity);
            isRangeQuantity(reservationMenuAndQuantity,RESERVATION_QUANTITY_RANGE_START,RESERVATION_QUANTITY_RANGE_END);
            //입력 메뉴 검증
            isMenu(reservationMenuAndQuantity);
            isDuplicationMenu(reservationMenuAndQuantity);
            isNotOnlyBeverage(reservationMenuAndQuantity);

            return true;
        }catch (Exception e){
            System.out.println(ChristmasPromotionException.INPUT_NOT_VALID_MENU_QUANTITY.getMessage());
            return false;
        }
    }

    private boolean isDigit(String input){
        try {
            Integer.parseInt(input);
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

    private boolean isDigitQuantity(String reservationMenuAndQuantity){
        String[] reservationMenuAndQuantityCommaSplit = stringSplitComma(reservationMenuAndQuantity);

        for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
            String[] menuAndQuantityHyphenSplit = stringSplitHyphen(menuAndQuantity);
            isDigit(menuAndQuantityHyphenSplit[1]);
        }

        return true;
    }

    private boolean isRangeQuantity(String reservationMenuAndQuantity,int start, int end){
        String[] reservationMenuAndQuantityCommaSplit = stringSplitComma(reservationMenuAndQuantity);
        int totalQuantity = 0;

        for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
            String[] menuAndQuantityHyphenSplit = stringSplitHyphen(menuAndQuantity);
            int quantity = Integer.parseInt(menuAndQuantityHyphenSplit[1]);
            if(quantity < start || quantity > end){
                throw new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_RANGE.getMessage());
            }
            totalQuantity += quantity;
        }

        if(totalQuantity>end){
            throw new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_RANGE.getMessage());
        }

        return true;
    }

    private boolean isMenu(String reservationMenuAndQuantity){
        String[] reservationMenuAndQuantityCommaSplit = stringSplitComma(reservationMenuAndQuantity);
        try {
            for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
                String[] menuAndQuantityHyphenSplit = stringSplitHyphen(menuAndQuantity);
                Menu.getMenu(menuAndQuantityHyphenSplit[0]);
            }
            return true;
        }catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_MENU.getMessage());
        }
    }

    private boolean isDuplicationMenu(String reservationMenuAndQuantity){
        List<String> menuNames = new ArrayList<>();
        String[] reservationMenuAndQuantityCommaSplit = stringSplitComma(reservationMenuAndQuantity);

        for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
            String[] menuAndQuantityHyphenSplit = stringSplitHyphen(menuAndQuantity);
            if(menuNames.contains(menuAndQuantityHyphenSplit[0])){
                throw new IllegalArgumentException(ChristmasPromotionException.INPUT_DUPLICATION_MENU.getMessage());
            }
            menuNames.add(menuAndQuantityHyphenSplit[0]);
        }
        return true;
    }

    private boolean isNotOnlyBeverage(String reservationMenuAndQuantity){
        List<String> menuCategory = new ArrayList<>();
        String[] reservationMenuAndQuantityCommaSplit = stringSplitComma(reservationMenuAndQuantity);

        for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
            String[] menuAndQuantityHyphenSplit = stringSplitHyphen(menuAndQuantity);
            Menu menu = Menu.getMenu(menuAndQuantityHyphenSplit[0]);
            menuCategory.add(menu.getCategory());
        }
        if(menuCategory.contains(NECESSARY_INCLUDE_MENU[0]) || menuCategory.contains(NECESSARY_INCLUDE_MENU[1]) || menuCategory.contains(NECESSARY_INCLUDE_MENU[2])){
            return true;
        }
        throw new IllegalArgumentException();
    }

    private String[] stringSplitHyphen(String reservationMenuAndQuantity) {
        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split("-");
        return reservationMenuAndQuantityCommaSplit;
    }

    private String[] stringSplitComma(String reservationMenuAndQuantity) {
        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
        return reservationMenuAndQuantityCommaSplit;
    }
}


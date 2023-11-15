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
    private final int RESERVATION_QUANTITY_RANGE_MIN = 1;
    private final int RESERVATION_QUANTITY_RANGE_MAX = 20;
    private final String[] NECESSARY_INCLUDE_MENU = {"디저트", "메인", "애피타이저"};

    @Override
    public boolean isValidReservationDay(String reservationDate) {
        try {
            isDigit(reservationDate);
            isRangeReservationDate(Integer.parseInt(reservationDate), RESERVATION_DATE_RANGE_START, RESERVATION_DATE_RANGE_END);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(ChristmasPromotionException.INPUT_NOT_VALID_DATE.getMessage());
            return false;
        }
    }

    @Override
    public boolean isValidReservationMenuAndQuantity(String reservationMenuAndQuantity) {
        try {
            isFormat(reservationMenuAndQuantity);
            isDigitQuantity(reservationMenuAndQuantity);
            isRangeQuantity(reservationMenuAndQuantity, RESERVATION_QUANTITY_RANGE_MIN, RESERVATION_QUANTITY_RANGE_MAX);
            isMenu(reservationMenuAndQuantity);
            isDuplicationMenu(reservationMenuAndQuantity);
            isNotOnlyBeverage(reservationMenuAndQuantity);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(ChristmasPromotionException.INPUT_NOT_VALID_MENU_QUANTITY.getMessage());
            return false;
        }
    }

    private boolean isDigit(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_DiGIT.getMessage());
        }
    }

    private boolean isRangeReservationDate(int reservationDate, int start, int end) {
        if (start <= reservationDate && end >= reservationDate) {
            return true;
        }

        throw new IllegalArgumentException(
                ChristmasPromotionException.INPUT_NOT_VALID_RANGE.getMessage());
    }

    private boolean isFormat(String reservationMenuAndQuantity) {
        String[] reservationMenuAndQuantityCommaSplit = stringSplitComma(reservationMenuAndQuantity);
        Stream<String> reservationMenuAndQuantityStream = Arrays.stream(reservationMenuAndQuantityCommaSplit).
                filter(s -> s.contains("-"));

        if (reservationMenuAndQuantityCommaSplit.length == reservationMenuAndQuantityStream.count()) {
            return true;
        }

        throw new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_FORMAT.getMessage());
    }

    private boolean isDigitQuantity(String reservationMenuAndQuantity) {
        String[] reservationMenuAndQuantityCommaSplit = stringSplitComma(reservationMenuAndQuantity);

        for (String menuAndQuantity : reservationMenuAndQuantityCommaSplit) {
            String[] menuAndQuantityHyphenSplit = stringSplitHyphen(menuAndQuantity);
            isDigit(menuAndQuantityHyphenSplit[1]);
        }

        return true;
    }

    private boolean isRangeQuantity(String reservationMenuAndQuantity, int min, int max) {
        String[] reservationMenuAndQuantityCommaSplit = stringSplitComma(reservationMenuAndQuantity);
        int totalQuantity = 0;

        for (String menuAndQuantity : reservationMenuAndQuantityCommaSplit) {
            String[] menuAndQuantityHyphenSplit = stringSplitHyphen(menuAndQuantity);
            int quantity = Integer.parseInt(menuAndQuantityHyphenSplit[1]);
            if (quantity < min || quantity > max) {
                throw new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_RANGE.getMessage());
            }
            totalQuantity += quantity;
        }

        return isOverMaxQuantity(totalQuantity, max);
    }

    private boolean isOverMaxQuantity(int totalQuantity, int maxQuantity) {
        if (totalQuantity > maxQuantity) {
            throw new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_RANGE.getMessage());
        }
        return true;
    }

    private boolean isMenu(String reservationMenuAndQuantity) {
        String[] reservationMenuAndQuantityCommaSplit = stringSplitComma(reservationMenuAndQuantity);
        try {
            for (String menuAndQuantity : reservationMenuAndQuantityCommaSplit) {
                String[] menuAndQuantityHyphenSplit = stringSplitHyphen(menuAndQuantity);
                Menu.getMenu(menuAndQuantityHyphenSplit[0]);
            }
            return true;
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_MENU.getMessage());
        }
    }

    private boolean isDuplicationMenu(String reservationMenuAndQuantity) {
        List<String> menuNames = new ArrayList<>();
        String[] reservationMenuAndQuantityCommaSplit = stringSplitComma(reservationMenuAndQuantity);

        for (String menuAndQuantity : reservationMenuAndQuantityCommaSplit) {
            String[] menuAndQuantityHyphenSplit = stringSplitHyphen(menuAndQuantity);
            if (menuNames.contains(menuAndQuantityHyphenSplit[0])) {
                throw new IllegalArgumentException(ChristmasPromotionException.INPUT_DUPLICATION_MENU.getMessage());
            }
            menuNames.add(menuAndQuantityHyphenSplit[0]);
        }
        return true;
    }

    private boolean isNotOnlyBeverage(String reservationMenuAndQuantity) {
        List<String> menuCategory = new ArrayList<>();
        String[] reservationMenuAndQuantityCommaSplit = stringSplitComma(reservationMenuAndQuantity);

        for (String menuAndQuantity : reservationMenuAndQuantityCommaSplit) {
            String[] menuAndQuantityHyphenSplit = stringSplitHyphen(menuAndQuantity);
            Menu menu = Menu.getMenu(menuAndQuantityHyphenSplit[0]);
            menuCategory.add(menu.getCategory());
        }
        if (menuCategory.contains(NECESSARY_INCLUDE_MENU[0]) || menuCategory.contains(NECESSARY_INCLUDE_MENU[1]) || menuCategory.contains(NECESSARY_INCLUDE_MENU[2])) {
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


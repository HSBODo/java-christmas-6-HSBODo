package christmas.constant;

public enum ChristmasPromotionException {
    ERROR("[ERROR] "),
    INPUT_NOT_VALID_DATE(ERROR.message+"유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INPUT_NOT_VALID_MENU_QUANTITY(ERROR.message+"유효하지 않은 주문입니다. 다시 입력해 주세요."),

    INPUT_NOT_DiGIT(ERROR.message+"숫자로 입력해 주세요."),
    INPUT_NOT_VALID_RANGE(ERROR.message+"유효하지 않는 범위입니다."),
    INPUT_NOT_VALID_FORMAT(ERROR.message+"유효하지 않은 포맷 형식입니다. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    INPUT_NOT_VALID_MENU(ERROR.message+"존재하지 않는 메뉴입니다."),
    INPUT_NOT_INCLUDE_NECESSARY_MENU(ERROR.message+"음료만 주문할 수 없습니다."),
    INPUT_DUPLICATION_MENU(ERROR.message+"메뉴가 중복됩니다.")
    ;

    final private String message;

    ChristmasPromotionException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

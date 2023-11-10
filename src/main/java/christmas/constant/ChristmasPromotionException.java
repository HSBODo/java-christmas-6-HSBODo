package christmas.constant;

public enum ChristmasPromotionException {
    ERROR("[ERROR] "),
    INPUT_NOT_DiGIT(ERROR.message+"숫자로 입력해 주세요."),
    INPUT_NOT_VALID_RANGE(ERROR.message+"유효하지 않는 범위입니다."),
    INPUT_NOT_VALID_DATE(ERROR.message+"유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    final private String message;

    ChristmasPromotionException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}

package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private final String RESERVATION_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private final String RESERVATION_MENU_QUANTITY = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public String inputReservationDate(){
        System.out.println(RESERVATION_DATE);
        return Console.readLine();
    }

    public String inputReservationMenuAndQuantity(){
        System.out.println(RESERVATION_MENU_QUANTITY);
        return Console.readLine();
    }
}

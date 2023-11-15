package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

    //추가 테스트

    @Test
    void 음료만_주문_시_주문할_수_없습니다() {
        assertSimpleTest(() -> {
            runException("26", "제로콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴는_한_번에_최대_20개까지만_주문할_수_있습니다() {
        assertSimpleTest(() -> {
            runException("26", "시저샐러드-5, 티본스테이크-8, 크리스마스파스타-7, 제로콜라-9, 아이스크림-3");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 총주문_금액_10000원_이상부터_이벤트가_적용됩니다() {
        assertSimpleTest(() -> {
            run("25", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"0","32","45","999","159423","일","십팔","asdasd","0.1","@#@%"})
    void 방문할_날짜는_1_이상_31_이하의_숫자로만_입력받아_주세요_예외케이스(String reservationDay) {
        assertSimpleTest(() -> {
            runException(reservationDay, "타파스-1,제로콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"1","2","8","11","17","24","27","18","13","31"})
    void 방문할_날짜는_1_이상_31_이하의_숫자로만_입력받아_주세요_정상케이스(String reservationDay) {
        assertSimpleTest(() -> {
            run(reservationDay, "타파스-1,제로콜라-1");
            assertThat(output()).contains("<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "8,500원");
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1/타파스-1,제로콜라-1,짜장면-5",
            "5/햄버거-1,제로콜라-1,짜장면-5",
            "18/타파스-1,치킨-1,짜장면-5",
            "25/족발-1,보쌈-1,짜장면-5",
            "31/타파스-1,제로콜라-1,짜장면-5"
    }, delimiter = '/')
    void  메뉴판에_없는_메뉴를_입력하는_경우_예외케이스(String reservationDay, String menuAndQuantity) {
        assertSimpleTest(() -> {
            runException(reservationDay, menuAndQuantity);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1/타파스-0,제로콜라-1,양송이수프-5",
            "5/바비큐립-1,제로콜라-0,아이스크림-5",
            "18/레드와인-7,해산물파스타-2,시저샐러드-0",
            "25/타파스-1,크리스마스파스타-0,초코케이크-0",
            "31/샴페인-0,크리스마스파스타-0,타파스-0"
    }, delimiter = '/')
    void  메뉴의_개수는_1_이상의_숫자만_입력_예외케이스(String reservationDay, String menuAndQuantity) {
        assertSimpleTest(() -> {
            runException(reservationDay, menuAndQuantity);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1/타파스_1,제로콜라_1,양송이수프_5",
            "5/바비큐립:1,제로콜라:4,아이스크림:5",
            "18/레드와인1,해산물파스타2,시저샐러드1",
            "25/타파스.1,크리스마스파스타.4,초코케이크.3",
            "31/샴페인=5,크리스마스파스타=6,타파스=2"
    }, delimiter = '/')
    void  메뉴_형식이_다른_경우_예외케이스(String reservationDay, String menuAndQuantity) {
        assertSimpleTest(() -> {
            runException(reservationDay, menuAndQuantity);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1/타파스-1,타파스-2,양송이수프-5",
            "5/바비큐립-1,바비큐립-1,아이스크림-5",
            "18/레드와인-7,레드와인-7,시저샐러드-2",
            "25/초코케이크-1,크리스마스파스타-3,초코케이크-1",
            "31/샴페인-2,크리스마스파스타-0,샴페인-2"
    }, delimiter = '/')
    void  중복_메뉴를_입력한_경우_예외케이스(String reservationDay, String menuAndQuantity) {
        assertSimpleTest(() -> {
            runException(reservationDay, menuAndQuantity);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "22/타파스-1,아이스크림-2,양송이수프-2"
    }, delimiter = '/')
    void  크리스마스_디데이_할인만_적용(String reservationDay, String menuAndQuantity) {
        assertSimpleTest(() -> {
            run(reservationDay, menuAndQuantity);
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "크리스마스 디데이 할인: -3,100원");
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "26/타파스-1,아이스크림-2,해산물파스타-2"
    }, delimiter = '/')
    void  평일_할인만_적용(String reservationDay, String menuAndQuantity) {
        assertSimpleTest(() -> {
            run(reservationDay, menuAndQuantity);
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "평일 할인: -4,046원");
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "29/타파스-1,아이스크림-2,해산물파스타-2"
    }, delimiter = '/')
    void  주말_할인만_적용(String reservationDay, String menuAndQuantity) {
        assertSimpleTest(() -> {
            run(reservationDay, menuAndQuantity);
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "주말 할인: -4,046원");
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "31/타파스-1,제로콜라-2,해산물파스타-2"
    }, delimiter = '/')
    void  특별_할인만_적용(String reservationDay, String menuAndQuantity) {
        assertSimpleTest(() -> {
            run(reservationDay, menuAndQuantity);
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "특별 할인: -1,000원");
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "26/해산물파스타-8"
    }, delimiter = '/')
    void  증정_이벤트만_적용(String reservationDay, String menuAndQuantity) {
        assertSimpleTest(() -> {
            run(reservationDay, menuAndQuantity);
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "증정 이벤트: -25,000원");
            assertThat(output()).contains("<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개");
        });
    }

}

package christmas.service.serviceimpl;

import christmas.constant.ChristmasPromotionException;

import christmas.constant.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;



class ValidationServiceImplTest {

    ValidationServiceImpl validationServiceImpl;

    @BeforeEach
    public void createValidationServiceImpl(){
        validationServiceImpl = new ValidationServiceImpl();
    }

    @ParameterizedTest
    @ValueSource(strings = {"15"})
    @DisplayName("입력 값이 숫자형이면 true를 반환한다")
    public void isDigit_정상케이스(String number) {
        Object result;

        try {
            Integer.parseInt(number);
            result = true;
        } catch (NumberFormatException e) {
            result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_DiGIT.getMessage());
        }

        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"십오"})
    @DisplayName("입력 값이 숫자형이 아니면 예외를 발생한다.")
    public void isDigit_예외케이스(String number) {
        Object result;

        try {
            Integer.parseInt(number);
            result = true;
        } catch (NumberFormatException e) {
            result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_DiGIT.getMessage());
        }

        assertThat(result).isNotEqualTo(true);
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,31", "5,1,31", "15,1,31", "20,1,31", "31,1,31"}, delimiter = ',')
    @DisplayName("입력 값이 범위에 포함되면 true를 반환한다.")
    public void isRangeReservationDate_정상케이스(int reservationDate, int start, int end) {
        Object result;

        if (start <= reservationDate && end >= reservationDate) {
            result = true;
        }else {
            result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_RANGE.getMessage());
        }

        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource(value = {"32,1,31", "0,1,31", "33,1,31", "34,1,31", "100,1,31"}, delimiter = ',')
    @DisplayName("입력 값이 범위에 포함되지 않으면 예외를 반환한다.")
    public void isRangeReservationDate_예외케이스(int reservationDate, int start, int end) {
        Object result;

        if (start <= reservationDate && end >= reservationDate) {
            result = true;
        }
        result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_RANGE.getMessage());

        assertThat(result).isNotEqualTo(true);
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-2", "해산물파스타-2,레드와인-1,초코케이크-1"})
    @DisplayName("입력 값이 형식에 맞으면 true를 반환한다.")
    public void isFormat_정상케이스(String reservationMenuAndQuantity) {
        Object result;

        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
        Stream<String> reservationMenuAndQuantityStream = Arrays.stream(reservationMenuAndQuantityCommaSplit).
                filter(s -> s.contains("-"));

        if(reservationMenuAndQuantityCommaSplit.length==reservationMenuAndQuantityStream.count()){
            result = true;
        }else {
            result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_FORMAT.getMessage());
        }

        assertThat(result).isEqualTo(true);
        assertThat(result).isNotInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "해산물파스타2", "해산물파스타2,레드와인.1,초코케이크31"})
    @DisplayName("입력 값이 형식에 맞지 않으면 예외를 반환한다.")
    public void isFormat_예외케이스(String reservationMenuAndQuantity) {
        Object result ;

        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
        Stream<String> reservationMenuAndQuantityStream = Arrays.stream(reservationMenuAndQuantityCommaSplit).
                filter(s -> s.contains("-"));

        if(reservationMenuAndQuantityCommaSplit.length==reservationMenuAndQuantityStream.count()){
            result = true;
        }else {
            result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_FORMAT.getMessage());
        }

        assertThat(result).isNotEqualTo(true);
        assertThat(result).isInstanceOf(IllegalArgumentException.class);

    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-0", "해산물파스타-2,레드와인-1,초코케이크-1"})
    @DisplayName("입력 값의 메뉴의 개수가 숫자이면 true를 반환한다.")
    void isDigitQuantity_정상케이스(String reservationMenuAndQuantity) {
        Object result;

        try {
            String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
            for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
                String[] split = menuAndQuantity.split("-");
                Integer.parseInt(split[1]);
            }
            result = true;
        }catch (Exception e){
            result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_DiGIT.getMessage());
        }

        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-삼", "해산물파스타-사,레드와인-일,초코케이크-a"})
    @DisplayName("입력 값의 메뉴의 개수가 숫자가아니면 예외를 반환한다.")
    void isDigitQuantity_예외케이스(String reservationMenuAndQuantity) {
        Object result;

        try {
            String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
            for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
                String[] menuAndQuantityHyphenSplit = menuAndQuantity.split("-");
                Integer.parseInt(menuAndQuantityHyphenSplit[1]);
            }
            result = true;
        }catch (Exception e){
            result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_DiGIT.getMessage());
        }

        assertThat(result).isNotEqualTo(true);
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-1", "해산물파스타-2,레드와인-1,초코케이크-1"})
    @DisplayName("입력 값의 메뉴의 개수가 범위에 포함되면 true를 반환한다.")
    void isRangeQuantity_정상케이스(String reservationMenuAndQuantity) {
        Object result;

        try {
            String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
            for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
                String[] menuAndQuantityHyphenSplit = menuAndQuantity.split("-");
                int quantity = Integer.parseInt(menuAndQuantityHyphenSplit[1]);

                if(quantity<1 || quantity>20){
                    throw new IllegalArgumentException();
                }
            }
            result = true;
        }catch (Exception e){
            result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_RANGE.getMessage());
        }

        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-0", "해산물파스타-21,레드와인-1,초코케이크-1"})
    @DisplayName("입력 값의 메뉴의 개수가 범위에 포함되지 않으면 예외를 반환한다.")
    void isRangeQuantity_예외케이스(String reservationMenuAndQuantity) {
        Object result;

        try {
            String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
            for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
                String[] menuAndQuantityHyphenSplit = menuAndQuantity.split("-");
                int quantity = Integer.parseInt(menuAndQuantityHyphenSplit[1]);

                if(quantity<1 || quantity>20){
                    throw new IllegalArgumentException();
                }
            }
            result = true;
        }catch (Exception e){
            result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_RANGE.getMessage());
        }

        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-0", "해산물파스타-21,레드와인-1,초코케이크-1"})
    @DisplayName("입력 값의 메뉴가 존재하면 true를 반환한다.")
    void isMenu_정상케이스(String reservationMenuAndQuantity){
        Object result;

        try {
            String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
            for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
                String[] menuAndQuantityHyphenSplit = menuAndQuantity.split("-");
                Menu.getMenu(menuAndQuantityHyphenSplit[0]);
            }
            result = true;
        }catch (IndexOutOfBoundsException e){
            result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_MENU.getMessage());
        }

        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"짜장면-0", "해산물파스타-21,짬뽕-1,초코케이크-1","탕수육-21,티본스테이크-1,초코케이크-1"})
    @DisplayName("입력 값의 메뉴가 존재하지 않으면 예외를 반환한다.")
    void isMenu_예외케이스(String reservationMenuAndQuantity){
        Object result;

        try {
            String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
            for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
                String[] menuAndQuantityHyphenSplit = menuAndQuantity.split("-");
                Menu.getMenu(menuAndQuantityHyphenSplit[0]);
            }
            result = true;
        }catch (IndexOutOfBoundsException e){
            result = new IllegalArgumentException(ChristmasPromotionException.INPUT_NOT_VALID_MENU.getMessage());
        }

        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-1", "해산물파스타-1,티본스테이크-2,초코케이크-1","해산물파스타-2,티본스테이크-1,초코케이크-1"})
    @DisplayName("입력 값의 메뉴가 중복되지 않으면 true를 반환한다.")
    void isDuplicationMenu_정상케이스(String reservationMenuAndQuantity){
        Object result;

        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
        List<String> menus = new ArrayList<>();
        try {
            for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
                String[] menuAndQuantityHyphenSplit = menuAndQuantity.split("-");
                if(menus.contains(menuAndQuantityHyphenSplit[0])){
                    throw new IllegalArgumentException(ChristmasPromotionException.INPUT_DUPLICATION_MENU.getMessage());
                }
                menus.add(menuAndQuantityHyphenSplit[0]);
            }
            result = true;
        }catch (Exception e){
            result = e;
        }

        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-1,해산물파스타-2,초코케이크-1","초코케이크-2,티본스테이크-1,초코케이크-1"})
    @DisplayName("입력 값의 메뉴가 중복되면 예외를 반환한다.")
    void isDuplicationMenu_예외케이스(String reservationMenuAndQuantity){
        Object result;

        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
        List<String> menus = new ArrayList<>();

        try {
            for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
                String[] menuAndQuantityHyphenSplit = menuAndQuantity.split("-");
                if(menus.contains(menuAndQuantityHyphenSplit[0])){
                    throw new IllegalArgumentException(ChristmasPromotionException.INPUT_DUPLICATION_MENU.getMessage());
                }
                menus.add(menuAndQuantityHyphenSplit[0]);
            }
            result = true;
        }catch (Exception e){
            result = e;
        }

        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-1,제로콜라-2,초코케이크-1","레드와인-2,티본스테이크-1,초코케이크-1"})
    @DisplayName("입력 값의 메뉴가 음료만 존재하지 않으면 true를 반환한다.")
    public void isNotOnlyBeverage_정상케이스(String reservationMenuAndQuantity) {
        Object result;

        List<String> menuCategory = new ArrayList<>();
        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");

        for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
            String[] menuAndQuantityHyphenSplit = menuAndQuantity.split("-");
            Menu menu = Menu.getMenu(menuAndQuantityHyphenSplit[0]);
            menuCategory.add(menu.getCategory());
        }

        if(menuCategory.contains("디저트") || menuCategory.contains("메인") || menuCategory.contains("애피타이저")){
            result = true;
        }else{
            result = new IllegalArgumentException();
        }

        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-1","레드와인-2","샴페인-3"})
    @DisplayName("입력 값의 메뉴가 음료만 존재하면 예외를 반환한다.")
    public void isNotOnlyBeverage_예외케이스(String reservationMenuAndQuantity) {
        Object result;

        List<String> menuCategory = new ArrayList<>();
        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");

        for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
            String[] menuAndQuantityHyphenSplit = menuAndQuantity.split("-");
            Menu menu = Menu.getMenu(menuAndQuantityHyphenSplit[0]);
            menuCategory.add(menu.getCategory());
        }

        if(menuCategory.contains("디저트") || menuCategory.contains("메인") || menuCategory.contains("애피타이저")){
            result = true;
        }else{
            result = new IllegalArgumentException();
        }

        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }
}
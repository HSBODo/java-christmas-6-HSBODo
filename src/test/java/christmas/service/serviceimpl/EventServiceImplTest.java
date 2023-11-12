package christmas.service.serviceimpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EventServiceImplTest {

    @ParameterizedTest
    @ValueSource(ints = {10000,11000,20000,35000})
    @DisplayName("이벤트적용 가능하면 true를 반환한다.")
    void isApplyEvent_정상케이스(int totalPriceBeforeDiscount) {
        Object result;
        if(totalPriceBeforeDiscount<10000){
            result = false;
        }else {
            result = true;
        }
        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(ints = {9000,8000,1000,5000})
    @DisplayName("이벤트적용 가능하지 않으면 false를 반환한다.")
    void isApplyEvent_예외케이스(int totalPriceBeforeDiscount) {
        Object result;
        if(totalPriceBeforeDiscount<10000){
            result = false;
        }else {
            result = true;
        }
        assertThat(result).isEqualTo(false);
    }
}
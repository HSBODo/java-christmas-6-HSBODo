package christmas.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BadgeTest {

    @ParameterizedTest
    @ValueSource(ints = {-20000,-30000,-40000,-50000})
    @DisplayName("총 혜택금액이 2만원 이상이면 산타 배지 부여")
    void getBadge_산타(int totalBenefitsPrice) {
        Badge badge = Badge.getBadge(totalBenefitsPrice);
        System.out.println("badge = " + badge.getName());
        assertThat(badge.getName()).isEqualTo("산타");
    }

    @ParameterizedTest
    @ValueSource(ints = {-10000,-11000,-12000,-15000,-19000})
    @DisplayName("총 혜택금액이 1만원 이상이면 트리 배지 부여")
    void getBadge_트리(int totalBenefitsPrice) {
        Badge badge = Badge.getBadge(totalBenefitsPrice);
        assertThat(badge.getName()).isEqualTo("트리");
    }

    @ParameterizedTest
    @ValueSource(ints = {-5000,-6000,-7777,-8154,-9999})
    @DisplayName("총 혜택금액이 5천원 이상이면 별 배지 부여")
    void getBadge_별(int totalBenefitsPrice) {
        Badge badge = Badge.getBadge(totalBenefitsPrice);
        System.out.println("badge = " + badge.getName());
        assertThat(badge.getName()).isEqualTo("별");
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1,-3333,-4854,-4999})
    @DisplayName("총 혜택금액이 5천원 미만이면 없음 배지 부여")
    void getBadge_없음(int totalBenefitsPrice) {
        Badge badge = Badge.getBadge(totalBenefitsPrice);
        System.out.println("badge = " + badge.getName());
        assertThat(badge.getName()).isEqualTo("없음");
    }
}
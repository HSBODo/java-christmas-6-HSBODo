package christmas.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Badge {
    SANTA("산타", -20000),
    TREE("트리", -10000),
    START("별", -5000),
    EMPTY("없음", 0),
    ;

    private static final int BADGE_INDEX = 0;

    private final String name;
    private final int moreThanOrEqualTo;

    Badge(String name, int moreThanOrEqualTo) {
        this.name = name;
        this.moreThanOrEqualTo = moreThanOrEqualTo;
    }

    public static Badge getBadge(int totalBenefitsPrice) {
        List<Badge> badges = Arrays.stream(Badge.values()).filter(badge ->
                badge.getMoreThanOrEqualTo() >= totalBenefitsPrice
        ).collect(Collectors.toList());

        return badges.get(BADGE_INDEX);
    }

    public String getName() {
        return name;
    }

    public int getMoreThanOrEqualTo() {
        return moreThanOrEqualTo;
    }
}

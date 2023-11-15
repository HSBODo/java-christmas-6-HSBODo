package christmas.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DayOfTheWeek {
    월요일(1,"평일"),
    화요일(2,"평일"),
    수요일(3,"평일"),
    목요일(4,"평일"),
    금요일(5,"주말"),
    토요일(6,"주말"),
    일요일(7,"평일")
    ;
    private static final int DAY_OF_THE_WEEK_INDEX = 0;

    final private int value;
    final private String category;

    DayOfTheWeek(int value, String category) {
        this.value = value;
        this.category = category;
    }

    public static DayOfTheWeek getDayOfTheWeek(int dayOfWeek) {
        List<DayOfTheWeek> DayOfTheWeeks = Arrays.stream(DayOfTheWeek.values())
                .filter(dayOfTheWeek ->
                        dayOfTheWeek.getValue() == dayOfWeek)
                .collect(Collectors.toList());
        return DayOfTheWeeks.get(DAY_OF_THE_WEEK_INDEX);
    }

    public int getValue(){
        return value;
    }

    public String getCategory() {
        return category;
    }
}

package christmas.constant;

public enum DayOfWeek {
    월요일(1),
    화요일(2),
    수요일(3),
    목요일(4),
    금요일(5),
    토요일(6),
    일요일(7)
    ;

    final private int value;

    DayOfWeek(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}

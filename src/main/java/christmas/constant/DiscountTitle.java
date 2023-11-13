package christmas.constant;

public enum DiscountTitle {
    크리스마스_디데이_할인("크리스마스 디데이 할인"),
    평일_할인("평일 할인"),
    주말_할인("주말 할인"),
    특별_할인("특별 할인"),
    증정_이벤트("증정 이벤트");

    final private String title;

    DiscountTitle(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }
}

package christmas.view;

import christmas.model.Model;

public class OutputView {

    private final String ORDER_MENU_TITLE = "<주문 메뉴>";
    private final String TOTAL_PRICE_BEFORE_DISCOUNT_TITLE = "<할인 전 총주문 금액>";
    private final String GIVEAWAY_TITLE = "<증정 메뉴>";
    private final String BENEFITS_DETAILS_TITLE = "<혜택 내역>";
    private final String TOTAL_BENEFITS_PRICE_TITLE = "<총혜택 금액>";
    private final String TOTAL_PRICE_AFTER_DISCOUNT_TITLE = "<할인 후 예상 결제 금액>";
    private final String BADGE_TITLE = "<12월 이벤트 배지>";
    private final String UNIT = "개";
    private final String WON = "원";

    public void firstGreeting(Model model){
        System.out.println(model.getModel().get("firstGreeting"));
    }

    public void orderMenu(Model model){
        System.out.println(ORDER_MENU_TITLE);
        model.getModel().forEach((menuName, quantity) ->{
            System.out.println(menuName+" "+quantity+UNIT);
        });
        System.out.println();
    }
    
    public void totalPriceBeforeDiscount(Model model){
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT_TITLE);
        System.out.println(model.getModel().get("totalPriceBeforeDiscount")+WON);
        System.out.println();
    }

    public void giveaway(Model model){
        System.out.println(GIVEAWAY_TITLE);
        System.out.println(model.getModel().get("giveWay")+" "+(model.getModel().get("quantity")+UNIT));
        System.out.println();
    }

    public void benefitsDetails(Model model){
        System.out.println(BENEFITS_DETAILS_TITLE);
        model.getModel().forEach((discountTitle, discountPrice) -> {
            System.out.println(discountTitle+": "+discountPrice+WON);
        });
        System.out.println();
    }

    public void totalBenefitsPrice(Model model){
        System.out.println(TOTAL_BENEFITS_PRICE_TITLE);
        System.out.println(model.getModel().get("totalBenefitsPrice"));
        System.out.println();
    }

    public void totalPriceAfterDiscount(Model model){
        System.out.println(TOTAL_PRICE_AFTER_DISCOUNT_TITLE);
        System.out.println(model.getModel().get("totalPriceAfterDiscount"));
        System.out.println();
    }

    public void badge(Model model){
        System.out.println(BADGE_TITLE);
        System.out.println(model.getModel().get("badge"));
    }


}

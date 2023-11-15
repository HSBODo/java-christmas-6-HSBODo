package christmas.domain;

import christmas.constant.BenefitsTitle;
import christmas.constant.Menu;
import christmas.model.Model;

import java.util.Map;

public class ReservationInfo {
    private int reservationDay;
    private Map<Menu,Integer> reservationMenusQuantity;
    private String totalPriceBeforeDiscount;
    private String totalPriceAfterDiscount;
    private String totalDiscountPrice;
    private String totalBenefitsPrice;
    private Map<BenefitsTitle,String> benefitsDetails;
    private Map<String,Integer> giveaway;
    private String badge;


    public ReservationInfo(int reservationDay, Map<Menu, Integer> reservationMenusQuantity, String totalPriceBeforeDiscount, String totalPriceAfterDiscount, String totalDiscountPrice, String totalBenefitsPrice, Map<BenefitsTitle, String> benefitsDetails, Map<String,Integer> giveaway, String badge) {
        this.reservationDay = reservationDay;
        this.reservationMenusQuantity = reservationMenusQuantity;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.totalPriceAfterDiscount = totalPriceAfterDiscount;
        this.totalDiscountPrice = totalDiscountPrice;
        this.totalBenefitsPrice = totalBenefitsPrice;
        this.benefitsDetails = benefitsDetails;
        this.giveaway = giveaway;
        this.badge = badge;
    }

    public Model toEventBenefitsPreviewModel(){
        final String PREVIEW = "12월 "+reservationDay+"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
        Model model = new Model();
        model.addAttribute("preview",PREVIEW);
        return model;
    }

    public Model toOrderMenuModel(){
        Model model = new Model();
        reservationMenusQuantity.forEach((menu, quantity) -> {
            model.addAttribute(menu.getName(),quantity);
        });
        return model;
    }

    public Model toTotalPriceBeforeDiscountModel(){
        Model model = new Model();
        model.addAttribute("totalPriceBeforeDiscount",totalPriceBeforeDiscount);
        return model;
    }

    public Model toGiveawayModel(){
        Model model = new Model();
        giveaway.forEach((giveaway, quantity) -> {
            model.addAttribute("giveaway",giveaway);
            model.addAttribute("quantity",quantity);
        });
        return model;
    }

    public Model toBenefitsDetailsModel(){
        Model model = new Model();
        benefitsDetails.forEach((benefitsTitle, benefitsPrice) -> {
            model.addAttribute(benefitsTitle.getTitle(),benefitsPrice);
        });
        return model;
    }

    public Model toTotalBenefitsPriceModel(){
        Model model = new Model();
        model.addAttribute("totalBenefitsPrice",totalBenefitsPrice);
        return model;
    }

    public Model toTotalPriceAfterDiscountModel(){
        Model model = new Model();
        model.addAttribute("totalPriceAfterDiscount",totalPriceAfterDiscount);
        return model;
    }

    public Model toBadgeModel(){
        Model model = new Model();
        model.addAttribute("badge",badge);
        return model;
    }

}

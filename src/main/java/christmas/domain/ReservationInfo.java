package christmas.domain;

import christmas.constant.BenefitsTitle;
import christmas.constant.Menu;
import christmas.model.Model;

import java.util.List;
import java.util.Map;

public class ReservationInfo {
    private int reservationDay;
    private List<Menu> reservationMenus;
    private Map<Menu,Integer> menusQuantity;
    private String totalPriceBeforeDiscount;
    private String totalPriceAfterDiscount;
    private String totalDiscountPrice;
    private String totalBenefitsPrice;
    private Map<BenefitsTitle,String> benefitsDetails;
    private String giveaway;
    private String badge;


    public ReservationInfo(int reservationDay, List<Menu> reservationMenus, Map<Menu, Integer> menusQuantity, String totalPriceBeforeDiscount, String totalPriceAfterDiscount, String totalDiscountPrice, String totalBenefitsPrice, Map<BenefitsTitle, String> benefitsDetails, String giveaway, String badge) {
        this.reservationDay = reservationDay;
        this.reservationMenus = reservationMenus;
        this.menusQuantity = menusQuantity;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.totalPriceAfterDiscount = totalPriceAfterDiscount;
        this.totalDiscountPrice = totalDiscountPrice;
        this.totalBenefitsPrice = totalBenefitsPrice;
        this.benefitsDetails = benefitsDetails;
        this.giveaway = giveaway;
        this.badge = badge;
    }

    public Model toOrderMenuModel(){
        Model model = new Model();
        menusQuantity.forEach((menu, quantity) -> {
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
        model.addAttribute("giveWay",giveaway);
        model.addAttribute("quantity",1);
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

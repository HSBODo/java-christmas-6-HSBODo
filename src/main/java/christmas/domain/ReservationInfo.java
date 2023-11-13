package christmas.domain;

import christmas.constant.DiscountTitle;
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
    private Map<DiscountTitle,String> discountDetails;
    private String giveaway;
    private String badge;


    public ReservationInfo(int reservationDay, List<Menu> reservationMenus, Map<Menu, Integer> menusQuantity, String totalPriceBeforeDiscount, String totalPriceAfterDiscount, String totalDiscountPrice, Map<DiscountTitle, String> discountDetails, String giveaway, String badge) {
        this.reservationDay = reservationDay;
        this.reservationMenus = reservationMenus;
        this.menusQuantity = menusQuantity;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.totalPriceAfterDiscount = totalPriceAfterDiscount;
        this.totalDiscountPrice = totalDiscountPrice;
        this.discountDetails = discountDetails;
        this.giveaway = giveaway;
        this.badge = badge;
    }

    public Model toOrderMenuModel(){
        Model model = new Model();
        menusQuantity.forEach((menuName, quantity) -> {
            model.addAttribute(menuName,quantity);
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

    public Model toDiscountDetailsModel(){
        Model model = new Model();
        discountDetails.forEach((discountTitle, discountPrice) -> {
            model.addAttribute(discountTitle,discountPrice);
        });
        return model;
    }

    public Model toTotalDiscountPriceModel(){
        Model model = new Model();
        model.addAttribute("totalDiscountPrice",totalDiscountPrice);
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

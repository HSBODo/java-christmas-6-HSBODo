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
}

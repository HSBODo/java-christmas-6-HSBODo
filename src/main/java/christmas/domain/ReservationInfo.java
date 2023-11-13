package christmas.domain;

import christmas.constant.DiscountTitle;
import christmas.constant.Menu;

import java.util.List;
import java.util.Map;

public class ReservationInfo {
    private int reservationDay;
    private List<Menu> reservationMenus;
    private Map<Menu,Integer> menusQuantity;
    private int totalPriceBeforeDiscount;
    private int totalPriceAfterDiscount;
    private int totalDiscountPrice;
    private Map<DiscountTitle,Integer> discountDetails;
    private String giveaway;
    private String badge;


    public ReservationInfo(int reservationDay, List<Menu> reservationMenus, Map<Menu, Integer> menusQuantity, int totalPriceBeforeDiscount, int totalPriceAfterDiscount, int totalDiscountPrice, Map<DiscountTitle, Integer> discountDetails, String giveaway, String badge) {
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

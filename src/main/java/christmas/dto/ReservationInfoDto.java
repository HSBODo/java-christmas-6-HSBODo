package christmas.dto;

import christmas.constant.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationInfoDto {
    private int reservationDay;
    private List<Menu> reservationMenus;
    private Map<Menu,Integer> menusQuantity;
    private int totalPriceBeforeDiscount;
    private int totalPriceAfterDiscount;
    private int totalDiscountPrice;
    private Map<DiscountTitle,Integer> discountDetails;
    private String giveaway;
    private String badge;

    public ReservationInfoDto(String reservationDay, String reservationMenuAndQuantity) {
        this.reservationDay = Integer.parseInt(reservationDay);
        this.discountDetails = new HashMap<>();
        initReservationMenus(reservationMenuAndQuantity);
        initTotalPriceBeforeDiscount();
    }

    private void initReservationMenus(String reservationMenuAndQuantity){
        reservationMenus = new ArrayList<>();
        menusQuantity = new HashMap<>();
        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
        for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
            String[] menuAndQuantityHyphenSplit = menuAndQuantity.split("-");
            Menu menu = Menu.getMenu(menuAndQuantityHyphenSplit[0]);
            int quantity = Integer.parseInt(menuAndQuantityHyphenSplit[1]);
            reservationMenus.add(menu);
            menusQuantity.put(menu,quantity);
        }
    }

    private void initTotalPriceBeforeDiscount(){
        menusQuantity.forEach((menu, quantity) -> {
            totalPriceBeforeDiscount += menu.getPrice() * quantity;
        });
    }

    public int getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public List<Menu> getReservationMenus() {
        return reservationMenus;
    }

    public int getQuantityOf(String menuName){
        return menusQuantity.get(Menu.getMenu(menuName));
    }

    public int getReservationDay() {
        return reservationDay;
    }

    public int getTotalPriceAfterDiscount() {
        totalPriceAfterDiscount = totalPriceBeforeDiscount - totalDiscountPrice;

        return totalPriceAfterDiscount;
    }

    public int getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public String getBadge() {
        return badge;
    }

    public String getGiveaway() {
        return giveaway;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public void setGiveaway(DiscountTitle discountTitle, Menu menu) {
        this.giveaway = menu.getName();
        totalDiscountPrice += menu.getPrice();
        discountDetails.put(discountTitle,menu.getPrice());
    }

    public void applyDiscountPrice(DiscountTitle discountTitle, int discountPrice){
        discountDetails.put(discountTitle,discountPrice);
        totalDiscountPrice += discountPrice;
    }
}

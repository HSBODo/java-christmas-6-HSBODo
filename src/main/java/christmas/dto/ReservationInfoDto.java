package christmas.dto;

import christmas.constant.Menu;

import java.util.ArrayList;
import java.util.List;

public class ReservationInfoDto {
    private int reservationDay;
    private List<Menu> reservationMenus;
    private int totalPriceBeforeDiscount;
    private int totalPriceAfterDiscount;
    private int totalDiscountPrice;
    private String giveaway;
    private String badge;

    public ReservationInfoDto(String reservationDate, String reservationMenuAndQuantity) {
        this.reservationDay = Integer.parseInt(reservationDate);
        initReservationMenus(reservationMenuAndQuantity);
        initTotalPriceBeforeDiscount();
    }

    private void initReservationMenus(String reservationMenuAndQuantity){
        reservationMenus = new ArrayList<>();
        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
        for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
            String[] menuAndQuantityHyphenSplit = menuAndQuantity.split("-");
            reservationMenus.add(Menu.getMenu(menuAndQuantityHyphenSplit[0]));
        }
    }

    private void initTotalPriceBeforeDiscount(){
        reservationMenus.stream().forEach(menu -> {
            totalPriceBeforeDiscount+=menu.getPrice();
        });

    }

    public int getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public List<Menu> getReservationMenus() {
        return reservationMenus;
    }

    public int getReservationDay() {
        return reservationDay;
    }

    public void setGiveaway(String giveaway) {
        this.giveaway = giveaway;
    }
}

package christmas.dto;

import christmas.constant.Menu;

import java.util.ArrayList;
import java.util.List;

public class ReservationInfoDto {
    private String reservationDate;
    private List<Menu> reservationMenus;
    private int totalPriceBeforeDiscount;
    private int totalPriceAfterDiscount;
    private int totalDiscountPrice;
    private String giveaway;
    private String badge;

    public ReservationInfoDto(String reservationDate, String reservationMenuAndQuantity) {
        this.reservationDate = reservationDate;
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

    public String getReservationDate() {
        return reservationDate;
    }
}

package christmas.dto;

import christmas.constant.BenefitsTitle;
import christmas.constant.Menu;
import christmas.domain.ReservationInfo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationInfoDto {
    private int reservationDay;
    private Map<Menu,Integer> reservationMenusQuantity;
    private int totalPriceBeforeDiscount;
    private int totalDiscountPrice;
    private Map<BenefitsTitle,Integer> benefitsDetails;
    private String giveaway;
    private String badge;

    public ReservationInfoDto(String reservationDay, String reservationMenuAndQuantity) {
        this.reservationDay = Integer.parseInt(reservationDay);
        this.benefitsDetails = new HashMap<>();
        initReservationMenusQuantity(reservationMenuAndQuantity);
        initTotalPriceBeforeDiscount();
    }

    private void initReservationMenusQuantity(String reservationMenuAndQuantity){
        reservationMenusQuantity = new HashMap<>();
        String[] reservationMenuAndQuantityCommaSplit = reservationMenuAndQuantity.split(",");
        for(String menuAndQuantity:reservationMenuAndQuantityCommaSplit){
            String[] menuAndQuantityHyphenSplit = menuAndQuantity.split("-");
            Menu menu = Menu.getMenu(menuAndQuantityHyphenSplit[0]);
            int quantity = Integer.parseInt(menuAndQuantityHyphenSplit[1]);
            reservationMenusQuantity.put(menu,quantity);
        }
    }

    private void initTotalPriceBeforeDiscount(){
        reservationMenusQuantity.forEach((menu, quantity) -> {
            totalPriceBeforeDiscount += menu.getPrice() * quantity;
        });
    }

    public int getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public int getQuantityOf(String menuName){
        return reservationMenusQuantity.get(Menu.getMenu(menuName));
    }

    public int getReservationDay() {
        return reservationDay;
    }

    public int getTotalPriceAfterDiscount() {
        return  totalPriceBeforeDiscount - totalDiscountPrice;
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

    public Map<Menu, Integer> getReservationMenusQuantity() {
        return reservationMenusQuantity;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public void applyGiveaway(BenefitsTitle discountTitle, Menu menu) {
        this.giveaway = menu.getName();
        benefitsDetails.put(discountTitle,menu.getPrice());
    }

    public void applyDiscountPrice(BenefitsTitle discountTitle, int discountPrice){
        benefitsDetails.put(discountTitle,discountPrice);
        totalDiscountPrice += discountPrice;
    }

    public ReservationInfo toEntity(){

        return new ReservationInfo(
                reservationDay,
                reservationMenusQuantity,
                thousandUnitsComma(totalPriceBeforeDiscount),
                thousandUnitsComma(getTotalPriceAfterDiscount()),
                "-"+thousandUnitsComma(totalDiscountPrice),
                "-"+thousandUnitsComma(getTotalBenefitsPrice()),
                benefitsPriceOfBenefitsDetailsConvertThousandUnits(benefitsDetails),
                giveaway,
                badge
        );
    }

    private String thousandUnitsComma(int number){
        return NumberFormat.getInstance().format(number);
    }

    private Map<BenefitsTitle,String> benefitsPriceOfBenefitsDetailsConvertThousandUnits(Map<BenefitsTitle,Integer> benefitsDetails){
        Map<BenefitsTitle,String> discountDetailsThousandUnits = new HashMap<>();
        benefitsDetails.forEach((benefitsTitle, benefitsPrice) -> {
            discountDetailsThousandUnits.put(benefitsTitle,"-"+thousandUnitsComma(benefitsPrice));
        });
        return discountDetailsThousandUnits;
    }

    public int getTotalBenefitsPrice(){
        int benefitsPrice = 0;
        for(BenefitsTitle benefitsTitle : benefitsDetails.keySet()){
            benefitsPrice += benefitsDetails.get(benefitsTitle);
        }
        return benefitsPrice;
    }
}

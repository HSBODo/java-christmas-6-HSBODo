package christmas.controller;

import christmas.domain.ReservationInfo;
import christmas.dto.ReservationInfoDto;
import christmas.model.Model;
import christmas.service.EventService;
import christmas.service.ValidationService;
import christmas.service.serviceimpl.EventServiceImpl;
import christmas.service.serviceimpl.ValidationServiceImpl;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasPromotionController {

    private InputView inputView;
    private OutputView outputView;
    private ValidationService validationService;
    private EventService eventService;

    public ChristmasPromotionController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.validationService = new ValidationServiceImpl();
        this.eventService = new EventServiceImpl();
    }

    public void reservation(){
        firstGreetingModelAndView();

        String reservationDay = inputReservationDay();
        String reservationMenuAndQuantity = inputReservationMenuAndQuantity();
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(reservationDay,reservationMenuAndQuantity);

        reservationInfoDto = eventService.applyDecemberEvent(reservationInfoDto);
        ReservationInfo reservationInfo = reservationInfoDto.toEntity();

        reservationResult(reservationInfo);
    }

    private String inputReservationDay(){
        boolean isValid = false;
        String reservationDay = null;

        while (!isValid){
            reservationDay = inputView.inputReservationDay();
            isValid = validationService.isValidReservationDay(reservationDay);
        }

        return reservationDay;
    }

    private String inputReservationMenuAndQuantity(){
        boolean isValid = false;
        String reservationMenuAndQuantity = null;

        while (!isValid){
            reservationMenuAndQuantity = inputView.inputReservationMenuAndQuantity();
            isValid = validationService.isValidReservationMenuAndQuantity(reservationMenuAndQuantity);
        }

        return reservationMenuAndQuantity;
    }

    private void firstGreetingModelAndView(){
        final String FIRST_GREETING_MESSAGE  = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
        Model model = new Model();
        model.addAttribute("firstGreeting",FIRST_GREETING_MESSAGE );
        outputView.firstGreeting(model);
    }

    private void reservationResult(ReservationInfo reservationInfo) {
        eventBenefitsPreviewModelAndView(reservationInfo.toEventBenefitsPreviewModel());
        orderMenuModelAndView(reservationInfo.toOrderMenuModel());
        totalPriceBeforeDiscountModelAndView(reservationInfo.toTotalPriceBeforeDiscountModel());
        giveawayModelAndView(reservationInfo.toGiveawayModel());
        benefitsDetailsModelAndView(reservationInfo.toBenefitsDetailsModel());
        totalBenefitsPriceModelAndView(reservationInfo.toTotalBenefitsPriceModel());
        totalPriceAfterDiscount(reservationInfo.toTotalPriceAfterDiscountModel());
        badgeModelAndView(reservationInfo.toBadgeModel());
    }

    private void eventBenefitsPreviewModelAndView(Model model){
        outputView.eventBenefitsPreview(model);
    }

    private void orderMenuModelAndView(Model model){
        outputView.orderMenu(model);
    }

    private void totalPriceBeforeDiscountModelAndView(Model model){
        outputView.totalPriceBeforeDiscount(model);
    }

    private void giveawayModelAndView(Model model){
        outputView.giveaway(model);
    }

    private void benefitsDetailsModelAndView(Model model){
        outputView.benefitsDetails(model);
    }

    private void totalBenefitsPriceModelAndView(Model model){
        outputView.totalBenefitsPrice(model);
    }

    private void totalPriceAfterDiscount(Model model){
        outputView.totalPriceAfterDiscount(model);
    }

    private void badgeModelAndView(Model model){
        outputView.badge(model);
    }

}

package christmas.controller;

import christmas.dto.ReservationInfoDto;
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
}

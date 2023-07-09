package lk.rental.controller;

import lk.rental.dto.*;
import lk.rental.entity.Car;
import lk.rental.service.CarService;
import lk.rental.service.RentService;
import lk.rental.util.RentStatus;
import lk.rental.util.ResponseUtil;
import lk.rental.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rent")
@CrossOrigin
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseUtil getRentals() {
        ArrayList<RentDTO> allRentals = rentService.getRentals();
        return new ResponseUtil("200", " Success.!", allRentals);
    }

    @GetMapping("/pending")
    public ResponseUtil getPendingRentals() {
        ArrayList<RentDTO> allPendingRentals = rentService.getRentalsByStatus(RentStatus.PENDING.getStatus());
        return new ResponseUtil("200", " Success.!", allPendingRentals);
    }

    @GetMapping("/current")
    public ResponseUtil getCurrentRentals() {
        ArrayList<RentDTO> allCurrentRentals = rentService.getRentalsByStatus(RentStatus.CURRENT.getStatus());
        return new ResponseUtil("200", " Success.!", allCurrentRentals);
    }

    @GetMapping("/customer")
    public ResponseUtil getCustomerRentalsNotPending(@RequestParam("email") String email) {
        System.out.println(email);
        ArrayList<RentDTO> allPendingCustomerRentals = rentService.getCustomerRentalsNotPending(email);
        return new ResponseUtil("200", " Success.!", allPendingCustomerRentals);
    }

    @GetMapping("/pending/customer")
    public ResponseUtil getCustomerPendingRentals(@RequestParam("email") String email) {
        System.out.println(email);
        ArrayList<RentDTO> allPendingCustomerRentals = rentService.getCustomerPendingRentals(email);
        return new ResponseUtil("200", " Success.!", allPendingCustomerRentals);
    }

    @PostMapping("/rent")
    public ResponseUtil saveRent(@RequestBody RentProceedDTO rentProceedDTO) {
        long rentId = rentService.addRent(rentProceedDTO);
        return new ResponseUtil("200", " Added.!", rentId);
    }

    @PostMapping("/search")
    public ResponseUtil getAvailableCars(@RequestBody CarSearchDTO carSearchDTO) {
        System.out.println(carSearchDTO);
        List<Car> availableCars = carService.getAvailableCars(carSearchDTO);
        return new ResponseUtil("200", " Success.!", availableCars);
    }

    @GetMapping(params = {"registrationNo"})
    public void setStatusFlag(@RequestParam("registrationNo") String registrationNo) {
        carService.setStatus(registrationNo, Status.FLAG.getStatus());
    }

    @GetMapping(params = {"rentId"})
    public ResponseUtil rentSummary(@RequestParam("rentId") String rentId) {
        RentSummaryDTO rentSummaryDTO = rentService.rentSummary(Long.parseLong(rentId));
        return new ResponseUtil("200", " Added.!", rentSummaryDTO);
    }

    @PostMapping("/rentstart")
    public void rentStart(@RequestBody RentStartDTO rentStartDTO) {
        System.out.println(rentStartDTO);
        rentService.updateRent(rentStartDTO);
//        return new ResponseUtil("200", " Added.!", null);
    }

    @PostMapping("/rentend")
    public void rentEnd(@RequestBody RentEndDTO rentEndDTO) {
        System.out.println(rentEndDTO);
        rentService.updateRentEnd(rentEndDTO);
//        return new ResponseUtil("200", " Added.!", null);
    }

    @PostMapping("/cancel")
    public void cancelRent(@RequestBody RentStartDTO rentStartDTO) {
        rentService.cancelRent(rentStartDTO);
    }
}

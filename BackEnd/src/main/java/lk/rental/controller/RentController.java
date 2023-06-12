package lk.rental.controller;

import lk.rental.dto.*;
import lk.rental.entity.Car;
import lk.rental.service.CarService;
import lk.rental.service.RentService;
import lk.rental.util.ResponseUtil;
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
        carService.setStatus(registrationNo, "flag");
    }

    @GetMapping(params = {"rentId"})
    public ResponseUtil rentSummary(@RequestParam("rentId") String rentId) {
        RentSummaryDTO rentSummaryDTO = rentService.rentSummary(Long.parseLong(rentId));
        return new ResponseUtil("200", " Added.!", rentSummaryDTO);
    }

    @PutMapping
    public void rentStart(@RequestBody RentStartDTO rentStartDTO) {
        rentService.updateRent(rentStartDTO);
    }

    @PutMapping("/cancel")
    public void cancelRent(@RequestBody RentStartDTO rentStartDTO) {
        rentService.cancelRent(rentStartDTO);
    }
}

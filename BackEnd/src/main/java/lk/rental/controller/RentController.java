package lk.rental.controller;

import lk.rental.dto.CarDTO;
import lk.rental.dto.RentStartDTO;
import lk.rental.dto.RentDTO;
import lk.rental.service.CarService;
import lk.rental.service.RentService;
import lk.rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/rent")
@CrossOrigin
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseUtil saveRental(@ModelAttribute RentDTO rentDTO) {
        rentService.addRental(rentDTO);
        return new ResponseUtil("200", " Added.!", null);
    }

    @GetMapping
    public ResponseUtil getAllRentals() {
        ArrayList<RentDTO> allRentals = rentService.getAllRentals();
        return new ResponseUtil("200", " Success.!", allRentals);
    }

    @PostMapping("/rent")
    public ResponseUtil saveRent(@RequestBody RentStartDTO rentStartDTO) {
        long rentId = rentService.addRent(rentStartDTO);
        return new ResponseUtil("200", " Added.!", rentId);
    }

    @GetMapping(params = {"type"})
    public ResponseUtil getAvailableCars(@RequestParam("type") String carType) {
        ArrayList<CarDTO> availableCars = carService.getAvailableCars(carType);
        return new ResponseUtil("200", " Success.!", availableCars);
    }

    @GetMapping(params = {"registrationNo"})
    public void setStatusFlag(@RequestParam("registrationNo") String registrationNo) {
        carService.setStatus(registrationNo, "flag");
    }
}

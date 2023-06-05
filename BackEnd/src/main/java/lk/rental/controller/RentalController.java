package lk.rental.controller;

import lk.rental.dto.CarDTO;
import lk.rental.dto.RentStartDTO;
import lk.rental.dto.RentalDTO;
import lk.rental.service.CarService;
import lk.rental.service.RentalService;
import lk.rental.service.UserService;
import lk.rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping("/rent")
@CrossOrigin
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseUtil saveRental(@ModelAttribute RentalDTO rentalDTO) {
        rentalService.addRental(rentalDTO);
        return new ResponseUtil("200", " Added.!", null);
    }

    @GetMapping
    public ResponseUtil getAllRentals() {
        ArrayList<RentalDTO> allRentals = rentalService.getAllRentals();
        return new ResponseUtil("200", " Success.!", allRentals);
    }

    @PostMapping("/rent")
    public ResponseUtil saveRentStart(@RequestBody RentStartDTO rentStartDTO) {
        rentalService.addRentStart(rentStartDTO);
        return new ResponseUtil("200", " Added.!", null);
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

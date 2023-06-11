package lk.rental.controller;

import lk.rental.dto.CarDTO;
import lk.rental.dto.RentStartDTO;
import lk.rental.dto.RentDTO;
import lk.rental.dto.SearchCarDTO;
import lk.rental.entity.Car;
import lk.rental.entity.Rent;
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
    public ResponseUtil getAllRentals() {
        ArrayList<RentDTO> allRentals = rentService.getAllRentals();
        return new ResponseUtil("200", " Success.!", allRentals);
    }

    @PostMapping("/rent")
    public ResponseUtil saveRent(@RequestBody RentStartDTO rentStartDTO) {
        long rentId = rentService.addRent(rentStartDTO);
        return new ResponseUtil("200", " Added.!", rentId);
    }

    @PostMapping("/search")
    public ResponseUtil getAvailableCars(@RequestBody SearchCarDTO searchCarDTO) {
        System.out.println(searchCarDTO);
        List<Car> availableCars = carService.getAvailableCars(searchCarDTO);
        return new ResponseUtil("200", " Success.!", availableCars);
    }

    @GetMapping(params = {"registrationNo"})
    public void setStatusFlag(@RequestParam("registrationNo") String registrationNo) {
        carService.setStatus(registrationNo, "flag");
    }
}

package lk.rental.controller;

import lk.rental.dto.CarDTO;
import lk.rental.dto.PricingDTO;
import lk.rental.service.CarService;
import lk.rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseUtil saveCar(@RequestBody CarDTO carDTO) {
        carService.addCar(carDTO);
        return new ResponseUtil("200", " Added.!", null);
    }

    @GetMapping
    public ResponseUtil getAllCars() {
        ArrayList<CarDTO> allCars = carService.getAllCars();
        return new ResponseUtil("200", " Success.!", allCars);
    }

    @GetMapping(params = {"carType"})
    public ResponseUtil getCars(@RequestParam("carType") String carType) {
        ArrayList<PricingDTO> cars = carService.getCars(carType);
        return new ResponseUtil("200", " Success.!", cars);
    }

    @GetMapping(params = {"registrationNo"})
    public ResponseUtil getCar(@RequestParam("registrationNo") String registrationNo) {
//        System.out.println("reg No" + registrationNo);
        CarDTO car = carService.getCar(registrationNo);
        return new ResponseUtil("200", "Success.!", car);
    }
}

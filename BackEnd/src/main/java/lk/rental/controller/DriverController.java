package lk.rental.controller;

import lk.rental.dto.DriverDTO;
import lk.rental.service.DriverService;
import lk.rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/driver")
@CrossOrigin
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping
    public ResponseUtil saveDriver(@ModelAttribute DriverDTO driverDTO) {
        driverService.addDriver(driverDTO);
        return new ResponseUtil("200", " Added.!", null);
    }

    @GetMapping
    public ResponseUtil getAllDrivers() {
        ArrayList<DriverDTO> allDrivers = driverService.getAllDrivers();
        return new ResponseUtil("200", " Success.!", allDrivers);
    }

    @GetMapping("/select")
    public ResponseUtil getAvailableDriver() {
        DriverDTO availableDriver = driverService.getAvailableDriver();
        return new ResponseUtil("200", " Success.!", availableDriver);
    }

    @GetMapping(params = {"driverId"})
    public void flagCar(@RequestParam("driverId") String driverId) {
        Long longDriverId = Long .parseLong(driverId);
        driverService.flagDriver(longDriverId);
    }



}

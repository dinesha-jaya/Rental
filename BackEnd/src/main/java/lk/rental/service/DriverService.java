package lk.rental.service;

import lk.rental.dto.DriverDTO;

import java.util.ArrayList;

public interface DriverService {

    void addDriver(DriverDTO driverDTO);

    ArrayList<DriverDTO> getAllDrivers();

    DriverDTO getAvailableDriver();

    void flagDriver(Long driverId);
}

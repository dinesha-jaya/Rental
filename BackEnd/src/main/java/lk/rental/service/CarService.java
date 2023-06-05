package lk.rental.service;

import lk.rental.dto.CarDTO;
import lk.rental.dto.PricingDTO;

import java.util.ArrayList;

public interface CarService {

    void addCar(CarDTO carDTO);

    ArrayList<CarDTO> getAllCars();

    ArrayList<PricingDTO> getCars(String carType);

    CarDTO getCar(String registrationNo);

    ArrayList<CarDTO> getAvailableCars(String type);

    void setStatus(String registrationNo, String flag);
}

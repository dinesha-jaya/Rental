package lk.rental.service;

import lk.rental.dto.CarDTO;
import lk.rental.dto.PricingDTO;
import lk.rental.dto.SearchCarDTO;
import lk.rental.entity.Car;
import lk.rental.entity.Rent;

import java.util.ArrayList;
import java.util.List;

public interface CarService {

    void addCar(CarDTO carDTO);

    ArrayList<CarDTO> getAllCars();

    ArrayList<PricingDTO> getCars(String carType);

    CarDTO getCar(String registrationNo);

    List<Car> getAvailableCars(SearchCarDTO searchCarDTO);

    void setStatus(String registrationNo, String flag);
}

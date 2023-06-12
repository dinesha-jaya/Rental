package lk.rental.service;

import lk.rental.dto.CarDTO;
import lk.rental.dto.PricingDTO;
import lk.rental.dto.CarSearchDTO;
import lk.rental.entity.Car;

import java.util.ArrayList;
import java.util.List;

public interface CarService {

    void addCar(CarDTO carDTO);

    ArrayList<CarDTO> getAllCars();

    ArrayList<PricingDTO> getCars(String carType);

    CarDTO getCar(String registrationNo);

    List<Car> getAvailableCars(CarSearchDTO carSearchDTO);

    void setStatus(String registrationNo, String flag);
}

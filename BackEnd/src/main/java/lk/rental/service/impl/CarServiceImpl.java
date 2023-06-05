package lk.rental.service.impl;

import lk.rental.dto.CarDTO;
import lk.rental.dto.PricingDTO;
import lk.rental.entity.Car;
import lk.rental.repo.CarRepo;
import lk.rental.service.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addCar(CarDTO carDTO) {
//        if (carRepo.existsById(carDTO.getCarId())) {
//            throw new RuntimeException(("Car " + carDTO.getCarId() + " already exists"));
//        }

        carRepo.save(modelMapper.map(carDTO, Car.class));
    }

    @Override
    public ArrayList<CarDTO> getAllCars() {
        return modelMapper.map(carRepo.findAll(), new TypeToken<ArrayList<CarDTO>>() {}.getType());
    }

    @Override
    public ArrayList<PricingDTO> getCars(String carType) {
        List<PricingDTO> generalCars = carRepo.findPricingDto(carType);
        return (ArrayList<PricingDTO>) generalCars;
        //return modelMapper.map(carRepo.findPricingDtoByBrand(), new TypeToken<ArrayList<PricingDTO>>() {}.getType());
    }

    @Override
    public CarDTO getCar(String registrationNo) {
        return modelMapper.map(carRepo.findByRegistrationNo(registrationNo), CarDTO.class);
    }

    @Override
    public ArrayList<CarDTO> getAvailableCars(String type) {
        return modelMapper.map(carRepo.getAvailableCarsByType(type), new TypeToken<ArrayList<CarDTO>>() {}.getType());
    }

    @Override
    public void setStatus(String registrationNo, String flag) {
        Car car = carRepo.findByRegistrationNo(registrationNo);
        car.setStatus(flag);
        carRepo.save(car);
    }
}

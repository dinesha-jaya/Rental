package lk.rental.service.impl;

import lk.rental.dto.CarDTO;
import lk.rental.dto.PricingDTO;
import lk.rental.dto.RentedCarDetailDTO;
import lk.rental.dto.SearchCarDTO;
import lk.rental.entity.Car;
import lk.rental.repo.CarRepo;
import lk.rental.repo.RentRepo;
import lk.rental.service.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private RentRepo rentRepo;

//    @Autowired
//    private CustomRentRepo customRentRepo;

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
        return modelMapper.map(carRepo.findAll(), new TypeToken<ArrayList<CarDTO>>() {
        }.getType());
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
    public List<Car> getAvailableCars(SearchCarDTO searchCarDTO) {
        LocalDate startDate = searchCarDTO.getStartDate();
//        System.out.println(startDate);
        LocalDate endDate = searchCarDTO.getEndDate().plusDays(1);
        String type = searchCarDTO.getCarType();


        List<RentedCarDetailDTO> rentedCarDetails = rentRepo.findRentedCarDetail();
//        System.out.println(rentedCarDetails);

        ArrayList<String> registrationNos = new ArrayList<>();

        for (LocalDate currentDate = startDate; currentDate.isBefore(endDate); currentDate = currentDate.plusDays(1)) {
            System.out.println("current " + currentDate);
            System.out.println();
            for (RentedCarDetailDTO rentedCarDetailDTO : rentedCarDetails) {
//                LocalDate start = rentedCarDetailDTO.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                LocalDate end = rentedCarDetailDTO.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                Instant instant = input.toInstant();
//                ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
//                LocalDate date = zdt.toLocalDate();
                Date date1 = new Date(rentedCarDetailDTO.getStartDate().getTime());
                LocalDate start = date1.toLocalDate();
//                System.out.println(start);
                Date date2 = new Date(rentedCarDetailDTO.getEndDate().getTime());
                LocalDate end = date2.toLocalDate().plusDays(1);
//                System.out.println(end);
//                System.out.println();

                for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
                    if (currentDate.isEqual(date)) {
                        Car car = carRepo.findCarByCarId(rentedCarDetailDTO.getCarId());
//                        System.out.println(car.getRegistrationNo());
//                        System.out.println();
                        if (!registrationNos.contains(car.getRegistrationNo())) {
                            registrationNos.add(car.getRegistrationNo());
                        }
                    }
                }
            }
        }
        System.out.println(registrationNos);

        List<Car> allCarsByType = carRepo.getAvailableCarsByType(type);
//        System.out.println(allCarsByType);

        ArrayList<Car> busyCars = new ArrayList<>();

        for (String registrationNo : registrationNos) {
            for (Car car : allCarsByType) {
                if (car.getRegistrationNo().equalsIgnoreCase(registrationNo)) {
                    System.out.println("occupied " + car.getRegistrationNo());
                    busyCars.add(car);
                }
            }
        }

        for (Car car : busyCars) {
            allCarsByType.remove(car);
        }

        System.out.println(allCarsByType);

        return modelMapper.map(allCarsByType, new TypeToken<ArrayList<CarDTO>>() {
        }.getType());
    }

    @Override
    public void setStatus(String registrationNo, String flag) {
        Car car = carRepo.findByRegistrationNo(registrationNo);
        car.setStatus(flag);
        carRepo.save(car);
    }
}

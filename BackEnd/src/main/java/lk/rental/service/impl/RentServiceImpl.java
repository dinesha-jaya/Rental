package lk.rental.service.impl;

import lk.rental.dto.CarDriverDTO;
import lk.rental.dto.RentDTO;
import lk.rental.dto.RentStartDTO;
import lk.rental.entity.*;
import lk.rental.repo.*;
import lk.rental.service.RentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
@Transactional
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepo rentRepo;

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public long addRent(RentStartDTO rentStartDTO) {
        String rentDurationPlan = rentStartDTO.getRentDurationPlan();
        LocalDate startDate = rentStartDTO.getStartDate();
        LocalDate endDate = rentStartDTO.getEndDate();
        String username = rentStartDTO.getUsername();

        Customer customer = customerRepo.findByEmail(username);

        Rent rent = new Rent();
        rent.setRentDurationPlan(rentDurationPlan);
        rent.setStartDate(Date.valueOf(startDate));
        rent.setEndDate(Date.valueOf(endDate));
        rent.setStatus("pending");
        rent.setCustomer(customer);

        ArrayList<CarDriverDTO> carDriverList = rentStartDTO.getCarDriverList();

//        double tentativeAmount;

        for (CarDriverDTO carDriverDTO : carDriverList) {

            RentHasCar rentHasCar = new RentHasCar();
            rentHasCar.setDriverOption(carDriverDTO.isDriverOption());

            if (carDriverDTO.isDriverOption()) {

                Driver availableDriver = driverRepo.getAvailableDriver();
                rentHasCar.setDriver(availableDriver);
                availableDriver.setStatus("occupied");


            }

            setStatus(carDriverDTO.getRegistrationNo(), "rent");
            Car car = carRepo.findByRegistrationNo(carDriverDTO.getRegistrationNo());
            System.out.println(car);

//            RentDuration rentDuration = rentDurationRepo.findByCarAndRentDurationType(car, rentDurationPlan);
//            Period period = Period.between(startDate, endDate);
//
//            int years = period.getYears();
//
//            int months = period.getMonths();
//
//            int days = period.getDays();
//
//            if (rentDurationPlan.equalsIgnoreCase("monthly")) {
//                tentativeAmount = ((years * 12) + months + (days / 30.0)) * rentDuration.
//            } else if (rentDurationPlan.equalsIgnoreCase("daily")) {
//
//            }

            rentHasCar.setCar(car);
            rentHasCar.setRent(rent);

            rent.addRentalHasCar(rentHasCar);
        }

        Rent savedRent = rentRepo.save(rent);

        customer.getRents().add(savedRent);

        customerRepo.save(customer);

        setStatusAll("available");

        return savedRent.getRentId();
    }

    private void setStatusAll(String status) {
        ArrayList<Car> allCars = (ArrayList<Car>) carRepo.findAll();
        for (Car car : allCars) {
            if (car.getStatus().equalsIgnoreCase("flag")) {
                car.setStatus(status);
                carRepo.save(car);
            }
        }
    }

    @Override
    public ArrayList<RentDTO> getAllRentals() {
        return modelMapper.map(rentRepo.findAll(), new TypeToken<ArrayList<RentDTO>>() {
        }.getType());
    }

    private void setStatus(String registrationNo, String flag) {
        Car car = carRepo.findByRegistrationNo(registrationNo);
        car.setStatus(flag);
        carRepo.save(car);
    }
}

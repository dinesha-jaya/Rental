package lk.rental.service.impl;

import lk.rental.dto.CarDriverDTO;
import lk.rental.dto.RentStartDTO;
import lk.rental.dto.RentalDTO;
import lk.rental.entity.Car;
import lk.rental.entity.Customer;
import lk.rental.entity.Rental;
import lk.rental.entity.RentalHasCar;
import lk.rental.repo.CarRepo;
import lk.rental.repo.CustomerRepo;
import lk.rental.repo.DriverRepo;
import lk.rental.repo.RentalRepo;
import lk.rental.service.RentalService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepo rentalRepo;

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addRental(RentalDTO rentalDTO) {
//        if (rentalRepo.existsById(rentalDTO.getRentalId())) {
//            throw new RuntimeException(("Rental " + rentalDTO.getRentalId() + " already exists"));
//        }

        rentalRepo.save(modelMapper.map(rentalDTO, Rental.class));

    }

    @Override
    public void addRentStart(RentStartDTO rentStartDTO) {
        String rentDurationPlan = rentStartDTO.getRentDurationPlan();
        LocalDate startDate = rentStartDTO.getStartDate();
        LocalDate endDate = rentStartDTO.getEndDate();
        String username = rentStartDTO.getUsername();

        Customer customer = customerRepo.findByEmail(username);

        Rental rental = new Rental();
        rental.setRentDurationPlan(rentDurationPlan);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        rental.setStatus("pending");
        rental.setCustomer(customer);

        ArrayList<CarDriverDTO> carDriverList = rentStartDTO.getCarDriverList();

        for (CarDriverDTO carDriverDTO : carDriverList) {

            RentalHasCar rentalHasCar = new RentalHasCar();
            rentalHasCar.setDriverOption(carDriverDTO.isDriverOption());

            if (carDriverDTO.isDriverOption()) {
                rentalHasCar.setDriver(driverRepo.getAvailableDriver());
            }

            setStatus(carDriverDTO.getRegistrationNo(), "rent");
            Car car = carRepo.findByRegistrationNo(carDriverDTO.getRegistrationNo());
            System.out.println(car);

            rentalHasCar.setCar(car);
            rentalHasCar.setRental(rental);

            rental.addRentalHasCar(rentalHasCar);
        }

        Rental savedRental = rentalRepo.save(rental);

        customer.getRentals().add(savedRental);

        customerRepo.save(customer);

        setStatusAll("available");
    }

    private void setStatusAll(String status) {
        ArrayList<Car> allCars = (ArrayList<Car>) carRepo.findAll();
        for (Car car: allCars) {
            if (car.getStatus().equalsIgnoreCase("flag")) {
                car.setStatus(status);
                carRepo.save(car);
            }
        }
    }

    @Override
    public ArrayList<RentalDTO> getAllRentals() {
        return modelMapper.map(rentalRepo.findAll(), new TypeToken<ArrayList<RentalDTO>>() {
        }.getType());
    }

    private void setStatus(String registrationNo, String flag) {
        Car car = carRepo.findByRegistrationNo(registrationNo);
        car.setStatus(flag);
        carRepo.save(car);
    }
}

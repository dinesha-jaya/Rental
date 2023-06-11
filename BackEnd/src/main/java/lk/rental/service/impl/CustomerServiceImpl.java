package lk.rental.service.impl;

import lk.rental.dto.CustomerDTO;
import lk.rental.dto.CustomerRentRequestDTO;
import lk.rental.dto.CustomerRentResponseDTO;
import lk.rental.dto.RentCarDTO;
import lk.rental.entity.*;
import lk.rental.repo.CustomerRepo;
import lk.rental.repo.RentDurationRepo;
import lk.rental.repo.RentRepo;
import lk.rental.repo.UserRepo;
import lk.rental.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RentRepo rentRepo;

    @Autowired
    private RentDurationRepo rentDurationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addCustomer(CustomerDTO customerDTO) {
//        if (customerRepo.existsById(customerDTO.getCustomerId())) {
//            throw new RuntimeException(("Customer " + customerDTO.getCustomerId() + " already exists"));
//        }

        User user = new User();
        Random randNo = new Random();
        user.setPassword("" + (randNo.nextInt(10000-1000) + 1000));
        user.setUsername(customerDTO.getEmail());
        user.setUserCategory("customer");

        User savedUser = userRepo.save(user);

        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setIdType(customerDTO.getIdType());
        customer.setIdImage(customerDTO.isIdImage());
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());
        customer.setContactNo(customerDTO.getContactNo());

        customer.setUser(savedUser);

        customerRepo.save(customer);

//        Customer savedCustomer = customerRepo.save(modelMapper.map(customerDTO, Customer.class));
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() {
        return modelMapper.map(customerRepo.findAll(), new TypeToken<ArrayList<CustomerDTO>>() {}.getType());

    }

    @Override
    public CustomerRentResponseDTO customerRentSummary(CustomerRentRequestDTO customerRentRequestDTO) {
        CustomerRentResponseDTO customerRentResponseDTO = new CustomerRentResponseDTO();

        String email = customerRentRequestDTO.getEmail();
        long rentId = customerRentRequestDTO.getRentId();

        Customer customer = customerRepo.findByEmail(email);

        String customerFirstName = customer.getFirstName();
        String customerLastName = customer.getLastName();
        String customerAddress = customer.getAddress();
        String customerContactNo = customer.getContactNo();

        long customerId = customer.getCustomerId();

        System.out.println(customerId);
        System.out.println(rentId);

        Rent rent = rentRepo.findByRentId(rentId);

        System.out.println(rent);

//        System.out.println(rent.getStartDate());

        LocalDate rentStartDate = rent.getStartDate().toLocalDate();
        System.out.println(rentStartDate);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
//        String text = rentStartDate.format(formatter);
//        LocalDate parsedRentStartDate = LocalDate.parse(text, formatter);
//        System.out.println(parsedRentStartDate);


        LocalDate rentEndDate = rent.getEndDate().toLocalDate();
        System.out.println(rentEndDate);
//        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy MM dd");
//        String text1 = rentEndDate.format(formatter);
//        System.out.println(text1);
//        LocalDate parsedRentEndDate = LocalDate.parse(text1, formatter1);
//        System.out.println(parsedRentStartDate);


        String rentDurationPlan = rent.getRentDurationPlan();

        List<RentHasCar> rentHasCars = rent.getRentHasCars();

        ArrayList<RentCarDTO> rentCarDTOs = new ArrayList<>();

        for (RentHasCar rentHasCar: rentHasCars) {
            RentCarDTO rentCarDTO = new RentCarDTO();
            Car car = rentHasCar.getCar();

            String brand = car.getBrand();
            String fuelType = car.getFuelType();
            int noOfPassengers = car.getNoOfPassengers();
            String registrationNo = car.getRegistrationNo();
            String transmissionType = car.getTransmissionType();

            long carId = car.getCarId();

            RentDuration rentDuration = rentDurationRepo.findByRentDurationTypeAndCar_CarId(rentDurationPlan, carId);

            double rate = rentDuration.getRate();
            int freeKms = rentDuration.getFreeKms();
            double pricePerExtraKm = rentDuration.getPricePerExtraKm();

            String driverName;
            String driverContactNo;

            Driver driver = rentHasCar.getDriver();

            if (driver != null) {
                driverName = driver.getName();
                driverContactNo = driver.getContactNo();
            } else {
                driverName = "Not assigned";
                driverContactNo = "";
            }

            rentCarDTO.setBrand(brand);
            rentCarDTO.setFuelType(fuelType);
            rentCarDTO.setNoOfPassengers(noOfPassengers);
            rentCarDTO.setRegistrationNo(registrationNo);
            rentCarDTO.setTransmissionType(transmissionType);

            rentCarDTO.setRate(rate);
            rentCarDTO.setFreeKms(freeKms);
            rentCarDTO.setPricePerExtraKm(pricePerExtraKm);

            rentCarDTO.setDriverName(driverName);
            rentCarDTO.setDriverContactNo(driverContactNo);

            rentCarDTOs.add(rentCarDTO);

        }

        customerRentResponseDTO.setCustomerFirstName(customerFirstName);
        customerRentResponseDTO.setCustomerLastName(customerLastName);
        customerRentResponseDTO.setCustomerAddress(customerAddress);
        customerRentResponseDTO.setCustomerContactNo(customerContactNo);

        customerRentResponseDTO.setRentStartDate(rentStartDate);
        customerRentResponseDTO.setRentEndDate(rentEndDate);
        customerRentResponseDTO.setRentDurationPlan(rentDurationPlan);

        customerRentResponseDTO.setRentCarDTOs(rentCarDTOs);

        return customerRentResponseDTO;

    }
}

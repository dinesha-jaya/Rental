package lk.rental.service.impl;

import lk.rental.dto.*;
import lk.rental.entity.*;
import lk.rental.repo.*;
import lk.rental.service.RentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private RentDurationRepo rentDurationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public long addRent(RentProceedDTO rentProceedDTO) {
        String rentDurationPlan = rentProceedDTO.getRentDurationPlan();
        LocalDate startDate = rentProceedDTO.getStartDate();
        LocalDate endDate = rentProceedDTO.getEndDate();
        String username = rentProceedDTO.getUsername();

        Customer customer = customerRepo.findByEmail(username);

        Rent rent = new Rent();
        rent.setRentDurationPlan(rentDurationPlan);
        rent.setStartDate(Date.valueOf(startDate));
        rent.setEndDate(Date.valueOf(endDate));
        rent.setStatus("pending");
        rent.setCustomer(customer);

        ArrayList<CarDriverDTO> carDriverList = rentProceedDTO.getCarDriverList();

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
    public ArrayList<RentDTO> getRentals() {

        ArrayList<Rent> allRents = (ArrayList<Rent>) rentRepo.findAll();
        ArrayList<RentDTO> rentDTOs = new ArrayList<>();

        for (Rent rent: allRents) {
            RentDTO rentDTO = new RentDTO();
            rentDTO.setRentId(rent.getRentId());
            rentDTO.setStartDate(rent.getStartDate().toLocalDate());
            rentDTO.setEndDate(rent.getEndDate().toLocalDate());
            rentDTO.setRentDurationPlan(rent.getRentDurationPlan());
            rentDTO.setStatus(rent.getStatus());

            rentDTOs.add(rentDTO);
        }

        return rentDTOs;

    }

    @Override
    public RentSummaryDTO rentSummary(long rentId) {
        RentSummaryDTO rentSummaryDTO = new RentSummaryDTO();

        Rent rent = rentRepo.findByRentId(rentId);

        Customer customer = rent.getCustomer();

//        Customer customer = rentRepo.findByEmail(email);

        String customerFirstName = customer.getFirstName();
        String customerLastName = customer.getLastName();
        String customerAddress = customer.getAddress();
        String customerContactNo = customer.getContactNo();

//        long customerId = customer.getCustomerId();
//
//        System.out.println(customerId);
//        System.out.println(rentId);

        LocalDate rentStartDate = rent.getStartDate().toLocalDate();
//        System.out.println(rentStartDate);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
//        String text = rentStartDate.format(formatter);
//        LocalDate parsedRentStartDate = LocalDate.parse(text, formatter);
//        System.out.println(parsedRentStartDate);


        LocalDate rentEndDate = rent.getEndDate().toLocalDate();
//        System.out.println(rentEndDate);

        String rentDurationPlan = rent.getRentDurationPlan();

        String rentStatus = rent.getStatus();

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

        rentSummaryDTO.setCustomerFirstName(customerFirstName);
        rentSummaryDTO.setCustomerLastName(customerLastName);
        rentSummaryDTO.setCustomerAddress(customerAddress);
        rentSummaryDTO.setCustomerContactNo(customerContactNo);

        rentSummaryDTO.setRentStartDate(rentStartDate);
        rentSummaryDTO.setRentEndDate(rentEndDate);
        rentSummaryDTO.setRentDurationPlan(rentDurationPlan);
        rentSummaryDTO.setRentStatus(rentStatus);

        rentSummaryDTO.setRentCarDTOs(rentCarDTOs);

        return rentSummaryDTO;

    }

    @Override
    public void updateRent(RentStartDTO rentStartDTO) {
        String rentStatus = rentStartDTO.getStatus();
        String rentRemarks = rentStartDTO.getRemarks();
        long rentId = rentStartDTO.getRentId();
        System.out.println(rentId);

        Rent rent = rentRepo.findByRentId(rentId);
        //System.out.println(rent);

        rent.setStatus(rentStatus);
        rent.setRemarks(rentRemarks);

        ArrayList<RentStartCarDTO> rentStartCarDTOs = rentStartDTO.getRentStartCars();

        for (RentStartCarDTO rentStartCarDTO : rentStartCarDTOs) {
            String registrationNo = rentStartCarDTO.getRegistrationNo();
            //System.out.println(registrationNo);
            Car car = carRepo.findByRegistrationNo(registrationNo);
            long carId = car.getCarId();
            //System.out.println(carId);
            boolean lossDamageWaiverPaymentReceipt = rentStartCarDTO.isLossDamageWaiverPaymentReceipt();
            long meterStart = rentStartCarDTO.getMeterStart();

            List<RentHasCar> rentHasCars = rentRepo.findRentHasCars(rentId);

            for (RentHasCar rentHasCar: rentHasCars) {
                long rentHasCarId = rentHasCar.getCar().getCarId();
//                System.out.println(rentHasCarId);
//                System.out.println(carId);
                if (rentHasCarId == carId) {
                    rentHasCar.setLossDamageWaiverPaymentReceipt(lossDamageWaiverPaymentReceipt);
                    rentHasCar.setMeterStart(meterStart);
                }
            }

            rent.setRentHasCars(rentHasCars);
        }

        System.out.println(rent.getRentHasCars());

        rentRepo.save(rent);

    }

    @Override
    public void cancelRent(RentStartDTO rentStartDTO) {
        String rentStatus = rentStartDTO.getStatus();
        String rentRemarks = rentStartDTO.getRemarks();
        long rentId = rentStartDTO.getRentId();

        Rent rent = rentRepo.findByRentId(rentId);

        rent.setStatus(rentStatus);
        rent.setRemarks(rentRemarks);

        rentRepo.save(rent);
    }

    private void setStatus(String registrationNo, String flag) {
        Car car = carRepo.findByRegistrationNo(registrationNo);
        car.setStatus(flag);
        carRepo.save(car);
    }
}

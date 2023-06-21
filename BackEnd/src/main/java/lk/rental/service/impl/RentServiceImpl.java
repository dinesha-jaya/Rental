package lk.rental.service.impl;

import lk.rental.dto.*;
import lk.rental.entity.*;
import lk.rental.repo.*;
import lk.rental.service.RentService;
import lk.rental.util.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        rent.setStatus(RentStatus.PENDING.getStatus());
        rent.setCustomer(customer);

        ArrayList<CarDriverDTO> carDriverList = rentProceedDTO.getCarDriverList();

        for (CarDriverDTO carDriverDTO : carDriverList) {

            RentHasCar rentHasCar = new RentHasCar();
            rentHasCar.setDriverOption(carDriverDTO.isDriverOption());

            if (carDriverDTO.isDriverOption()) {

                Driver availableDriver = driverRepo.getAvailableDriver();
                rentHasCar.setDriver(availableDriver);
                availableDriver.setStatus(RentStatus.PENDING.getStatus());
            }

            setStatus(carDriverDTO.getRegistrationNo(), RentStatus.PENDING.getStatus());
            Car car = carRepo.findByRegistrationNo(carDriverDTO.getRegistrationNo());
//            System.out.println(car);

            rentHasCar.setCar(car);
            rentHasCar.setRent(rent);

            rent.addRentalHasCar(rentHasCar);
        }

        Rent savedRent = rentRepo.save(rent);

        customer.getRents().add(savedRent);

        customerRepo.save(customer);

        setStatusAll(Status.AVAILABLE.getStatus());

        return savedRent.getRentId();
    }

    private void setStatusAll(String status) {
        ArrayList<Car> allCars = (ArrayList<Car>) carRepo.findAll();
        for (Car car : allCars) {
            if (car.getStatus().equalsIgnoreCase(Status.FLAG.getStatus())) {
                car.setStatus(status);
                carRepo.save(car);
            }
        }
    }

    @Override
    public ArrayList<RentDTO> getRentals() {

        ArrayList<Rent> allRents = (ArrayList<Rent>) rentRepo.findAll();
        ArrayList<RentDTO> rentDTOs = new ArrayList<>();

        for (Rent rent : allRents) {
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
        double amount = rent.getAmount();

        List<RentHasCar> rentHasCars = rent.getRentHasCars();

        ArrayList<RentCarDTO> rentCarDTOs = new ArrayList<>();

        for (RentHasCar rentHasCar : rentHasCars) {
            RentCarDTO rentCarDTO = new RentCarDTO();
            Car car = rentHasCar.getCar();

            String brand = car.getBrand();
            String fuelType = car.getFuelType();
            int noOfPassengers = car.getNoOfPassengers();
            String registrationNo = car.getRegistrationNo();
            String transmissionType = car.getTransmissionType();

            long carId = car.getCarId();

            RentDuration rentDuration = rentDurationRepo.findByRentDurationTypeAndCar_CarId(rentDurationPlan, carId);

            double rate = rentDuration.getRatePerType();
            int freeKms = rentDuration.getFreeKmsPerType();
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

            boolean lossDamageWaiverPaymentReceipt = rentHasCar.isLossDamageWaiverPaymentReceipt();
            double lossDamageWaiverPaymentCharge = rentHasCar.getLossDamageWaiverPaymentCharge();
            double lossDamageWaiverPayment = rentHasCar.getLossDamageWaiverPayment();
            long meterStart = rentHasCar.getMeterStart();
            long meterEnd = rentHasCar.getMeterEnd();
            double rateFee = rentHasCar.getRateFee();
            double driverFee = rentHasCar.getDriverFee();
            double chargeForKms = rentHasCar.getChargeForKms();
            double amountPerCarPerTrip = rentHasCar.getAmountPerCarPerTrip();

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

            rentCarDTO.setLossDamageWaiverPaymentReceipt(lossDamageWaiverPaymentReceipt);
            rentCarDTO.setMeterStart(meterStart);
            rentCarDTO.setLossDamageWaiverPaymentCharge(lossDamageWaiverPaymentCharge);
            rentCarDTO.setLossDamageWaiverPayment(lossDamageWaiverPayment);
            rentCarDTO.setMeterEnd(meterEnd);
            rentCarDTO.setRateFee(rateFee);
            rentCarDTO.setDriverFee(driverFee);
            rentCarDTO.setChargeForKms(chargeForKms);
            rentCarDTO.setAmountForCarForTrip(amountPerCarPerTrip);

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
        rentSummaryDTO.setRentAmount(amount);

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

            for (RentHasCar rentHasCar : rentHasCars) {
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

//        System.out.println(rent.getRentHasCars());

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

    @Override
    public void updateRentEnd(RentEndDTO rentEndDTO) {
        String status = rentEndDTO.getStatus();
        Date rentTerminatedDate = Date.valueOf(rentEndDTO.getRentTerminatedDate());

        long rentId = rentEndDTO.getRentId();

        Rent rent = rentRepo.findByRentId(rentId);

        rent.setEndDate(rentTerminatedDate);

        LocalDate startDate = rent.getStartDate().toLocalDate();
        LocalDate endDate = rent.getEndDate().toLocalDate();

        String rentDurationPlan = rent.getRentDurationPlan();

        long days = 0;
        long months = 0;

        if (rentDurationPlan.equalsIgnoreCase(RentDurationPlan.DAILY.getRentDurationPlan())) {
            days = ChronoUnit.DAYS.between(startDate, endDate);
        } else {
            months = ChronoUnit.MONTHS.between(startDate, endDate);
        }

        ArrayList<RentEndCarDTO> rentEndCarList = rentEndDTO.getRentEndCarList();

        double amount = 0.0;

        for (RentEndCarDTO rentEndCarDTO : rentEndCarList) {
            String registrationNo = rentEndCarDTO.getRegistrationNo();

            Car car = carRepo.findByRegistrationNo(registrationNo);

            long carId = car.getCarId();
            String type = car.getType();

            RentDuration rentDuration = rentDurationRepo.findByRentDurationTypeAndCar_CarId(rentDurationPlan, carId);

            double rate = rentDuration.getRatePerType();
            int freeKms = rentDuration.getFreeKmsPerType();
            double pricePerExtraKm = rentDuration.getPricePerExtraKm();

            double lossDamageWaiverPaymentCharge = rentEndCarDTO.getLossDamageWaiverPaymentCharge();
            long meterEnd = rentEndCarDTO.getMeterEnd();

            List<RentHasCar> rentHasCars = rentRepo.findRentHasCars(rentId);

            long meterStart = 0;
            double lossDamageWaiverPayment = 0.0;
            double lossDamageWaiverPaymentReturn = 0.0;

            for (RentHasCar rentHasCar : rentHasCars) {
                long rentHasCarId = rentHasCar.getCar().getCarId();

                if (rentHasCarId == carId) {
                    meterStart = rentHasCar.getMeterStart();
                    rentHasCar.setLossDamageWaiverPaymentCharge(lossDamageWaiverPaymentCharge);
                    rentHasCar.setMeterEnd(meterEnd);

                    switch (type) {
                        case "general":
                            lossDamageWaiverPayment = LossDamageWaiverPayment.LOSS_DAMAGE_WAIVER_PAYMENT_GENERAL.getPayment();
                            break;
                        case "premium":
                            lossDamageWaiverPayment = LossDamageWaiverPayment.LOSS_DAMAGE_WAIVER_PAYMENT_PREMIUM.getPayment();
                            break;
                        case "luxury":
                            lossDamageWaiverPayment = LossDamageWaiverPayment.LOSS_DAMAGE_WAIVER_PAYMENT_LUXURY.getPayment();
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + type);
                    }
                    
//                    System.out.println(lossDamageWaiverPaymentCharge);
//                    System.out.println(lossDamageWaiverPayment);
                    lossDamageWaiverPaymentReturn = lossDamageWaiverPayment - lossDamageWaiverPaymentCharge;

//                    System.out.println(lossDamageWaiverPaymentReturn);
                    rentHasCar.setLossDamageWaiverPayment(lossDamageWaiverPaymentReturn);

                    if (rentHasCar.isDriverOption()) {
                        if (rentDurationPlan.equalsIgnoreCase(RentDurationPlan.DAILY.getRentDurationPlan())) {
                            rentHasCar.setDriverFee(days * DriverFee.DRIVER_FEE.getFee());
                        } else {
                            long monthDays = ChronoUnit.DAYS.between(startDate, endDate);
                            rentHasCar.setDriverFee(monthDays * DriverFee.DRIVER_FEE.getFee());
                        }
                    }

                    if (rentDurationPlan.equalsIgnoreCase(RentDurationPlan.DAILY.getRentDurationPlan())) {
                        rentHasCar.setRateFee(rate * days);
                        rentHasCar.setChargeForKms(((meterEnd - meterStart) - (freeKms * days)) * pricePerExtraKm);
                    } else {
                        rentHasCar.setRateFee(rate * months);
                        rentHasCar.setChargeForKms(((meterEnd - meterStart) - (freeKms * months)) * pricePerExtraKm);
                    }

                    rentHasCar.setAmountPerCarPerTrip(rentHasCar.getDriverFee() + rentHasCar.getRateFee() + rentHasCar.getChargeForKms());

                    amount += rentHasCar.getAmountPerCarPerTrip();
                }
            }
            rent.setRentHasCars(rentHasCars);
            rent.setStatus(status);
            rent.setAmount(amount);
        }
        rentRepo.save(rent);
    }

    @Override
    public ArrayList<RentDTO> getRentalsByStatus(String status) {

        List<Rent> pendingRents = rentRepo.findAllByStatus(status);
        ArrayList<RentDTO> rentDTOs = new ArrayList<>();

        for (Rent rent : pendingRents) {
            RentDTO rentDTO = new RentDTO();
            rentDTO.setRentId(rent.getRentId());
            rentDTO.setStartDate(rent.getStartDate().toLocalDate());
            rentDTO.setEndDate(rent.getEndDate().toLocalDate());
            rentDTO.setRentDurationPlan(rent.getRentDurationPlan());
            rentDTO.setStatus(rent.getStatus());

            rentDTOs.add(rentDTO);
        }

        return rentDTOs;
//        return modelMapper.map(rentRepo.findAllByStatus("pending"), new TypeToken<ArrayList<RentDTO>>() {}.getType());
    }

    @Override
    public ArrayList<RentDTO> getCustomerRentalsNotPending(String customerEmail) {
        Customer customer = customerRepo.findByEmail(customerEmail);

        long customerId = customer.getCustomerId();

        List<Rent> rentsNotPending = rentRepo.findAllByCustomerNotStatus(customerId, RentStatus.PENDING.getStatus());

        ArrayList<RentDTO> rentDTOs = new ArrayList<>();

        for (Rent rent : rentsNotPending) {
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
    public ArrayList<RentDTO> getCustomerPendingRentals(String customerEmail) {
        Customer customer = customerRepo.findByEmail(customerEmail);

        long customerId = customer.getCustomerId();

        List<Rent> customerRents = rentRepo.findAllByCustomer_CustomerId(customerId);

        ArrayList<RentDTO> rentDTOs = new ArrayList<>();

        for (Rent rent : customerRents) {
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

    private void setStatus(String registrationNo, String flag) {
        Car car = carRepo.findByRegistrationNo(registrationNo);
        car.setStatus(flag);
        carRepo.save(car);
    }
}

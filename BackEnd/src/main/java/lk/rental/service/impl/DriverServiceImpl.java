package lk.rental.service.impl;

import lk.rental.dto.DriverDTO;
import lk.rental.entity.Driver;
import lk.rental.repo.DriverRepo;
import lk.rental.service.DriverService;
import lk.rental.util.Status;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addDriver(DriverDTO driverDTO) {
//        if (driverRepo.existsById(driverDTO.getDriverId())) {
//            throw new RuntimeException(("Driver " + driverDTO.getDriverId() + " already exists"));
//        }

        driverRepo.save(modelMapper.map(driverDTO, Driver.class));

    }

    @Override
    public ArrayList<DriverDTO> getAllDrivers() {
        return modelMapper.map(driverRepo.findAll(), new TypeToken<ArrayList<DriverDTO>>() {}.getType());
    }

    @Override
    public DriverDTO getAvailableDriver() {
        return modelMapper.map(driverRepo.getAvailableDriver(), DriverDTO.class);
    }

    @Override
    public void flagDriver(Long driverId) {
        Driver driver = driverRepo.findByDriverId(driverId);
        driver.setStatus(Status.FLAG.getStatus());
        driverRepo.save(driver);
    }
}

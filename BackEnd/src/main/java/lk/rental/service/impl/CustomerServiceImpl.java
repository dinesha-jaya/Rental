package lk.rental.service.impl;

import lk.rental.dto.CustomerDTO;
import lk.rental.dto.RentSummaryDTO;
import lk.rental.dto.RentCarDTO;
import lk.rental.entity.*;
import lk.rental.repo.CustomerRepo;
import lk.rental.repo.RentDurationRepo;
import lk.rental.repo.RentRepo;
import lk.rental.repo.UserRepo;
import lk.rental.service.CustomerService;
import lk.rental.util.UserCategory;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
        user.setUserCategory(UserCategory.CUSTOMER.getUserCategory());

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


}

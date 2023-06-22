package lk.rental.service;

import lk.rental.dto.CustomerDTO;
import lk.rental.dto.RentSummaryDTO;
import lk.rental.dto.UserDTO;

import java.util.ArrayList;

public interface CustomerService {

    UserDTO addCustomer(CustomerDTO customerDTO);

    ArrayList<CustomerDTO> getAllCustomers();


}

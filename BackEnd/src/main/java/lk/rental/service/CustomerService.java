package lk.rental.service;

import lk.rental.dto.CustomerDTO;
import lk.rental.dto.CustomerRentRequestDTO;
import lk.rental.dto.CustomerRentResponseDTO;

import java.util.ArrayList;

public interface CustomerService {

    void addCustomer(CustomerDTO customerDTO);

    ArrayList<CustomerDTO> getAllCustomers();

    CustomerRentResponseDTO customerRentSummary(CustomerRentRequestDTO customerRentRequestDTO);
}

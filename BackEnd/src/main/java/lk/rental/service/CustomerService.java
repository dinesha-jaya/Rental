package lk.rental.service;

import lk.rental.dto.CustomerDTO;

import java.util.ArrayList;

public interface CustomerService {

    void addCustomer(CustomerDTO customerDTO);

    ArrayList<CustomerDTO> getAllCustomers();
}

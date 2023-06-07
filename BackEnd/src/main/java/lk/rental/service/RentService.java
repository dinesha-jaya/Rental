package lk.rental.service;

import lk.rental.dto.RentStartDTO;
import lk.rental.dto.RentDTO;

import java.util.ArrayList;

public interface RentService {

    void addRental(RentDTO rentDTO);

    long addRent(RentStartDTO rentStartDTO);

    ArrayList<RentDTO> getAllRentals();
}

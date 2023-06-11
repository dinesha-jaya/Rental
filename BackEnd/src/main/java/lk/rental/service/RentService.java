package lk.rental.service;

import lk.rental.dto.RentStartDTO;
import lk.rental.dto.RentDTO;

import java.util.ArrayList;

public interface RentService {

    long addRent(RentStartDTO rentStartDTO);

    ArrayList<RentDTO> getAllRentals();
}

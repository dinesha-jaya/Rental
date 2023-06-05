package lk.rental.service;

import lk.rental.dto.RentStartDTO;
import lk.rental.dto.RentalDTO;

import java.util.ArrayList;

public interface RentalService {

    void addRental(RentalDTO rentalDTO);

    void addRentStart(RentStartDTO rentStartDTO);

    ArrayList<RentalDTO> getAllRentals();
}

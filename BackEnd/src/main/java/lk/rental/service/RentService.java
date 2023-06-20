package lk.rental.service;

import lk.rental.dto.*;

import java.util.ArrayList;

public interface RentService {

    long addRent(RentProceedDTO rentProceedDTO);

    ArrayList<RentDTO> getRentals();

    RentSummaryDTO rentSummary(long rentId);

    void updateRent(RentStartDTO rentStartDTO);

    void cancelRent(RentStartDTO rentStartDTO);

    void updateRentEnd(RentEndDTO rentEndDTO);

    ArrayList<RentDTO> getPendingRentals();

    ArrayList<RentDTO> getPendingCustomerRentals(String customerEmail);

    ArrayList<RentDTO> getCustomerRentals(String customerEmail);
}

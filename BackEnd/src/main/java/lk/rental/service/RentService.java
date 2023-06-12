package lk.rental.service;

import lk.rental.dto.RentProceedDTO;
import lk.rental.dto.RentDTO;
import lk.rental.dto.RentStartDTO;
import lk.rental.dto.RentSummaryDTO;

import java.util.ArrayList;

public interface RentService {

    long addRent(RentProceedDTO rentProceedDTO);

    ArrayList<RentDTO> getRentals();

    RentSummaryDTO rentSummary(long rentId);

    void updateRent(RentStartDTO rentStartDTO);

    void cancelRent(RentStartDTO rentStartDTO);
}

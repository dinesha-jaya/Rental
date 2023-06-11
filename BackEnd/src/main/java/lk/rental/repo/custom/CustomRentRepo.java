package lk.rental.repo.custom;

import lk.rental.dto.RentedCarDetailDTO;

import java.util.List;

public interface CustomRentRepo {

    //    @Query(nativeQuery = true)
    List<RentedCarDetailDTO> findRentedCarDetail();

//    List<Rent> findAll();
}

package lk.rental.repo.custom;

import lk.rental.dto.RentedCarDetailDTO;
import lk.rental.entity.Rent;
import lk.rental.entity.RentHasCar;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomRentRepo {

    //    @Query(nativeQuery = true)
    List<RentedCarDetailDTO> findRentedCarDetail();

//    List<Rent> findAll();

    List<RentHasCar> findRentHasCars(long rentId);
}

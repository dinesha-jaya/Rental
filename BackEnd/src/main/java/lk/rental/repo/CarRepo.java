package lk.rental.repo;

import lk.rental.dto.PricingDTO;
import lk.rental.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

    @Query(nativeQuery = true)
    List<PricingDTO> findPricingDto(String carType);

    @Query(value = "SELECT * FROM car c WHERE c.type = :t and c.status = 'available'", nativeQuery = true)
    List<Car> getAvailableCarsByType(@Param("t") String type);

    Car findByRegistrationNo(String registrationNo);
//
//    @Modifying
//    @Query(value = "UPDATE car SET status = 'rent' WHERE registrationNo = :regNo", nativeQuery = true)
//    void selectCars(@Param("regNo") String registrationNo);
//
//    @Modifying
//    @Query(value = "UPDATE car c SET c.status = 'flag' WHERE c.registrationNo = :r", nativeQuery = true)
//    void flagCar(@Param("r") String registrationNo);

}

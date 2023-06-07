package lk.rental.repo;

import lk.rental.entity.Car;
import lk.rental.entity.RentDuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentDurationRepo extends JpaRepository<RentDuration, Long> {

    RentDuration findByRentDurationTypeAndCar_CarId(String rentDurationType, long carId);
}

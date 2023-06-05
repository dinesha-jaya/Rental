package lk.rental.repo;

import lk.rental.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Long> {

    @Query(value = "SELECT * FROM driver WHERE status = 'available' LIMIT 1", nativeQuery = true)
    Driver getAvailableDriver();

    Driver findByDriverId(Long driverId);
}

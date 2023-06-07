package lk.rental.repo;

import lk.rental.entity.Customer;
import lk.rental.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepo extends JpaRepository<Rent, Long> {

    Rent findByRentId(long rentId);
}

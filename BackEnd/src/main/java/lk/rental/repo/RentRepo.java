package lk.rental.repo;

import lk.rental.entity.Rent;
import lk.rental.repo.custom.CustomRentRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepo extends CrudRepository<Rent, Long>, CustomRentRepo {

    Rent findByRentId(long rentId);

    List<Rent> findAllByStatus(String status);

    @Query(value = "SELECT * FROM rent WHERE customer_customerId = ?1 AND status <> ?2", nativeQuery = true)
    List<Rent> findAllByCustomerNotStatus(long customerId, String status);

    List<Rent> findAllByCustomer_CustomerId(long customerId);
}

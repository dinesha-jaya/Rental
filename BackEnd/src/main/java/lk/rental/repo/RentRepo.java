package lk.rental.repo;

import lk.rental.entity.Rent;
import lk.rental.repo.custom.CustomRentRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepo extends CrudRepository<Rent, Long>, CustomRentRepo {

    Rent findByRentId(long rentId);

}

package lk.rental.repo.custom.impl;

import lk.rental.dto.RentedCarDetailDTO;
import lk.rental.entity.*;
import lk.rental.repo.custom.CustomRentRepo;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
public class CustomRentRepoImpl implements CustomRentRepo {


    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<RentedCarDetailDTO> findRentedCarDetail() {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
//        Root<Rent> rootRent = criteriaQuery.from(Rent.class);
//        Root<RentHasCar> rootRentHasCar = criteriaQuery.from(RentHasCar.class);
//        //Join<Rent, RentHasCar> rentHasCar = root.join(Rent_.rentHasCars);
//
//        criteriaQuery.multiselect(rootRent, rootRentHasCar);
//        criteriaQuery.where(criteriaBuilder.equal(rootRent.get("rentId"), rootRentHasCar.get("rent")));
//
//
////        criteriaQuery.select(criteriaBuilder.construct(
////                RentedCarDetailDTO.class,
////                root.get(Rent_.startDate),
////                root.get(Rent_.endDate),
////                criteriaBuilder.(rentHasCar.get(RentHasCar_.car))));
//
//        //        System.out.println(rentedCars);
//        List<Object[]> resultList = entityManager.createQuery(criteriaQuery).getResultList();
//        Object[] objects = resultList.toArray();
//
//        List<RentedCarDetailDTO> rentedCarDetailDTOs = new ArrayList<>();
//
//        for (Object object : objects) {
//            Rent rent = (Rent) object;
//            RentHasCar rentHasCar = (RentHasCar) object;
//            RentedCarDetailDTO rentedCarDetailDTO = new RentedCarDetailDTO();
//            rentedCarDetailDTO.setStartDate(rent.getStartDate());
//            rentedCarDetailDTO.setEndDate(rent.getStartDate());
//            rentedCarDetailDTO.setCarId(rentHasCar.getCar().getCarId());
//
//            rentedCarDetailDTOs.add(rentedCarDetailDTO);
//        }
//
//        return rentedCarDetailDTOs;

//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<RentedCarDetailDTO> criteriaQuery = criteriaBuilder.createQuery(RentedCarDetailDTO.class);
//
//        Root<RentHasCar> rootRentHasCar = criteriaQuery.from(RentHasCar.class);
//        Root<Rent> rootRent = criteriaQuery.from(Rent.class);
//
//        criteriaQuery.multiselect(rootRent, rootRentHasCar);
//
//        Join<RentHasCar, Rent> rentHasCarJoin = rootRentHasCar.join(RentHasCar_.rent);
//        Join<Rent, RentHasCar> rentJoin = rentHasCarJoin.join(Rent_.rentHasCars);
//
//        Path<Date> startDatePath = rootRent.get(Rent_.startDate);
//        Path<Date> endDatePath = rootRent.get(Rent_.endDate);
//        Path<Car> carPath = rootRentHasCar.get(RentHasCar_.car);
//
//        criteriaQuery.select(criteriaBuilder.construct(RentedCarDetailDTO.class, startDatePath, endDatePath, carPath));
//
//        return entityManager.createQuery(criteriaQuery).getResultList();

        TypedQuery<RentedCarDetailDTO> query = entityManager.createQuery("SELECT new lk.rental.dto.RentedCarDetailDTO(r.startDate, r.endDate, h.car.carId) FROM Rent r JOIN r.rentHasCars h", RentedCarDetailDTO.class);
        return query.getResultList();


    }

//    @Override
//    public List<Rent> findAll() {
//        return entityManager.createQuery("SELECT r.status FROM Rent r", Rent.class).getResultList();
//    }
}

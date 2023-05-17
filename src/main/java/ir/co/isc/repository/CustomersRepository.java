package ir.co.isc.repository;

import ir.co.isc.entity.Customers;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {

//findAllNationalCode
    @Query("select c.nationalCode from Customers c where c.nationalCode=:nationalCode")
    Customers findCustomersByNationalCode(String nationalCode);
    @Query("select c.nationalCode from Customers c")
    List<String> findAllNationalCode();

    @Query("select c from Customers c where c.id=:id")
    Optional<Customers> findCustomersByNationalCodeOrId(String nationalCode,long id);

}
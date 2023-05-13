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
    @Query("select c.nationalCode from Customers c")
    Optional<Customers> findCustomersByNationalCode(String nationalCode);
    @Query("select c from Customers c where c.id=:id")
    Optional<Customers> findCustomersById(long id);

    @Query("select c from Customers c where c.id=:id")
    Optional<Customers> findCustomersByNationalCodeOrId(String nationalCode,long id);

    //search notOk
    /*public List<Customers> searchCurtomers(Customers customers, Session session) {
        List<Customers> customer = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Customers> Query = criteriaBuilder.createQuery(Customers.class);
        Root<Customers> emp = Query.from(Customers.class);
        Predicate finalPredicate = criteriaBuilder.conjunction();

        if (customers.getFirstName() != null && !customers.getFirstName().equals("")) {
            finalPredicate = criteriaBuilder.and(finalPredicate, criteriaBuilder.equal(emp.get("firstName"), customers.getFirstName()));
        }
        if (customers.getLastName() != null && !customers.getLastName().equals("")) {
            finalPredicate = criteriaBuilder.and(finalPredicate, criteriaBuilder.equal(emp.get("lastName"), customers.getLastName()));
        }
        if (customers.getAccountNumber() != null && !customers.getAccountNumber().equals("")) {
            finalPredicate = criteriaBuilder.and(finalPredicate, criteriaBuilder.equal(emp.get("accountNumber"), customers.getAccountNumber()));
        }
        if (customers.getNationalCode() != null && !customers.getNationalCode().equals("")) {
            finalPredicate = criteriaBuilder.and(finalPredicate, criteriaBuilder.equal(emp.get("nationalCode"), customers.getNationalCode()));
        }
        Query.select(emp).where(finalPredicate);
        org.hibernate.Query<Customers> query = session.createQuery(Query);
        customer = query.getResultList();
        return customer;

    }*/
}
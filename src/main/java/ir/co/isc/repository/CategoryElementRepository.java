/*
package ir.co.isc.repository;


import ir.co.isc.entity.Cards;
import ir.co.isc.entity.CategoryElement;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryElementRepository extends JpaRepository<CategoryElement, Long> {


    @Query("select ce from CategoryElement ce where ce.code=:categoryCode")
    Optional<CategoryElement> findCategoryElementByCode(String categoryCode);
}
*/

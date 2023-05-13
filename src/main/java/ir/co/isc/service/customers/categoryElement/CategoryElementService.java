/*
package ir.co.isc.service.categoryElement;

import ir.co.isc.entity.CategoryElement;
import ir.co.isc.exception.EntityNotFoundException;
import ir.co.isc.repository.CategoryElementRepository;
import ir.co.isc.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CategoryElementService {
    private final CategoryElementRepository entityRepository;

    public ResponseEntity<CategoryElement> findCategoryElementByCode(String categoryElement) {

        Optional<CategoryElement> entity = entityRepository.findCategoryElementByCode(categoryElement);

        if (entity.isPresent()) {

            return new ResponseEntity(entityRepository.findCategoryElementByCode(categoryElement), HttpStatus.OK);

        } else {

            throw new EntityNotFoundException(Constant.TYPE_CARD_NOT_FOUND_ERROR_MESSAGE);
        }

    }
}
*/

package ir.co.isc.service.customers;

import ir.co.isc.entity.Cards;
import ir.co.isc.entity.Customers;
import ir.co.isc.repository.CustomersRepository;
import ir.co.isc.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomersService {
    private final CustomersRepository customersRepository;

    public Customers findCustomersByNationalCode(String nationalCode) {

        Optional<Customers> foundedCustomer = customersRepository.findCustomersByNationalCode(nationalCode);

        if (foundedCustomer.isPresent()) {

            return new Customers(foundedCustomer, HttpStatus.OK);

        } else {

            throw new ir.co.isc.exception.EntityNotFoundException(Constant.CUSTOMERS_NOT_FOUND_ERROR_MESSAGE);

        }

    }
    public ResponseEntity<Cards> findCustomersById(long id) {

        Optional<Customers> foundedCustomer = customersRepository.findCustomersById(id);

        if (foundedCustomer.isPresent()) {

            return new ResponseEntity(foundedCustomer, HttpStatus.OK);

        } else {

            throw new ir.co.isc.exception.EntityNotFoundException(Constant.CUSTOMERS_NOT_FOUND_ERROR_MESSAGE);

        }
}

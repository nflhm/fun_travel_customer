package id.co.travel.fun.customer.repository;

import id.co.travel.fun.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByCustomerId(Integer integer);
    List<Customer> findAllByOrderByCustomerId();
}

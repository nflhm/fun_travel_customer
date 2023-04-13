package id.co.travel.fun.customer.service;

import id.co.travel.fun.customer.model.Customer;
import id.co.travel.fun.customer.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    ICustomerRepository customerRepository;

    public void insert(Customer customer){ customerRepository.save(customer); }

    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findTheCustomer(Customer customer) {
        return customerRepository.findCustomerByCustomerId(customer.getCustomerId());
    }

    public List<Customer> allCustomer() {
        return customerRepository.findAllByOrderByCustomerId();
    }
}

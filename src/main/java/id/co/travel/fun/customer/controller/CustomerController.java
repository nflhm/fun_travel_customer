package id.co.travel.fun.customer.controller;

import id.co.travel.fun.customer.model.Customer;
import id.co.travel.fun.customer.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    ICustomerRepository customerRepository;
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Customer> findAll() { return customerRepository.findAllByOrderByCustomerId(); }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Customer findCustomerByCustomerId(@PathVariable("id") int id) {
        return customerRepository.findCustomerByCustomerId(id);
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("id") int id) {
        customer.setCustomerId(id);
        return customerRepository.save(customer);
    }

    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer deleteCustomer(@RequestBody Customer customer, @PathVariable("id") int id) {
        customer.setCustomerId(id);
        customer.setDeleteFlag(1);
        return customerRepository.save(customer);
    }
}

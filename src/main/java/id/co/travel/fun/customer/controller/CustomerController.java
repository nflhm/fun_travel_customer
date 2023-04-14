package id.co.travel.fun.customer.controller;

import id.co.travel.fun.customer.model.Customer;
import id.co.travel.fun.customer.repository.ICustomerRepository;
import id.co.travel.fun.customer.req.CustomerReq;
import id.co.travel.fun.customer.res.MessageRes;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    ICustomerRepository customerRepository;
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageRes<List<Customer>>> findAll() {
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success get all customer data");
        res.put("data", customerRepository.findAllByOrderByCustomerId());
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageRes<Customer>> findCustomerByCustomerId(@PathVariable("id") int id) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success find customer data");
        res.put("data", customerRepository.findCustomerByCustomerId(id));
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageRes> addCustomer(@RequestBody CustomerReq customerReq) {
        Customer customer = new Customer(customerReq);
        customerRepository.save(customer);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success add customer data");
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageRes<Customer>> updateCustomer(@RequestBody Customer customer, @PathVariable("id") int id) {
        customer.setCustomerId(id);
        customerRepository.save(customer);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success update customer data");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageRes<Customer>> deleteCustomer(@PathVariable("id") int id) {
        Customer customer = customerRepository.findCustomerByCustomerId(id);
        customer.setCustomerId(id);
        customer.setDeleteFlag(1);
        customerRepository.save(customer);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success delete customer data");
        return new ResponseEntity(res, HttpStatus.OK);
    }
}

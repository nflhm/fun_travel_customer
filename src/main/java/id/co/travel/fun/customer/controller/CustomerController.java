package id.co.travel.fun.customer.controller;

import id.co.travel.fun.customer.model.Customer;
import id.co.travel.fun.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;
    @GetMapping("/")
    public @ResponseBody List<Customer> selectAll() { return customerRepository.selectAll(); }
    @GetMapping("/find")
    public @ResponseBody Customer selectUnique(@RequestParam("id") int id) {
        return customerRepository.selectUnique(id);
    }
    @GetMapping("/add")
    public String addCustomer(@RequestParam("username") String username,
                              @RequestParam("name") String name,
                              @RequestParam("password") String password,
                              @RequestParam("email") String email) {
        Customer customer = new Customer(0, username, name, password, email, 0);
        customerRepository.add(customer);
        return "redirect:/customer/";
    }
    @GetMapping("/update")
    public String updateCustomer(@RequestParam("id") int id,
                                 @RequestParam("username") String username,
                                 @RequestParam("name") String name,
                                 @RequestParam("password") String password,
                                 @RequestParam("email") String email) {
        Customer modcustomer = new Customer(id, username, name, password, email, 0);
        customerRepository.update(modcustomer);
        return "redirect:/customer/";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") int id) {
        customerRepository.delete(id);
        return "redirect:/customer/";
    }
}

package com.example.java6_thymeleaf.controller;

import com.example.java6_thymeleaf.model.Customer;
import com.example.java6_thymeleaf.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController
@RequestMapping(value = "/view")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<Customer> list() {
        return customerRepository.findAll();
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return ResponseEntity.ok("Customer create successfully");
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
  public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Customer customer) {
        Customer customer1 = customerRepository.findById(id).orElseThrow();
        customer1.setName(customer.getName());
        customer1.setEmail(customer.getEmail());
        customerRepository.save(customer1);
        return ResponseEntity.ok("Customer update successfully by id = " + id);
    }
    @CrossOrigin
    @GetMapping("/details/{id}")
    public Customer details(@PathVariable Long id) {
    Customer customer1 = customerRepository.findById(id).orElseThrow();
        return customer1;
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return ResponseEntity.ok("Customer deleted successfully by id = " + id);
    }

}

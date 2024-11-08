package com.example.cargo.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cargo.Entity.Customers;
import com.example.cargo.Service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<Object> createCustomer(@RequestBody Customers customer) {

		LocalDate cutoffDate = LocalDate.of(2002, 1, 1);

		if (customer.getDetails().getDob().isBefore(cutoffDate)) {
			Customers savedCustomer = customerService.createCustomer(customer);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DOB is invalid (must be before 01-01-2002)");
		}

	}

	@GetMapping
	public ResponseEntity<List<Customers>> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getCustomerById(@PathVariable Integer id) throws Exception {
		Optional<Customers> customer = customerService.getCustomerById(id);
		if (customer.isPresent()) {
			return ResponseEntity.ok(customer.get());
		} else {
			throw new Exception("Customer not found");
		}
	}

	@PutMapping("update/{id}")
	public ResponseEntity<Object> update(@RequestBody Customers customer, @PathVariable Integer id) {
		ResponseEntity<String> CustomersUpdate = customerService.updateCustomer(id, customer);
		return ResponseEntity.ok(CustomersUpdate);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable Integer id) throws Exception {
		Optional<Customers> customer = customerService.deleteCustomer(id);
		if (customer.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body("Delete one Record succefully");
		} else {
			throw new Exception("CustomerRecord not found");
		}
	}

}

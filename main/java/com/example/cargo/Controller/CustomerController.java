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
import org.springframework.web.server.ResponseStatusException;

import com.example.cargo.DTO.CustomerDTO;
import com.example.cargo.Entity.Customers;
import com.example.cargo.Exeception.CustomerNotFoundException;
import com.example.cargo.Exeception.CustomerSaveException;
import com.example.cargo.Exeception.InvalidDateException;
import com.example.cargo.Service.CustomerService;
import com.example.cargo.mapper.CustomerMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

//	@PostMapping("/createcustomer")
//	public ResponseEntity<Object> createCustomer(@RequestBody Customers customer) {
//		try {
//			LocalDate cutoffDate = LocalDate.of(2002, 1, 1);
//
//			if (!customer.getDetails().getDob().isBefore(cutoffDate)) {
//				throw new InvalidDateException("DOB is invalid (must be before 01-01-2002)");
//			}
//
//			Customers savedCustomer = customerService.createCustomer(customer);
//			if (savedCustomer == null || savedCustomer.getName().isEmpty()) {
//				logger.error("Customer save failed. Returned customer is null.");
//				throw new CustomerSaveException("Failed to save customer. Please try again.");
//			}
//
//			return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
//
//		} catch (InvalidDateException | CustomerSaveException ex) {
//
//			throw ex;
//		} catch (Exception ex) {
//			throw new RuntimeException("An unexpected error occurred", ex);
//		}
//	}

	@PostMapping("/createcustomer")
	public ResponseEntity<Object> createCustomer(@RequestBody CustomerDTO customerDTO) {
		try {
			LocalDate cutoffDate = LocalDate.of(2002, 1, 1);

			if (!customerDTO.getDetails().getDob().isBefore(cutoffDate)) {
				throw new InvalidDateException("DOB is invalid (must be before 01-01-2002)");
			}

			// Convert DTO to Entity using MapStruct
			Customers customer = CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);

			Customers savedCustomer = customerService.createCustomer(customer);
			if (savedCustomer == null || savedCustomer.getName().isEmpty()) {
				logger.error("Customer save failed. Returned customer is null.");
				throw new CustomerSaveException("Failed to save customer. Please try again.");
			}

			// Convert saved entity back to DTO for response
			CustomerDTO savedCustomerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(savedCustomer);

			return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerDTO);

		} catch (InvalidDateException | CustomerSaveException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException("An unexpected error occurred", ex);
		}
	}
	
//	public ResponseEntity<List<Course>> getAllCoursesWebClient() {
//		List<Course> courses = webClientBuilder.baseUrl("http:8095/studentconfig/")
//	            .build()
//	            .get()
//	            .uri("/courses/getAllCourses")
//	            .retrieve()
//	            .bodyToMono(new ParameterizedTypeReference<List<Course>>() {})
//	            .block();
//		return  courses;
//	}

	@GetMapping("/getallcustomers")
	public ResponseEntity<List<Customers>> getAllCustomers() {
		try {
			return customerService.getAllCustomers();
		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve customers");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getCustomerById(@PathVariable Integer id) {
		try {
			Optional<Customers> customer = customerService.getCustomerById(id);
			if (customer.isPresent()) {
				return ResponseEntity.ok(customer.get());
			} else {
				throw new Exception("Customer not found");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e); // 404
		}
	}

	@PutMapping("update/{id}")
	public ResponseEntity<Object> update(@RequestBody Customers customer, @PathVariable Integer id) {
		ResponseEntity<String> CustomersUpdate = customerService.updateCustomer(id, customer);
		return ResponseEntity.ok(CustomersUpdate);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable Integer id) {
		try {
			Optional<Customers> customer = customerService.deleteCustomer(id);
			if (customer.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body("Deleted one record successfully");
			} else {
				throw new CustomerNotFoundException("Customer record not found with ID: " + id);
			}
		} catch (Exception e) {
			throw new RuntimeException("An error occurred while deleting the customer: " + e.getMessage());
		}
	}

}

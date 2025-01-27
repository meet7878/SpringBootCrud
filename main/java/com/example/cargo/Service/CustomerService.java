package com.example.cargo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cargo.Entity.BusinessRequirement;
import com.example.cargo.Entity.Customers;
import com.example.cargo.Exeception.CustomerNotFoundException;
import com.example.cargo.Repository.BusinessRequirementRepository;
import com.example.cargo.Repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BusinessRequirementRepository businessRequirementRepository;

	public Customers createCustomer(Customers customer) {
		Customers cus = customerRepository.save(customer);
		List<BusinessRequirement> bus = customer.getBusinessRequirements().stream().map(item -> {
			item.setCustomer(cus);
			return item;
		}).toList();

		businessRequirementRepository.saveAll(bus);
		return cus;
	}

	public ResponseEntity<List<Customers>> getAllCustomers() {
		List<Customers> CustomersList = customerRepository.findAll();
		return ResponseEntity.ok(CustomersList);
	}

	public Optional<Customers> getCustomerById(Integer id) {
		return customerRepository.findById(id);
	}

	public ResponseEntity<String> updateCustomer(Integer id, Customers customer) {
		Optional<Customers> existingCustomer = customerRepository.findById(id);
		try {
			if (existingCustomer.isPresent()) {
				customer.setId(existingCustomer.get().getId());
				customerRepository.save(customer);
				return ResponseEntity.status(HttpStatus.OK).body("Update customer Data successfully for id: " + id);
			} else {
				throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
			}
		} catch (Exception e) {
			throw new RuntimeException("An error occurred while updating customer data: " + e.getMessage());
		}
	}

	public Optional<Customers> deleteCustomer(Integer id) {
		Optional<Customers> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			customerRepository.deleteById(id);
			return customer;
		} else {
			return Optional.empty();
		}
	}
}

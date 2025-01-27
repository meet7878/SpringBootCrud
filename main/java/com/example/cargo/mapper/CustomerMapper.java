package com.example.cargo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.cargo.DTO.CustomerDTO;
import com.example.cargo.Entity.Customers;

@Mapper
public interface CustomerMapper {
	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	CustomerDTO customerToCustomerDTO(Customers customer);

	Customers customerDTOToCustomer(CustomerDTO customerDTO);
}

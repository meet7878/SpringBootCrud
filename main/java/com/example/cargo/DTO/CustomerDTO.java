package com.example.cargo.DTO;

import jakarta.validation.constraints.Pattern;
import java.util.Set; // For Set collection

public class CustomerDTO {

	private Integer id;

	private String name;

	private String accountType;

	@Pattern(regexp = "fulltime|parttime", message = "contractType should be 'fulltime' or 'parttime'")
	private String contractType;

	private DetailsDTO details;

	private Set<RequirementDTO> businessRequirements;

	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public DetailsDTO getDetails() {
		return details;
	}

	public void setDetails(DetailsDTO details) {
		this.details = details;
	}

	public Set<RequirementDTO> getBusinessRequirements() {
		return businessRequirements;
	}

	public void setBusinessRequirements(Set<RequirementDTO> businessRequirements) {
		this.businessRequirements = businessRequirements;
	}

}

package com.example.cargo.Entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "customers")
public class Customers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Column(name = "account_type")
	private String accountType;

	@Pattern(regexp = "fulltime|parttime", message = "contractType should be 'fulltime' or 'parttime'")
	@Column(name = "contract_type")
	private String contractType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "details_id", referencedColumnName = "id")
	private Details details;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<BusinessRequirement> businessRequirements;

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

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public Set<BusinessRequirement> getBusinessRequirements() {
		return businessRequirements;
	}

	public void setBusinessRequirements(Set<BusinessRequirement> businessRequirements) {
		this.businessRequirements = businessRequirements;
	}

}

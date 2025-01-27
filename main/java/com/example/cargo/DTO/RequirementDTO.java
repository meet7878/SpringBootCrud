package com.example.cargo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RequirementDTO {
	private Integer id;
	private String requirement;

	@JsonIgnore
	private Integer customerId;

	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
}

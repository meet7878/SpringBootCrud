package com.example.cargo.DTO;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class DetailsDTO {

	private Integer id;

	@Pattern(regexp = "M|F", message = "Sex should be 'M' or 'F'")
	private String sex;

	@NotNull(message = "Date of birth is required")
	@Past(message = "Date of birth must be in the past")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	private String nativeLocation;

	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getNativeLocation() {
		return nativeLocation;
	}

	public void setNativeLocation(String nativeLocation) {
		this.nativeLocation = nativeLocation;
	}
}
